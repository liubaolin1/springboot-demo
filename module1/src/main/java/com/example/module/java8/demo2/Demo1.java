package com.example.module.java8.demo2;

import com.example.module.java8.entity.Apple;
import com.example.module.java8.common.Generator;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 2.2行为参数化
 * @author: liubao
 * @Date: Created in 2018/10/29 9:57
 */
public class Demo1 {


    /**
     * 在上一节已经看到
     * 现在需要一种比添加很多参数更好的方法来应对变化的需求
     * 一种解决方案是对你的选择标准建模：
     *     考虑的是苹果，需要根据苹果的某些属性来返回一个boolean值
     *     让我们定义一个接口来对选择标准建模 ApplePredicate
     *     然后根据ApplePredicate的多个实现代表不通的选择标准
     */

    /**
     * 行为参数化
     * 多种行为  一个参数
     * 例如 filterGreenApples 方法 参数是固定的 却可以实现不同的功能
     */


    public static void main(String[] args) {

        List<Apple> appleList = Generator.getAppleList();
        /**
         * 筛选绿色的苹果
         * 这段代码比我们第一次尝试的时候灵活多了
         * 如果需求有变更 只需要创建一个类来实现ApplePredicate就行了
         * 这种方法就是 行为参数化
         * 当然 缺点就是代码必须包裹在实现类里
         * 通过下一节 使用lambda 无需定义多个实现类
         */
        List<Apple> greenApples = filterApples(appleList, new AppleGreenColorPredicate());

        /**
         * 筛选重的苹果
         */
        List<Apple> heavyApples = filterApples(appleList, new AppleHeavyWeightPredicate());

        /**
         * 使用匿名类筛选红色苹果 不用创建实现类
         * 内联参数化 filterApples 方法
         */
        List<Apple> redApples = filterApples(appleList, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });
        /**
         * 使用匿名类筛选比较轻的苹果
         */
        List<Apple> lightApples = filterApples(appleList, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getWeight() < 150;
            }
        });

        /**
         * 但是匿名类还是不够好
         * 第一 比较笨重
         * 第二 用起来比较难读
         * 好的代码是一目了然的
         */

        /**
         * 上面的代码在java8里可以用lambda表达式
         * 使用lambda表达式
         */
        List<Apple> redApples2 =
                filterApples(appleList,(Apple apple) -> "red".equals(apple.getColor()));

        /**
         * 总的来说
         * 值参数化(多个方法)<接口实现类<匿名类<Lambda
         */

    }



    /**
     * 根据抽象条件筛选
     * @param inventory
     * @return
     */
    public static List<Apple> filterApples(List<Apple> inventory,
                                                ApplePredicate p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple:inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }
}
