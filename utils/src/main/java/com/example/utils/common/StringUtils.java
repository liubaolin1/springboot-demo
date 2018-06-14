package com.example.utils.common;

import static com.example.utils.common.Print.println;

/**
 * @Description:
 * @author: liubao
 * @Date: Created in 2018/6/14 16:36
 */
public class StringUtils {
    /**
     * 可以同时传入多个参数，如果其中有一个为 ""或null 则返回true
     * 多个参数时 只要有一个为空 返回true
     * @param strings
     * @return
     */
    public static boolean isEmpty(String... strings){
        if(strings != null && strings.length>0) {
            for (String o : strings) {
                if (o == null || o.length() == 0 || "".equals(o)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        println(isEmpty("a","b"),
                isEmpty("a","b"),
                isEmpty("a",""),
                isEmpty("a",""),
                isEmpty("","b"),
                isEmpty("a",null),
                isEmpty("a",null));
    }
}
