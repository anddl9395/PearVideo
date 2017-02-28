package com.qf.pearvideo.present.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.qf.pearvideo.activity.IMyMessage;
import com.qf.pearvideo.bean.SystemMessage;
import com.qf.pearvideo.callback.PearStringCallBack;
import com.qf.pearvideo.dao.IPearDao;
import com.qf.pearvideo.dao.impl.PearDao;
import com.qf.pearvideo.present.IMyMessagep;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 */

public class MyMessage implements IMyMessagep{
    Context context;
    IMyMessage mIMyMessage;
    IPearDao mIPearDao = new PearDao();
    List<SystemMessage> list = new ArrayList<>();
    SharedPreferences sp;

    public MyMessage(Context context,IMyMessage mIMyMessage){
        this.context = context;
        this.mIMyMessage = mIMyMessage;
    }

    PearStringCallBack callBack = new PearStringCallBack() {
        @Override
        public void doResult(String result,int tag) {
            if(result != null){
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String info = jsonObject.getString("resultMsg");
                    if("success".equals(info)){
                        JSONArray msgList = jsonObject.getJSONArray("msgList");
                        int len = msgList.length();
                        SystemMessage mSystemMessage = null;
                        for (int i = 0; i < len; i++) {
                            JSONObject mes = msgList.getJSONObject(i);

                            mSystemMessage = new SystemMessage();
                            mSystemMessage.setID(mes.getString("id"));
                            mSystemMessage.setTitle(mes.getString("title"));
                            mSystemMessage.setDetail(mes.getString("detail"));
                            mSystemMessage.setPubTime(mes.getString("pubTime"));

                            list.add(mSystemMessage);
                        }
                        mIMyMessage.successResult(list);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    @Override
    public void getMyMessagep(String url) {
        sp = context.getSharedPreferences("PearVideoCookie", Context.MODE_PRIVATE);
        String cookie = sp.getString("cookie","err");
        mIPearDao.getSystemMessage(url,cookie,callBack);
    }
}
