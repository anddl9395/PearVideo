package com.qf.pearvideo.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.qf.pearvideo.R;
import com.qf.pearvideo.adapter.MyMessageAdapter;
import com.qf.pearvideo.fragment.PushMessageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MyMessageActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private TabLayout messageTab;//
    private List<String> titleList;//表格布局的内容集合
    private List<Fragment> pageList = new ArrayList<>();
    private MyMessageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);
        init();
        //initPager();
        pageList.add(new PushMessageFragment());
        pageList.add(new PushMessageFragment());

        //设置适配器
        adapter = new MyMessageAdapter(getSupportFragmentManager(), pageList,titleList);
        mViewPager.setAdapter(adapter);
    }

    //实例化
    private void init() {
        //设置标题
        titleList = new ArrayList<>();
        titleList.add("系统消息");
        titleList.add("推送消息");
        messageTab = (TabLayout) findViewById(R.id.messageTab);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        //设置tab的模式
        messageTab.setTabMode(TabLayout.MODE_FIXED);
        //添加tab选项卡
        for (int i = 0; i < titleList.size(); i++) {
            messageTab.addTab(messageTab.newTab().setText(titleList.get(i)));
        }
        //把TabLayout和ViewPager关联起来
        messageTab.setupWithViewPager(mViewPager);
    }


    private void initPager(){
        PushMessageFragment pushMessageFragment = null;
        for (int i = 0; i <2 ; i++) {
            pageList.add(new PushMessageFragment());
        }
    }
}
