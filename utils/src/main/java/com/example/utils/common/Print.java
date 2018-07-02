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
        System.out.print("====>");
        for(Object o:object){
            System.out.print(o+" ");
        }
        System.out.println();
    }
    public  static void println(Object... object){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+":");
        for(Object o:object){
            System.out.println("====>"+o);
        }
    }

    /**
     * 如果要使用这两个方法
     * 请先了解 System.out.print 和 System.err.print 输出的区别
     * @param object
     */
    public static void printRed(Object... object){
        System.err.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:ddd").format(new Date())+":");
        System.err.print("====>");
        for(Object o:object){
            System.err.print(o+" ");
        }
        System.out.println();
    }
    public  static void printlnRed(Object... object){
        System.err.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:ddd").format(new Date())+":");
        for(Object o:object){
            System.err.println("====>"+o);
        }
    }

    public  static void printlnNoneTime(Object... object){
        for(Object o:object){
            System.out.println("====>"+o);
        }
    }

    public static void printRun(){
        System.err.println("spring-boot START ");
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
