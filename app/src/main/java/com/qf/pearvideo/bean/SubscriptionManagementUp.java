package com.qf.pearvideo.bean;

/**
 * Created by Administrator on 2017/2/27.
 */

public class SubscriptionManagementUp {
    private String name;

    @Override
    public String toString() {
        return "SubscriptionManagementUp{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

