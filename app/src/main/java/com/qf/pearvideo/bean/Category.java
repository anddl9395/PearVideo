package com.qf.pearvideo.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/22.
 * 头条标题栏的实体类
 */

public class Category implements Serializable{

    private int categoryId;
    private String name;
    private String color;

    public Category(int categoryId, String name, String color) {
        this.categoryId = categoryId;
        this.name = name;
        this.color = color;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
