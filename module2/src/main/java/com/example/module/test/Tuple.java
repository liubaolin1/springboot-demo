package com.example.module.test;

import static com.example.utils.common.Print.println;

/**
 * @Description: 元组
 * @author: liubao
 * @Date: Created in 2018/7/3 10:01
 */
public class Tuple<Student,Person> {


    /**
     * 元组的功能是 为了在return时候想返回两个对象使用的
     * 用泛型限制了类型 所以不需要进行类型转换
     * 调用时泛型的类的前后顺序要写对
     */
    public final Person person;
    public final Student student;


    public Tuple(Person person,Student student) {
        this.student = student;
        this.person = person;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "student=" + student +
                ", person=" + person +
                '}';
    }
}

/**
 * 创建类1
 */
class Person{
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}

/**
 * 创建类2
 */
class Student{
    private String stuCount;
    private String school;

    public String getStuCount() {
        return stuCount;
    }

    public void setStuCount(String stuCount) {
        this.stuCount = stuCount;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Student(String stuCount, String school) {
        this.stuCount = stuCount;
        this.school = school;
    }
}

/**
 * 测试类
 */
class TupleTest{
    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        Person person = new Person("刘宝",22);
        Student student = new Student("33","清华大学");
        Tuple<Student,Person> tuple = new Tuple(person,student);
        println(tuple.person.getName());
    }
}
