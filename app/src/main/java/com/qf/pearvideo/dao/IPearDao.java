package com.qf.pearvideo.dao;

import com.qf.pearvideo.bean.PhoneInfo;
import com.qf.pearvideo.callback.PearStringCallBack;

/**
 * Created by Administrator on 2017/2/21.
 */

public interface IPearDao {
    public void getTitleInfo(String url, PhoneInfo phoneInfo, PearStringCallBack callBack);
}
