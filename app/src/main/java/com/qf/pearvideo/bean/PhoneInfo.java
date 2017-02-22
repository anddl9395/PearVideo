package com.qf.pearvideo.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/21.
 */

public class PhoneInfo implements Serializable{

    private String id;                      //对应X-Client-ID 手机的id
    private String num;                     //对应X-Serial-Num 当前时间的秒数
    private String platformVersion;         //对应X-Platform-Version 用户手机的版本
    private String clientVersion = "2.0.1"; //对应X-Client-Version
    private String clientAgent;             //对应X-Client-Agent 手机的型号代理
    private String platformType = "2";           //对应X-Platform-Type

    public PhoneInfo(){}

    public void setId(String id) {
        this.id = id;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public void setClientAgent(String clientAgent) {
        this.clientAgent = clientAgent;
    }

    public String getId() {
        return id;
    }

    public String getNum() {
        return num;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public String getClientAgent() {
        return clientAgent;
    }

    public String getPlatformType() {
        return platformType;
    }

    public String getIdName() {
        return "X-Client-ID";
    }

    public String getNumName() {
        return "X-Serial-Num";
    }

    public String getPlatformVersionName() {
        return "X-Platform-Version";
    }

    public String getClientVersionName() {
        return "X-Client-Version";
    }

    public String getClientAgentName() {
        return "X-Client-Agent";
    }

    public String getPlatformTypeName() {
        return "X-Platform-Type";
    }

    @Override
    public String toString() {
        return "PhoneInfo{" +
                "id='" + id + '\'' +
                ", num='" + num + '\'' +
                ", platformVersion='" + platformVersion + '\'' +
                ", clientVersion='" + clientVersion + '\'' +
                ", clientAgent='" + clientAgent + '\'' +
                ", platformType='" + platformType + '\'' +
                '}';
    }
}
