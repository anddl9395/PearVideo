package com.qf.pearvideo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.qf.pearvideo.R;
import com.qf.pearvideo.adapter.SubscriptionManagementUpAdapter;
import com.qf.pearvideo.bean.SubscriptionManagementUp;

import java.util.List;

/**
 * 订阅管理
 */
public class SubscriptionManagementActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView subscribeManagementBackwards;//后退
    private ImageView subscribeManagementSearch;//搜索
    private ListView subscribeScrollViewIn;//
    List <SubscriptionManagementUp> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_management);
        init();
        SubscriptionManagementUpAdapter subscriptionManagementUpAdapter =new SubscriptionManagementUpAdapter(this,data);
        subscribeScrollViewIn.setAdapter(subscriptionManagementUpAdapter);
    }
    //初始化
    private void init() {
        findVIew();
        setListener();
    }
    //设置监听
    private void setListener() {
        subscribeManagementBackwards.setOnClickListener(this);//后退
        subscribeManagementSearch.setOnClickListener(this);
        subscribeScrollViewIn.setOnClickListener(this);//listView的点击监听
    }
    //找控件
    private void findVIew() {
        subscribeManagementBackwards = (ImageView) findViewById(R.id.subscribeManagementBackwards);
        subscribeManagementSearch = (ImageView) findViewById(R.id.subscribeManagementSearch);
        subscribeScrollViewIn = (ListView) findViewById(R.id.subscribeScrollViewIn);
    }
    //事件处理
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.subscribeManagementSearch://搜索
                break;
            case R.id.subscribeManagementBackwards://返回
                finish();
                break;
            case R.id.subscribeScrollViewIn:
                break;
        }
    }
}
