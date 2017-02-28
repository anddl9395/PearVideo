package com.qf.pearvideo.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/23.
 * 内容实体类
 */

public class Cont implements Serializable{

    private int contId;         //内容ID
    private String contName;    //内容标题
    private String pic;         //内容图片
    private String Label;       //推荐等标签
    private String duration;    //视频时长
    private String videoPath;   //视频地址

    private NodeInfo nodeInfo;  //NodeInfo实体类

    public int getContId() {
        return contId;
    }

    public void setContId(int contId) {
        this.contId = contId;
    }

    public String getContName() {
        return contName;
    }

    public void setContName(String contName) {
        this.contName = contName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public NodeInfo getNodeInfo() {
        return nodeInfo;
    }

    public void setNodeInfo(NodeInfo nodeInfo) {
        this.nodeInfo = nodeInfo;
    }
}
