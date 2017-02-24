package com.qf.pearvideo.dao.impl;

import android.util.Log;

import com.qf.pearvideo.bean.PhoneInfo;
import com.qf.pearvideo.callback.PearStringCallBack;
import com.qf.pearvideo.dao.IPearDao;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.http.cookie.DbCookieStore;
import org.xutils.x;

import java.net.HttpCookie;
import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 */

public class PearDao implements IPearDao {

    @Override
    public void getTitleInfo(String url, PhoneInfo phoneInfo, final PearStringCallBack callBack) {
        RequestParams requestParams = new RequestParams(url);
        requestParams.addHeader(phoneInfo.getIdName(), phoneInfo.getId());
        requestParams.addHeader(phoneInfo.getNumName(), phoneInfo.getNum());
        requestParams.addHeader(phoneInfo.getPlatformVersionName(), phoneInfo.getPlatformVersion());
        requestParams.addHeader(phoneInfo.getClientVersionName(), phoneInfo.getClientAgent());
        requestParams.addHeader(phoneInfo.getClientAgentName(), phoneInfo.getClientAgent());
        requestParams.addHeader(phoneInfo.getPlatformTypeName(), phoneInfo.getPlatformType());

        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null){
                    Log.e("=======", result);
                    callBack.doResult(result, 1);//将Json字符串返回
                    DbCookieStore instance = DbCookieStore.INSTANCE;
                    List<HttpCookie> cookies = instance.getCookies();
                    StringBuffer stringBuffer = new StringBuffer();
                    for(HttpCookie cookie : cookies){
                        stringBuffer.append(cookie.getName()+":"+cookie.getValue()+";");
                    }
                    callBack.doResult(stringBuffer.toString(), 2);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
