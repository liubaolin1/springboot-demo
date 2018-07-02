package com.example.module.test;

/**
 * @Description: 类型转换前要先检查
 * @author: liubao
 * @Date: Created in 2018/6/28 14:39
 */
public class ShapeTest {
    private String name;

    public ShapeTest(String name) {
        this.name = name;
    }

    static class P1 extends ShapeTest{

        public P1(String name) {
            super(name);
        }
    }
    static class P2 extends ShapeTest{

        public P2(String name) {
            super(name);
        }
    }

    public static void main(String[] args) {
        P1 p1 = new P1("拉拉");

        if(p1 instanceof ShapeTest){
            String name = ((ShapeTest) p1).name;
            System.out.println(name);
        }
    }


}
