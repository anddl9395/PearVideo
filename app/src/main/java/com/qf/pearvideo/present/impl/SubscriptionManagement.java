package com.qf.pearvideo.present.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.qf.pearvideo.activity.ISubscriptionManagementActivity;
import com.qf.pearvideo.bean.SubscriptionManagementUp;
import com.qf.pearvideo.callback.CallBack;
import com.qf.pearvideo.dao.IPearDao;
import com.qf.pearvideo.dao.impl.PearDao;
import com.qf.pearvideo.present.ISubscriptionManagement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 得到解析后的集合
 * Created by Administrator on 2017/2/27.
 */

public class SubscriptionManagement implements ISubscriptionManagement {
    private Context context;
    private List<SubscriptionManagementUp> list = new ArrayList<>();
    private SharedPreferences sp;
    private IPearDao mIPearDao = new PearDao();
    private ISubscriptionManagementActivity ISMA;

    public SubscriptionManagement(Context context,ISubscriptionManagementActivity ISMA) {
        this.context = context;
        this.ISMA = ISMA;
    }
    CallBack mCallBack= new CallBack() {
        @Override
        public void getResult(String result) {
            if (result!=null){
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);
                    String tag = jsonObject.getString("resultMsg");
                    if("success".equals(tag)){
                        JSONArray msgList = jsonObject.getJSONArray("domainList");
                        int len = msgList.length();
                        SubscriptionManagementUp SMP = null;
                        for (int i = 0; i <len ; i++) {
                            JSONObject mes = msgList.getJSONObject(i);
                            SMP=new SubscriptionManagementUp();
                            SMP.setName(mes.getString("name"));
                            SMP.setDomainId(mes.getString("domainId"));
                            list.add(SMP);
                        }
                        ISMA.successInfo(list);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    };

    @Override
    public void getSubscriptionManagement(String url) {
        //sp = context.getSharedPreferences("PearVideoCookie", Context.MODE_PRIVATE);
       // String cookie = sp.getString("cookie","err");
        String cookie = "PEAR_PLATFORM=2;PEAR_UUID=867931028815395;JSESSIONID=D22C82CAF8708FF10D96A83839390A48;PEAR_TOKEN=94e758a5-a1c7-4386-9902-759602e140e7;PV_APP=srv-pv-prod-portal1;__ads_session=eYIG5TlN3QjjHfYuOgA=";
        mIPearDao.getSubscriptionTitle(url,cookie,mCallBack);
    }
}
