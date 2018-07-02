package com.example.module.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static com.example.utils.common.Print.println;
import static com.example.utils.common.Print.printlnNoneTime;

/**
 * @Description: 反射机制
 * @author: liubao
 * @Date: Created in 2018/6/29 11:07
 */
public class Reflex {

    /**
     * 自己没怎么用过反射
     * 这是自己学习过程中的简单的记录
     * <<java编程思想>> 书中写的不太明白,而且还要了解其他无关的东西 比如 main方法的参数是什么等 不利于专注了解
     * 一番搜索之后感觉 这篇博客写的可以 所以参考了一下
     * http://www.cnblogs.com/rollenholt/archive/2011/09/02/2163758.html
     *
     *
     */


    /**
     * 测试
     *
     * 获取类有三种方式
     * Class.forName("")  参数是 package+className
     * new Demo().getClass()
     * Demo.class
     *
     * 建议测试哪个就把其他注释掉 不然输出太多 不好找
     * @param args
     */
    public static void main(String[] args) {


        /**
         * 获取类de三种方式
         * 第二种由于是根据类名去找的
         * 所以有可能会出现找不到类的异常
         * java.lang.ClassNotFoundException
         */
        Class aClass = Reflex.class;

        Class bClass = null;
        try {
            bClass = Class.forName("com.example.module.test.Reflex");
        }catch (ClassNotFoundException c){
            c.printStackTrace();
        }

        Class cClass = new Reflex().getClass();

        printlnNoneTime(
                "第一种方式："+aClass.getName(),
                "第二种方式："+bClass.getName(),
                "第三种方式："+cClass.getName());




        /**
         * 获取所有的构造方法
         */
        Constructor<?>[] constructors = aClass.getConstructors();
        for (Constructor o:constructors){
            printlnNoneTime(o.toString());
        }

        /**
         * 可以用构造方法构建实例
         */
        try {
            Reflex o = (Reflex)constructors[0].newInstance("李四", "李四说他不吃鸡");
        }catch (Exception e){
            e.printStackTrace();
        }


        /**
         * 获取类中所有的方法（好像还多了几个？）
         * todo 后面看到了会补上原因
         */
        Method[] methods = aClass.getMethods();
        for (Object o:methods){
            printlnNoneTime(o.toString());
        }

        /**
         * 实例化
         * 实例化有可能会出现异常
         * 比如要实例化的类没有无参构造方法
         * 会抛出实例化异常
         * java.lang.InstantiationException
         *
         * 所以在使用反射构建对象时
         * 一定要给要构建的对象加上无参的构造方法
         */
        Reflex reflex = null;
        try {
            reflex = (Reflex) aClass.newInstance();
            reflex.setName("张三");
            reflex.setValue("张三说他跟张三丰差不多");
            printlnNoneTime(reflex);
        }catch (Exception e){
            e.printStackTrace();
        }


        /**
         * 获取本类全部属性
         * 获取父类或者实现接口的属性 getFields()
         * 这里列举了三个
         * 字段名称
         * 字段类型
         * 修饰符
         */
        Field[] declaredFields = aClass.getDeclaredFields();
        for(Field f:declaredFields){
            println(f,
                    f.getName(),
                    f.getType(),
                    Modifier.toString(f.getModifiers()));
        }

        /**
         * 调用方法
         * 调用方法前要先实例化类
         */
        try {
            Object o = aClass.newInstance();
            Method method = aClass.getMethod("method1");
            Object invoke = method.invoke(o);
            printlnNoneTime(invoke);
        }catch (Exception n){
            n.printStackTrace();
        }

        /**
         * 操作类中的属性
         */
        try {
            Object o = aClass.newInstance();
            /**
             * 获取name这个属性
             */
            Field field = aClass.getDeclaredField("name");
            /**
             * 也可以获取所有属性
             */
            Field[] declaredFields1 = aClass.getDeclaredFields();
            for(Field f:declaredFields1){
                if(f.getName().equals("name")){
                    f.set(o,"刘宝");
                };
                if(f.getName().equals("value")){
                    f.set(o,"你好啊");
                };
            }
            println(o);
        }catch (Exception e){
            e.printStackTrace();
        }


        abc(Reflex.class);


    }

    /**
     * 通过反射 来调用类中的方法
     * @param c
     */
    public static void abc(Class<?> c){
        try {
            Object o = c.newInstance();
            Method method2 = c.getMethod("method2");
            println(method2.invoke(o));
        }catch (Exception e){
            e.printStackTrace();
        }
    }





    private String name;
    private String value;

    public Reflex() {
        printlnNoneTime("构建无参实例");
    }

    public Reflex(String name, String value) {
        this.name = name;
        this.value = value;
        printlnNoneTime("构建有参实例"+name+value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String method1(){
        return "method1";
    }
    public String method2(){
        return "method2";
    }

    public String method1(String s1,String s2){
        return "method1+s1+s2";
    }
    public String method2(String s1,String s2){
        return "method2+s1+s2";
    }

    @Override
    public String toString() {
        return "Reflex{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
