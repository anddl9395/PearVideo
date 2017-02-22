package com.qf.pearvideo.present.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.qf.pearvideo.bean.PhoneInfo;
import com.qf.pearvideo.callback.PearStringCallBack;
import com.qf.pearvideo.dao.IPearDao;
import com.qf.pearvideo.dao.impl.PearDao;
import com.qf.pearvideo.fragment.IIndexFragment;
import com.qf.pearvideo.present.ITitleInfoPresenter;
import com.qf.pearvideo.utils.GetPhoneInfo;

/**
 * Created by Administrator on 2017/2/21.
 */

public class TitleInfoPresenter implements ITitleInfoPresenter {

    private Context context;
    private IIndexFragment mIIndexFragment;
    private SharedPreferences sp;

    public TitleInfoPresenter(Context context) {
        this.context = context;
        //this.mIIndexFragment = mIIndexFragment;
    }

    private IPearDao pearDao = new PearDao();
    private PearStringCallBack stringCallBack = new PearStringCallBack() {
        @Override
        public void doResult(String resultStr, int tag) {
            switch (tag){
                case 1:
                    //返回的Json字符串
                    break;
                case 2:
                    //返回的cookie字符串
                    sp = context.getSharedPreferences("PearVideoCookie", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("cookie", resultStr);
                    editor.commit();//提交
                    break;
            }
        }
    };

    @Override
    public void getTitleInfo(String url) {
        if (url != null){
            PhoneInfo phoneInfo =  GetPhoneInfo.getPhoneInfo(context);
            pearDao.getTitleInfo(url, phoneInfo, stringCallBack);
        }
    }
}
