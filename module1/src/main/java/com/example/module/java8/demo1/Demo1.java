package com.example.module.java8.demo1;

import com.example.module.java8.entity.Apple;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 2.1 应对不断变化的需求
 * @author: liubao
 * @Date: Created in 2018/10/26 13:38
 */
public class Demo1 {
    public static void main(String[] args) {

    }
    /**
     * 筛选绿苹果
     * inventory 库存
     * filter 过滤 筛选
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory){
        /**
         * 创建筛选结果的列表
         */
        List<Apple> result = new ArrayList<>();
        /**
         * 筛选出绿色的苹果
         */
        for(Apple apple:inventory){
            if("green".equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 如果需求变化 要筛选各种颜色的苹果
     * 那么方法改为将颜色变为参数 就能灵活的使用变化了
     * @param inventory
     * @param color
     * @return
     */
    public static List<Apple> filterApplesByColor(List<Apple> inventory,String color){
        /**
         * 创建筛选结果的列表
         */
        List<Apple> result = new ArrayList<>();
        /**
         * 筛选出绿色的苹果
         */
        for(Apple apple:inventory){
            if(color.equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 需求又有变化 要筛选重量
     * 那么方法又要加上重量的参数
     * 如果flag 为true 只筛选颜色
     * 如果flag 为false 只筛选重量
     * @param inventory
     * @param color
     * @param weight
     * @param flag
     * @return
     */
    public static List<Apple> filterApples(List<Apple> inventory,String color,int weight,boolean flag){
        /**
         * 创建筛选结果的列表
         */
        List<Apple> result = new ArrayList<>();
        /**
         * 筛选出绿色的苹果
         */
        for(Apple apple:inventory){
            if((flag && color.equals(apple.getColor())) ||
                    (!flag && apple.getWeight() > weight)){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 如果需求又有变化 比如 筛选大小、形状、产地等
     * 又或者是组合属性 筛选绿色的重苹果 又该怎么办？
     * 你会有好多个重复的filter方法
     * 或者是一个巨大的非常复杂的方法
     * 下一节介绍如何李勇行为参数化实现这种灵活性
     */

}
