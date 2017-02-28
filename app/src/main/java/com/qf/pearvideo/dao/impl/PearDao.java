package com.qf.pearvideo.dao.impl;

import android.os.Environment;
import android.util.Log;

import com.qf.pearvideo.bean.PhoneInfo;
import com.qf.pearvideo.callback.PearStringCallBack;
import com.qf.pearvideo.dao.IPearDao;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.http.cookie.DbCookieStore;
import org.xutils.x;

import java.io.File;
import java.net.HttpCookie;
import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 */

public class PearDao implements IPearDao {

    /**
     * 首次加载
     * @param url
     * @param phoneInfo
     * @param callBack
     */
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

    /**
     * 消息推送
     * @param url
     * @param callBack
     */
    @Override
    public void getSystemMessage(String url, String cookie, final PearStringCallBack callBack) {
        RequestParams mRequestParams = new RequestParams(url);
        mRequestParams.addHeader("Cookie",cookie);

        x.http().get(mRequestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callBack.doResult(result,1);
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

    /**
     * 获取首页信息
     * @param url
     * @param cookie
     * @param callBack
     */
    @Override
    public void getIndexMainInfo(String url, String cookie, final PearStringCallBack callBack) {
        RequestParams mRequestParams = new RequestParams(url);
        mRequestParams.addHeader("Cookie",cookie);

        x.http().get(mRequestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callBack.doResult(result,1);
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

    /**
     * 兴趣页面信息
     * @param url
     * @param cookie
     * @param callBack
     */
    @Override
    public void getInterestInfo(String url, String cookie, final PearStringCallBack callBack) {
        RequestParams requestParams = new RequestParams(url);
        requestParams.addHeader("Cookie",cookie);

        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if(result != null){
                    callBack.doResult(result,1);
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

    /**
     * 获取首页内容
     * @param url
     * @param cookie
     * @param callBack
     */
    @Override
    public void getIndexMainInfo(String url, String cookie, final PearStringCallBack callBack) {
        RequestParams requestParams = new RequestParams(url);
        requestParams.addHeader("Cookie", cookie);

        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null){
                    callBack.doResult(result, 1);
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

    /**
     * 下载首页的短视频
     * @param url
     * @param callBack
     */
    @Override
    public void getIndexMainFile(String url, final PearStringCallBack callBack) {

        String videoPath = Environment.getExternalStorageDirectory().getPath() + File.separator + "PearVideo";
        //判断文件夹是否存在，不存在则创建文件夹
        File file = new File(videoPath);
        if (!file.exists()){
            file.mkdirs();
        }

        String savePath = videoPath + File.separator + getFileName(url);
        RequestParams requestParams = new RequestParams(url);
        requestParams.setSaveFilePath(savePath);    //设置保存路径

        x.http().get(requestParams, new Callback.CommonCallback<File>() {
            @Override
            public void onSuccess(File result) {
                //下载成功
                callBack.doResult(result.getAbsolutePath(), 2);
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


    /**
     * 订阅管理的左侧的标签
     * @param url
     *
     */
    @Override
    public void getSubscriptionTitle(String url, String cookie, final CallBack mCallBack) {
        RequestParams mRequestParams = new RequestParams(url);
        mRequestParams.addHeader("Cookie",cookie);
        x.http().get(mRequestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                mCallBack.getResult(result);
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

    /**
     * 获取url内文件名称
     * @param urlPath
     * @return
     */
    private String getFileName(String urlPath){
        String[] str = urlPath.split("/");
        return str[str.length - 1];
    }
}
