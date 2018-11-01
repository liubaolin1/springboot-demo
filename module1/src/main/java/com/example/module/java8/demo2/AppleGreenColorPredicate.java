package com.example.module.java8.demo2;

import com.example.module.java8.entity.Apple;

/**
 * @Description: 筛选绿色苹果的实现
 * @author: liubao
 * @Date: Created in 2018/10/29 10:32
 */
public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
