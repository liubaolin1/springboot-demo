package com.example.module.java8.demo3;

import com.example.module.java8.entity.Apple;
import com.example.module.java8.common.Generator;
import com.example.module.java8.entity.Orange;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Description: 1.更灵活的写法
 *               2.用Comparator来排序
 * @author: liubao
 * @Date: Created in 2018/10/31 15:00
 */
public class Demo1 {

    /**
     * demo2中 我们写的方法只适用于苹果
     * 还可以将List类型抽象化
     * 从而使 香蕉 橘子 也可以共用
     */


    public static void main(String[] args) {
        /**
         * 用匿名类来实现方法
         * 获取middle类型的orange
         */
        List<Orange> middleOrange = filter(Generator.getOrangeList(), new Predicate<Orange>() {
            @Override
            public boolean test(Orange orange) {
                return "middle".equals(orange.getType());
            }
        });
        /**
         * 用lambda方法
         * 获取绿色苹果集合
         */
        List<Apple> greenApples =
                filter(Generator.getAppleList(),(Apple apple)->"green".equals(apple.getColor()));


        /**
         * 用Comparator来排序
         * java8中 list自带了一个sort方法
         */
        List<Apple> appleList = Generator.getAppleList();
        System.out.println("排序前");
        for(Apple a:appleList){
            System.out.println(a);
        }
        appleList.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return String.valueOf(o1.getWeight()).compareTo(String.valueOf(o2.getWeight()));
            }
        });
        System.out.println("排序后");
        for(Apple a:appleList){
            System.out.println(a);
        }

        /**
         * 用lambda排序
         * 暂时先不用担心这个新语法
         * 下一章会详细讲解如何编写和使用lambda表达式
         */
        List<Orange> orangeList = Generator.getOrangeList();
        orangeList.sort(
                (Orange o1,Orange o2)->
                        String.valueOf(o1.getWeight()).compareTo(String.valueOf(o2.getWeight())));

        System.out.println("橘子排序后");
        for(Orange o:orangeList){
            System.out.println(o);
        }

    }








    public static <T> List<T> filter(List<T> list,Predicate<T> p){
        List<T> result = new ArrayList<>();
        for (T e:list){
            if(p.test(e)){
                result.add(e);
            }
        }
        return result;
    }
}
