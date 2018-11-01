//package com.example.module.excel;
//
//import org.apache.poi.hssf.usermodel.*;
//import javax.servlet.http.HttpServletResponse;
//import java.io.UnsupportedEncodingException;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.text.SimpleDateFormat;
//import java.util.*;
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
//                                    List<LinkedHashMap> dataset, String pattern) {
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
//        Iterator<LinkedHashMap> it = dataset.iterator();
//        int index = 0;
//        while (it.hasNext()) {
//            index++;
//
//            row = sheet.createRow(index);
//            LinkedHashMap next = it.next();
//            Iterator iterator = next.keySet().iterator();
//            int index2 = 0;
//            while(iterator.hasNext()){
//                index2++;
//                Object next1 = iterator.next();
//                HSSFCell cell = row.createCell(index2);
//                cell.setCellStyle(style2);
//
//
//                HSSFRichTextString richString = new HSSFRichTextString(
//                        String.valueOf(next1));
//                richString.applyFont(font3);
//                cell.setCellValue(richString);
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
