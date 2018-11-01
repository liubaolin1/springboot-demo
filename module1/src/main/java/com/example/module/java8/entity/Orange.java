package com.example.module.java8.entity;

/**
 * @Description:
 * @author: liubao
 * @Date: Created in 2018/10/31 15:04
 */
public class Orange {

    private int weight;

    private String type;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Orange{" +
                "weight=" + weight +
                ", type='" + type + '\'' +
                '}';
    }
}
