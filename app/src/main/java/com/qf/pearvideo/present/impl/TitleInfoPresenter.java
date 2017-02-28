package com.qf.pearvideo.present.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.qf.pearvideo.bean.Category;
import com.qf.pearvideo.bean.PhoneInfo;
import com.qf.pearvideo.callback.PearStringCallBack;
import com.qf.pearvideo.dao.IPearDao;
import com.qf.pearvideo.dao.impl.PearDao;
import com.qf.pearvideo.fragment.InterFaces.IIndexFragment;
import com.qf.pearvideo.present.ITitleInfoPresenter;
import com.qf.pearvideo.utils.GetPhoneInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 */

public class TitleInfoPresenter implements ITitleInfoPresenter {

    private Context context;
    private IIndexFragment mIIndexFragment;
    private SharedPreferences sp;

    public TitleInfoPresenter(Context context, IIndexFragment mIIndexFragment) {
        this.context = context;
        this.mIIndexFragment = mIIndexFragment;
    }

    private IPearDao pearDao = new PearDao();
    private PearStringCallBack stringCallBack = new PearStringCallBack() {
        @Override
        public void doResult(String resultStr, int tag) {
            switch (tag){
                case 1:
                    //返回的Json字符串
                    try {
                        //解析JSON
                        JSONObject jsonObject = new JSONObject(resultStr);
                        int tagMsg = jsonObject.getInt("resultCode");//查看返回的标记
                        if (tagMsg != 1){
                            //得到的数据出错
                        }else {
                            //数据正确，进行解析
                            JSONArray array = jsonObject.getJSONArray("categoryList");
                            List<Category> lists = new ArrayList<>();
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObj = array.getJSONObject(i);
                                int id = jsonObj.getInt("categoryId");
                                String name = jsonObj.getString("name");
                                String color = jsonObj.getString("color");
                                Category ca = new Category(id, name, color);
                                lists.add(ca);
                            }
                            //将数据返回
                            mIIndexFragment.getCategoryList(lists);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
