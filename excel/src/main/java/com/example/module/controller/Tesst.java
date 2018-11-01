package com.example.module.controller;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: liubao
 * @Date: Created in 2018/7/16 8:51
 */
public class Tesst {

    private List<Test1> year;

}
class Test1{
    private Map<String,Test2> map;

    public Map<String, Test2> getMap() {
        return map;
    }

    public void setMap(Map<String, Test2> map) {
        this.map = map;
    }
}
class Test2{
    private String indname;
    private List<Test3> dataList;

    public String getIndname() {
        return indname;
    }

    public void setIndname(String indname) {
        this.indname = indname;
    }

    public List<Test3> getDataList() {
        return dataList;
    }

    public void setDataList(List<Test3> dataList) {
        this.dataList = dataList;
    }
}
class Test3{
    String area;
    String value;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
