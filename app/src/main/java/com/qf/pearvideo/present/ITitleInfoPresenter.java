package com.qf.pearvideo.present;

import com.qf.pearvideo.bean.PhoneInfo;

/**
 * Created by Administrator on 2017/2/21.
 * 用于获取首页标题栏数据处理，并生成cookie
 */

public interface ITitleInfoPresenter {
    public void getTitleInfo(String url, PhoneInfo phoneInfo);
}
