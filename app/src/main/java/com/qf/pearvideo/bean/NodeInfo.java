package com.qf.pearvideo.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/23.
 */

public class NodeInfo implements Serializable{
    private int nodeId;             //订阅ID
    private String nodeName;        //订阅名称
    private String nodeLogoImgPath; //订阅头像Logo地址

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeLogoImgPath() {
        return nodeLogoImgPath;
    }

    public void setNodeLogoImgPath(String nodeLogoImgPath) {
        this.nodeLogoImgPath = nodeLogoImgPath;
    }
}
