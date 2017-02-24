package com.qf.pearvideo.dao;

import com.qf.pearvideo.bean.PhoneInfo;
import com.qf.pearvideo.callback.PearStringCallBack;

/**
 * Created by Administrator on 2017/2/21.
 */

public interface IPearDao {
    //首次加载的回调接口
    public void getTitleInfo(String url, PhoneInfo phoneInfo, PearStringCallBack callBack);

    //获取主要内容的回调接口
    public void getIndexMainInfo(String url, String cookie, PearStringCallBack callBack);

    //加载兴趣页面的回调接口
    public void getInterestInfo(String url, String cookie, PearStringCallBack callBack);
}
