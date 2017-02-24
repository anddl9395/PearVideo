package com.qf.pearvideo.bean;

/**
 * 兴趣页面的实体类
 * Created by Administrator on 2017/2/23 0023.
 */

public class Interest {
    private String pic;//视频的图片
    private String name;//视频的标题名字
    private String logoname;//发视频者的名字
    private String logoimg;//发视频者的头像地址
    private String duration;//视频时长
    private String nextUrl;//加载下一页的地址

    public Interest(){

    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoname() {
        return logoname;
    }

    public void setLogoname(String logoname) {
        this.logoname = logoname;
    }

    public String getLogoimg() {
        return logoimg;
    }

    public void setLogoimg(String logoimg) {
        this.logoimg = logoimg;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    @Override
    public String toString() {
        return "Interest{" +
                "pic='" + pic + '\'' +
                ", name='" + name + '\'' +
                ", logoname='" + logoname + '\'' +
                ", logoimg='" + logoimg + '\'' +
                ", duration='" + duration + '\'' +
                ", nextUrl='" + nextUrl + '\'' +
                '}';
    }
}
