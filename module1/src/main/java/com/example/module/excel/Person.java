package com.example.module.excel;

/**
 * @Description: 用来EXCEL导出
 * 变量顺序不可随便调整 影响导出Excel的顺序
 * @author: liubao
 * @Date: Created in 2018/6/15 10:12
 */
public class Person {

    private String name;

    private Integer age;

    private String sex;

    private String phone;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Person(String name, Integer age, String sex, String phone) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
