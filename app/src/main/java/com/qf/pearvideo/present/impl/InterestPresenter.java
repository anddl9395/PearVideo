package com.qf.pearvideo.present.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.qf.pearvideo.bean.Interest;
import com.qf.pearvideo.callback.PearStringCallBack;
import com.qf.pearvideo.dao.IPearDao;
import com.qf.pearvideo.dao.impl.PearDao;
import com.qf.pearvideo.fragment.InterFaces.IInterestFragment;
import com.qf.pearvideo.present.IInterestPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class InterestPresenter implements IInterestPresenter {
    IInterestFragment mIInterestFragment;
    Context context;
    IPearDao mIPearDao = new PearDao();
    SharedPreferences sp;
    Interest interest;

    public InterestPresenter(Context context,IInterestFragment mIInterestFragment){
        this.context = context;
        this.mIInterestFragment = mIInterestFragment;
    }

    PearStringCallBack callBack = new PearStringCallBack() {
        @Override
        public void doResult(String resultStr, int tag) {
            if(resultStr != null){
                try {
                    JSONObject jsonObject = new JSONObject(resultStr);
                    String resultMsg = jsonObject.getString("resultMsg");
                    if("success".equals(resultMsg)){
                        List<Interest> list = new ArrayList<>();


                        JSONArray jsonArray = jsonObject.getJSONArray("contList");
                        int len = jsonArray.length();
                        for (int i = 0; i < len; i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            interest = new Interest();

                            interest.setNextUrl(jsonObject.getString("nextUrl"));
                            interest.setPic(data.getString("pic"));
                            interest.setName(data.getString("name"));
                            interest.setDuration(data.getString("duration"));
                            interest.setSharePic(data.getString("sharePic"));
                            interest.setShareUrl(data.getString("shareUrl"));
                            JSONArray array = data.getJSONArray("videos");
                            for (int j = 0; j < array.length(); j++) {
                                JSONObject url = array.getJSONObject(0);
                                interest.setShareUrl(url.getString("url"));
                            }

                            JSONObject nodeInfo = data.getJSONObject("nodeInfo");
                            interest.setLogoname(nodeInfo.getString("name"));
                            interest.setLogoimg(nodeInfo.getString("logoImg"));

                            list.add(interest);
                        }
                        mIInterestFragment.successInfo(list);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    public void getInterest(String url) {
        sp = context.getSharedPreferences("PearVideoCookie", Context.MODE_PRIVATE);
        String cookie = sp.getString("Cookie","err");
        mIPearDao.getInterestInfo(url,cookie,callBack);
    }
}
