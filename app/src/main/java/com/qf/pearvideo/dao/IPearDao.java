package com.qf.pearvideo.dao;

import com.qf.pearvideo.bean.PhoneInfo;
import com.qf.pearvideo.callback.PearStringCallBack;

/**
 * Created by Administrator on 2017/2/21.
 */

public interface IPearDao {

    //加载标题
    public void getTitleInfo(String url, PhoneInfo phoneInfo, PearStringCallBack callBack);

    //获取系统消息
    public void getSystemMessage(String url, String cookie, PearStringCallBack callBack);

    //获取首页信息
    public void getIndexMainInfo(String url, String cookie, PearStringCallBack callBack);

    //获取兴趣页信息
    public void getInterestInfo(String url, String cookie, PearStringCallBack callBack);

    //获取首页文件
    public void getIndexMainFile(String url, final PearStringCallBack callBack);

    public void getSubscriptionTitle(String url, String cookie, PearStringCallBack mCallBack);
}
