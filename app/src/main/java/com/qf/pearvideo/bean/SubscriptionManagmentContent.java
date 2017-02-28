package com.qf.pearvideo.bean;

/**
 * Created by Administrator on 2017/2/28.
 */

public class SubscriptionManagmentContent {
    private String name;//标题
    private String desc;//内容
    private String logoImg;//头像的地址

    public SubscriptionManagmentContent() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getLogoImg() {
        return logoImg;
    }
    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }
    @Override
    public String toString() {
        return "SubscriptionManagmentContent{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", logoImg='" + logoImg + '\'' +
                '}';
    }
}
