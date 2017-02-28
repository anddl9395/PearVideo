package com.qf.pearvideo.fragment;

import com.qf.pearvideo.bean.SystemMessage;

import java.util.List;

/**
 * Created by Administrator on 2017/2/25.
 */

public interface IPushMessageFragment {
    public void successInfo(List<SystemMessage> list);
    public void failInfo();
}
