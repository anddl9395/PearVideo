package com.qf.pearvideo.bean;

/**
 * 订阅管理界面的标题
 * Created by Administrator on 2017/2/27.
 */

public class SubscriptionManagementUp {
    private String name;
    private String domainId;
    public SubscriptionManagementUp(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    @Override
    public String toString() {
        return "SubscriptionManagementUp{" +
                "name='" + name + '\'' +
                ", domainId='" + domainId + '\'' +
                '}';
    }
}

