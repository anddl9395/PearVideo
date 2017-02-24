package com.qf.pearvideo.bean;

/**
 * Created by Administrator on 2017/2/23.
 */

public class SystemMessage {
    private String ID;
    private String title;
    private String detail;
    private String pubTime;
    public SystemMessage(){

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    @Override
    public String toString() {
        return "SystemMessage{" +
                "ID='" + ID + '\'' +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", pubTime='" + pubTime + '\'' +
                '}';
    }
}
