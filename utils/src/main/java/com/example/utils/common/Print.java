package com.example.utils.common;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 自己封装了一下控制台输出
 * @author: liubao
 * @Date: Created in 2018/6/14 16:32
 */
public class Print {
    public static void print(Object... object){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+":");
        System.out.print("---->");
        for(Object o:object){
            System.out.print(o+" ");
        }
        System.out.println();
    }
    public  static void println(Object... object){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+":");
        for(Object o:object){
            System.out.println("---->"+o);
        }
    }

    /**
     * 测试
     * 可以打印输出 对象 数字 字符串等
     * 打印自定义对象 建议先再对象中重写toString方法
     * @param args
     */
    public static void main(String[] args) {
        Integer integer = new Integer(100);
        print(1,"a00",integer);
        println(1,"a00",integer);
    }
}
