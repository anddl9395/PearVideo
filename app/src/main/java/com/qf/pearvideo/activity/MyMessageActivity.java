package com.qf.pearvideo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.qf.pearvideo.R;
import com.qf.pearvideo.bean.SystemMessage;
import com.qf.pearvideo.present.IMyMessagep;
import com.qf.pearvideo.present.impl.MyMessage;
import com.qf.pearvideo.utils.ConnectUrl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MyMessageActivity extends AppCompatActivity implements IMyMessage{
    private ViewPager mViewPager;
    private TabLayout messageTab;
    private List<String> titleList;
    IMyMessagep mIMyMessagep;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);
        init();
        mIMyMessagep = new MyMessage(this,this);
        mIMyMessagep.getMyMessagep(ConnectUrl.messageUrl);
    }

    //实例化
    private void init() {
        //设置标题
        titleList = new ArrayList<>();
        titleList.add("系统消息");
        titleList.add("推送消息");
        messageTab = (TabLayout) findViewById(R.id.messageTab);
        //设置tab的模式
        messageTab.setTabMode(TabLayout.MODE_FIXED);
        //添加tab选项卡
        for (int i = 0; i < titleList.size(); i++) {
            messageTab.addTab(messageTab.newTab().setText(titleList.get(i)));
        }
        //把TabLayout和ViewPager关联起来
        messageTab.setupWithViewPager(mViewPager);
    }

    @Override
    public void successResult(List<SystemMessage> list) {
        Log.i("----",list.toString());
    }
}
