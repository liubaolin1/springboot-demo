package com.example.module.excel;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.utils.common.StringUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 导入工具类
 * 
 * @author liuxianling
 * @date 2018年7月10日
 */
public class ExcelImportUtil {

	private static final Logger log = LoggerFactory.getLogger(ExcelImportUtil.class);

	/**
	 * 读取配置信息
	 * 
	 * @param configFileName
	 * @return
	 * @throws IOException
	 * @date 2018年7月10日
	 */
	private static ImportConfig readConfig(String configFileName) throws IOException {

		XStream xstream = new XStream(new DomDriver());
		//		XStream.setupDefaultSecurity(xstream);
		xstream.allowTypes(new Class[]{ImportConfig.class, List.class, ImportField.class});
		// 设置别名
		xstream.alias("Config", ImportConfig.class);
		xstream.alias("fields", List.class);
		xstream.alias("field", ImportField.class);
		// 注解支持 
		xstream.autodetectAnnotations(true);

		String locationPattern = String.format("excel/%s", configFileName);
		InputStream in = null;
		ClassPathResource resource = new ClassPathResource(locationPattern);
		in = resource.getInputStream();
		return (ImportConfig) xstream.fromXML(in);
	}


	public static <T> List<T> getList(InputStream in, String configFileName, 
			Class<T> clazz, List<String> errorList) throws Exception {

		long startRead = System.currentTimeMillis();
		ExcelReader reader = ExcelUtil.getReader(in);
		List<List<Object>> rowList = reader.read();
		log.info("读取excel耗时 {}mm", System.currentTimeMillis() - startRead);

		List<Object> titleList = rowList.get(0);

		ImportConfig config = readConfig(configFileName);
		List<ImportField> fieldList = config.getFieldList();
		List<T> list = new ArrayList(rowList.size());

		for (int i=1; i<rowList.size(); i++) {
			List<Object> row = rowList.get(i);
			T obj = clazz.newInstance();
			list.add(obj);

			for (int j=0; j<fieldList.size(); j++) {
				Object data = row.get(j);
				ImportField fieldConfig = fieldList.get(j);
				if (data == null) {
					if (fieldConfig.isRequired()) {
						String errorInfo = ImportErrorType.REQUIRED.getDesc();
						errorList.add(String.format(errorInfo, String.valueOf(titleList.get(j)), i+1));
						break;
					}
					continue;
				}

				String dataStr = StringUtils.trim(data.toString());
				if (StringUtils.isBlank(dataStr)) {
					if (fieldConfig.isRequired()) {
						String errorInfo = ImportErrorType.REQUIRED.getDesc();
						errorList.add(String.format(errorInfo, String.valueOf(titleList.get(j)), i+1));
						break;
					}
					continue;
				}

				Field field = obj.getClass().getDeclaredField(fieldConfig.getName());
				if (!field.isAccessible()) {
					field.setAccessible(true);	
				}
				String type = fieldConfig.getType();
				try {
					if (type.equalsIgnoreCase("int")) {
						field.set(obj, Integer.valueOf(dataStr));
					} else if (type.equalsIgnoreCase("long")) {
						field.set(obj, Long.valueOf(dataStr));
					} else if (type.equalsIgnoreCase("date")) {
						try {
							Date date = parseDate(dataStr, fieldConfig.getFormat());
							field.set(obj, date);
						} catch (ParseException e) {
							log.error("日期转换失败", e);
							String errorInfo = ImportErrorType.FORMAT_ERROR.getDesc();
							errorList.add(String.format(errorInfo, String.valueOf(titleList.get(j)), i+1));
						}
					} else if (type.equalsIgnoreCase("string")) {
						field.set(obj, dataStr);
					}
				} catch (Exception e) {
					String errorInfo = ImportErrorType.TYPE_ERROR.getDesc();
					errorList.add(String.format(errorInfo, String.valueOf(titleList.get(j)), i+1));
					log.error("excel导入数据类型错误", e);
				}
			}
		}
		return list;
	}


	private static Date parseDate(String str, String pattern) throws ParseException {
		SimpleDateFormat sdf = null;
		if (StringUtils.isBlank(pattern)) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		} else {
			sdf = new SimpleDateFormat(pattern);
		}
		return sdf.parse(str);
	}

}
