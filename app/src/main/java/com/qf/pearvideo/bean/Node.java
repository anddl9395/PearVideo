package com.qf.pearvideo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 * 总模块实体类
 */

public class Node implements Serializable{

    private int nodeType;
    private String nodeName;
    private List<Cont> contList;

    public int getNodeType() {
        return nodeType;
    }

    public void setNodeType(int nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public List<Cont> getContList() {
        return contList;
    }

    public void setContList(List<Cont> contList) {
        this.contList = contList;
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeType=" + nodeType +
                ", nodeName='" + nodeName + '\'' +
                ", contList=" + contList +
                '}';
    }
}
