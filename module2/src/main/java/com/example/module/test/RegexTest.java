package com.example.module.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 正则表达式
 * @author: liubao
 * @Date: Created in 2018/6/28 9:25
 */
public class RegexTest {
    /**
     * 网上搜了一个手机号的正则
     */
    private static String PHONE_NUMBER_REG = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";

    public static boolean method1(String a){
        Pattern compile = Pattern.compile(PHONE_NUMBER_REG);
        Matcher matcher = compile.matcher(a);
        return matcher.matches();
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(method1("13582037548"));
        System.out.println(method1("10000000000"));
    }
}
