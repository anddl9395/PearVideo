package com.qf.pearvideo.present.impl;

import android.content.Context;

import com.qf.pearvideo.bean.PhoneInfo;
import com.qf.pearvideo.callback.PearStringCallBack;
import com.qf.pearvideo.dao.IPearDao;
import com.qf.pearvideo.dao.impl.PearDao;
import com.qf.pearvideo.fragment.IIndexFragment;
import com.qf.pearvideo.present.ITitleInfoPresenter;

/**
 * Created by Administrator on 2017/2/21.
 */

public class TitleInfoPresenter implements ITitleInfoPresenter {

    private Context context;
    private IIndexFragment mIIndexFragment;

    public TitleInfoPresenter(Context context, IIndexFragment mIIndexFragment) {
        this.context = context;
        this.mIIndexFragment = mIIndexFragment;
    }

    private IPearDao pearDao = new PearDao();
    private PearStringCallBack stringCallBack = new PearStringCallBack() {
        @Override
        public void doResult(String resultStr) {
            //返回的数据
        }
    };

    @Override
    public void getTitleInfo(String url, PhoneInfo phoneInfo) {
        if (phoneInfo != null){
            pearDao.getTitleInfo(url, phoneInfo, stringCallBack);
        }
    }
}
