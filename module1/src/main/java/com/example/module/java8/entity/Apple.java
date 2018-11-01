package com.example.module.java8.entity;

/**
 * @Description:
 * @author: liubao
 * @Date: Created in 2018/10/26 13:39
 */
public class Apple {

    private String color;

    private int weight;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
