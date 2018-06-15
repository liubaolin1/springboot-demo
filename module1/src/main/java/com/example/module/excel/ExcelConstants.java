package com.example.module.excel;

import org.apache.poi.hssf.util.HSSFColor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 简单配置excel的样式
 * @author: liubao
 * @Date: Created in 2018/6/15 9:33
 */
public class ExcelConstants {

    /**
     * 导出excel中的标题栏
     */
    public static final String[] HEADERS = new String[]{"姓名","年龄","性别","电话"};
    /**
     * 导出excel的文件名
     */
    public static String FILENAME = "人员信息表"+new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    /**
     * excel中当前sheet的名称
     */
    public static final String SHEETNAME = "人员信息表";
    /**
     * 设置表格列宽
     */
    public static final short COLNUM = 25;
    /**
     * excel表头表格背景颜色
     */
    public static final short HEADCOLOR = HSSFColor.LIGHT_GREEN.index;
    /**
     * excel表头表格字体颜色
     */
    public static final short HEADFONTCOLOR = HSSFColor.VIOLET.index;
    /**
     * excel内容部分表格背景颜色
     */
    public static final short BODYCOLOR = HSSFColor.LIGHT_YELLOW.index;
    /**
     * excel内容部分字体颜色
     */
    public static final short BODYFONTCOLOR = HSSFColor.BLACK.index;

}
