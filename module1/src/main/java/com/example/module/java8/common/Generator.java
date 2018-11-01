package com.example.module.java8.common;

import com.example.module.java8.entity.Apple;
import com.example.module.java8.entity.Orange;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 生成list
 * @author: liubao
 * @Date: Created in 2018/10/31 15:13
 */
public class Generator {


    /**
     * 获取苹果集合的方法
     * @return
     */
    public static List<Apple> getAppleList(){
        List<Apple> apples = new ArrayList<>();
        int size = 10;
        /**
         * 生成10个苹果的集合
         */
        for(int i=0;i<size;i++){
            Apple apple = new Apple();
            /**
             * 随机生成100-200之间的数字
             */
            apple.setWeight((int)(100+Math.random()*100));
            /**
             * 随机生成三种颜色
             */
            switch ((int)(1+Math.random()*3)){
                case 1:
                    apple.setColor("green");
                    break;
                case 2:
                    apple.setColor("red");
                    break;
                default:
                    apple.setColor("blue");
                    break;
            }
            apples.add(apple);
        }
        return apples;
    }



    /**
     * 获取橘子集合的方法
     * @return
     */
    public static List<Orange> getOrangeList(){
        List<Orange> oranges = new ArrayList<>();
        int size = 10;
        /**
         * 生成10个橘子的集合
         */
        for(int i=0;i<size;i++){
            Orange orange = new Orange();
            /**
             * 随机生成100-200之间的数字
             */
            orange.setWeight((int)(100+Math.random()*100));
            /**
             * 随机生成三种类型
             */
            switch ((int)(1+Math.random()*3)){
                case 1:
                    orange.setType("large");
                    break;
                case 2:
                    orange.setType("middle");
                    break;
                default:
                    orange.setType("small");
                    break;
            }
            oranges.add(orange);
        }
        return oranges;
    }
}
