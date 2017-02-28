package com.qf.pearvideo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.qf.pearvideo.R;
import com.qf.pearvideo.adapter.SubscriptionManagementUpAdapter;
import com.qf.pearvideo.bean.SubscriptionManagementUp;
import com.qf.pearvideo.fragment.SubscriptionManagmentFragment;
import com.qf.pearvideo.present.ISubscriptionManagement;
import com.qf.pearvideo.present.impl.SubscriptionManagement;
import com.qf.pearvideo.utils.ConnectUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * 订阅管理
 */
public class SubscriptionManagementActivity extends AppCompatActivity implements
        View.OnClickListener,AdapterView.OnItemClickListener,ISubscriptionManagementActivity{
    private ImageView subscribeManagementBackwards;//后退
    private ImageView subscribeManagementSearch;//搜索
    private ListView subscribeScrollViewIn;//
    List <SubscriptionManagementUp> data = new ArrayList<>();
    ISubscriptionManagement mISubscriptionManagement;
    SubscriptionManagementUpAdapter adapter;
    FrameLayout subscriptionFrameLayout;
    private FragmentManager manager;
    private List<SubscriptionManagmentFragment> pagerList= new ArrayList<>();//碎片的集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_management);
        init();
        //设置适配器
        adapter =new SubscriptionManagementUpAdapter(this,data);
        subscribeScrollViewIn.setAdapter(adapter);

    }
    //初始化
    private void init() {
        findVIew();
        setListener();
        mISubscriptionManagement=new SubscriptionManagement(this,this);
        mISubscriptionManagement.getSubscriptionManagement(ConnectUrl.subscriptionElseUrl);
        //将FrameLayout替换成Fragment
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.subscriptionFrameLayout, new SubscriptionManagmentFragment());
        transaction.commit();
        /*//给每个ListView动态添加一个Fragment
        for (int i = 0; i <data.size() ; i++) {
            pagerList.add(new SubscriptionManagmentFragment());
        }*/
    }
    //设置监听
    private void setListener() {
        subscribeManagementBackwards.setOnClickListener(this);//后退
        subscribeManagementSearch.setOnClickListener(this);
        subscribeScrollViewIn.setOnItemClickListener(this);//listView的点击监听
    }
    //找控件
    private void findVIew() {
        subscribeManagementBackwards = (ImageView) findViewById(R.id.subscribeManagementBackwards);
        subscribeManagementSearch = (ImageView) findViewById(R.id.subscribeManagementSearch);
        subscribeScrollViewIn = (ListView) findViewById(R.id.subscribeScrollViewIn);
        subscriptionFrameLayout = (FrameLayout) findViewById(R.id.subscriptionFrameLayout);
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
            case R.id.subscribeScrollViewIn://listView
                break;
            case R.id.subscriptionFrameLayout://用来占位的帧布局
                break;
        }
    }
    //左侧listView的点击事件处理
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void successInfo(List<SubscriptionManagementUp> list) {
        data.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
