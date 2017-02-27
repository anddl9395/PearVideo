package com.qf.pearvideo.present.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.qf.pearvideo.bean.SystemMessage;
import com.qf.pearvideo.callback.CallBack;
import com.qf.pearvideo.dao.IPearDao;
import com.qf.pearvideo.dao.impl.PearDao;
import com.qf.pearvideo.fragment.IPushMessageFragment;
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
   private Context context;
    private IPushMessageFragment mIPushMessageFragment;
    private IPearDao mIPearDao = new PearDao();
    private List<SystemMessage> list = new ArrayList<>();
    private SharedPreferences sp;

    public MyMessage(Context context,IPushMessageFragment mIPushMessageFragment){
        this.context = context;
        this.mIPushMessageFragment = mIPushMessageFragment;
    }

    CallBack mCallBack = new CallBack() {
        @Override
        public void getResult(String result) {
            if(result != null){
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String tag = jsonObject.getString("resultMsg");
                    if("success".equals(tag)){
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
                        mIPushMessageFragment.successInfo(list);
                    }
                    else {
                        mIPushMessageFragment.failInfo();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }
    };
    @Override
    public void getMyMessagep(String url) {
        //sp = context.getSharedPreferences("PearVideoCookie", Context.MODE_PRIVATE);
        //String cookie = sp.getString("cookie","err");
        String cookie = "PEAR_PLATFORM=2;PEAR_UUID=867931028815395;JSESSIONID=E7A03EDFE4438F466ED722E381FC35C1;PV_APP=srv-pv-prod-portal3;__ads_session=4XyFTnww3Qj/OG4uCQA=";
        mIPearDao.getSystemMessage(url,cookie,mCallBack);
    }
}
