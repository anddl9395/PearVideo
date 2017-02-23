package com.qf.pearvideo.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.qf.pearvideo.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MyMessageActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout messageTab;
    private List<String> titleList;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_my_message);
        init();
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
}
