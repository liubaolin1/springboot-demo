package com.example.module.java8.demo2;

import com.example.module.java8.entity.Apple;

/**
 * @Description: 筛选重苹果的实现
 * @author: liubao
 * @Date: Created in 2018/10/29 10:27
 */
public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
