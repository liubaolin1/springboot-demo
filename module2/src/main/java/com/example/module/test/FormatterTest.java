package com.example.module.test;

import java.util.Formatter;

/**
 * @Description: 格式化
 * @author: liubao
 * @Date: Created in 2018/6/27 16:47
 */
public class FormatterTest {
    private String name;
    private Formatter formatter;

    public FormatterTest(String name, Formatter formatter) {
        this.name = name;
        this.formatter = formatter;
    }
    public void move(int x,int y){
        Formatter format = formatter.format("%s The Test is at (%d,%d)\n", name, x, y);
        System.out.println("嘎嘎"+format.toString());
    }
    public static void main(String[] args) {
        FormatterTest test = new FormatterTest("terry",new Formatter());
        test.move(3,4);
    }
}
