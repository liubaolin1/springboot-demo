//package com.example.module.excel;
//
//import org.apache.poi.hssf.usermodel.*;
//import javax.servlet.http.HttpServletResponse;
//import java.io.UnsupportedEncodingException;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.text.SimpleDateFormat;
//import java.util.Collection;
//import java.util.Date;
//import java.util.Iterator;
//
///**
// * 利用开源组件POI3.0.2动态导出EXCEL文档
// * @author liubao
// * @param <T>
// *            应用泛型，代表任意一个符合javabean风格的类
// *            注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()
// *            byte[]表jpg格式的图片数据
// */
//public class ExportExcelUtil<T> {
//    public static final String FILE_SEPARATOR = System.getProperties()
//            .getProperty("file.separator");
//
//    public HSSFWorkbook exportExcel(Collection<T> dataset) {
//        return exportExcel("测试POI导出EXCEL文档", null, dataset, "yyyy-MM-dd");
//    }
//
//    public HSSFWorkbook exportExcel(String title, String[] headers, Collection<T> dataset) {
//        return exportExcel(title, headers, dataset, "yyyy-MM-dd");
//    }
//
//    public HSSFWorkbook exportExcel(String[] headers, Collection<T> dataset, String pattern) {
//        return exportExcel("测试POI导出EXCEL文档", headers, dataset, pattern);
//    }
//
//    /**
//     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
//     *
//     * @param title
//     *            表格标题名
//     * @param headers
//     *            表格属性列名数组
//     * @param dataset
//     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
//     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
//     *
//     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
//     * @param pattern
//     *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
//     */
//    @SuppressWarnings("unchecked")
//    public HSSFWorkbook exportExcel(String title, String[] headers,
//                                    Collection<T> dataset, String pattern) {
//        // 声明一个工作薄
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        // 生成一个表格
//        HSSFSheet sheet = workbook.createSheet(title);
//        // 设置表格默认列宽度为15个字节
//        sheet.setDefaultColumnWidth(ExcelConstants.COLNUM);
//        //下面是自动设置列宽 只能根据某列 来设置 并且老版本不支持
////		sheet.autoSizeColumn(1,true);
//        // 生成一个样式
//        HSSFCellStyle style = workbook.createCellStyle();
//        // 设置这些样式
//        style.setFillForegroundColor(ExcelConstants.HEADCOLOR);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        // 生成一个字体 表格标题字体
//        HSSFFont font = workbook.createFont();
//        font.setColor(ExcelConstants.HEADFONTCOLOR);
//        font.setFontHeightInPoints((short) 12);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        // 把字体应用到当前的样式
//        style.setFont(font);
//        // 生成并设置另一个样式 设置表格body样式
//        HSSFCellStyle style2 = workbook.createCellStyle();
//        style2.setFillForegroundColor(ExcelConstants.BODYCOLOR);
//        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        // 生成另一个字体
//        HSSFFont font2 = workbook.createFont();
//        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//        // 把字体应用到当前的样式
//        style2.setFont(font2);
//        // 声明一个画图的顶级管理器
//        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
//        //设置表格body中的字体颜色
//        HSSFFont font3 = workbook.createFont();
//        font3.setColor(ExcelConstants.BODYFONTCOLOR);
//        // 产生表格标题行
//        HSSFRow row = sheet.createRow(0);
//        for (short i = 0; i < headers.length; i++) {
//            HSSFCell cell = row.createCell(i);
//            cell.setCellStyle(style);
//            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
//            cell.setCellValue(text);
//        }
//        // 遍历集合数据，产生数据行
//        Iterator<T> it = dataset.iterator();
//        int index = 0;
//        while (it.hasNext()) {
//            index++;
//
//            row = sheet.createRow(index);
//            T t = (T) it.next();
//            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
//            Field[] fields = t.getClass().getDeclaredFields();
//            for (short i = 0; i < fields.length; i++) {
//                HSSFCell cell = row.createCell(i);
//                cell.setCellStyle(style2);
//                Field field = fields[i];
//                String fieldName = field.getName();
//                String getMethodName = "get"
//                        + fieldName.substring(0, 1).toUpperCase()
//                        + fieldName.substring(1);
//                try {
//                    Class tCls = t.getClass();
//                    Method getMethod = tCls.getMethod(getMethodName,
//                            new Class[] {});
//                    Object value = getMethod.invoke(t, new Object[] {});
//                    // 判断值的类型后进行强制类型转换
//                    String textValue = null;
//                    if(value == null){
//                        textValue = "";
//                    }else if (value instanceof Boolean) {
//                        boolean bValue = (Boolean) value;
//                        textValue = "1";
//                        if (!bValue) {
//                            textValue = "0";
//                        }
//                    } else if (value instanceof Date) {
//                        Date date = (Date) value;
//                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//                        textValue = sdf.format(date);
//                    } else if (value instanceof byte[]) {
//                        // 有图片时，设置行高为60px;
//                        row.setHeightInPoints(60);
//                        // 设置图片所在列宽度为80px,注意这里单位的一个换算
//                        sheet.setColumnWidth(i, (short) (35.7 * 80));
//                        byte[] bsValue = (byte[]) value;
//                        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
//                                1023, 255, (short) 6, index, (short) 6, index);
//                        anchor.setAnchorType(2);
//                        patriarch.createPicture(anchor, workbook.addPicture(
//                                bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
//                    } else {
//                        // 其它数据类型都当作字符串简单处理
//                        textValue = value.toString();
//                    }
//                    HSSFRichTextString richString = new HSSFRichTextString(
//                            textValue);
//                    richString.applyFont(font3);
//                    cell.setCellValue(richString);
//                } catch (SecurityException e) {
//                    e.printStackTrace();
//                } catch (NoSuchMethodException e) {
//                    e.printStackTrace();
//                } catch (IllegalArgumentException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                } finally {
//                    // 清理资源
//                }
//            }
//        }
//        return workbook;
//    }
//    public static void setResponseHeader(HttpServletResponse response, String fileName) {
//        try {
//            try {
//                fileName = new String(fileName.getBytes(),"ISO8859-1");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//            response.setContentType("application/octet-stream;charset=ISO8859-1");
//            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
//            response.addHeader("Pargam", "no-cache");
//            response.addHeader("Cache-Control", "no-cache");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//}
//
