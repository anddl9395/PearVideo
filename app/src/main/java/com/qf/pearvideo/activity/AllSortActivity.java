package com.qf.pearvideo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qf.pearvideo.R;
import com.qf.pearvideo.fragment.IndexFragment;
import com.qf.pearvideo.fragment.InterestFragment;
import com.qf.pearvideo.fragment.MineFragment;
import com.qf.pearvideo.fragment.SubscriptionFragment;

public class AllSortActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener{
    private RadioGroup rg;
    private RadioButton rb_index;//首页的按钮
    private IndexFragment indexFragment;//首页碎片
    private InterestFragment interestFragment;//兴趣页面碎片
    private SubscriptionFragment subscriptionFragment;//订阅页面碎片
    private MineFragment mineFragment;//“我”的页面碎片

    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_sort);

        init();
    }
    /**
     * 初始化
     */
    private void init() {
        findView();
        rb_index.setChecked(true);//设置第一个碎片被选中
        setListener();

        //创建碎片对象
        indexFragment = new IndexFragment();
        interestFragment = new InterestFragment();
        subscriptionFragment = new SubscriptionFragment();
        mineFragment = new MineFragment();

        //默认加载第一个碎片
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.replaceId, new IndexFragment());
        transaction.commit();
    }
    /**
     * 设置监听
     */
    private void setListener() {
        rg.setOnCheckedChangeListener(this);
    }
    /**
     * 找控件
     */
    private void findView() {
        rg = (RadioGroup) findViewById(R.id.rg);
        rb_index = (RadioButton) findViewById(R.id.rb_index);
    }
    /**
     * 事件处理
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_index://加载首页
                transaction = manager.beginTransaction();
                transaction.replace(R.id.replaceId, indexFragment);
                transaction.commit();
                break;
            case R.id.rb_interest://加载兴趣页面
                transaction = manager.beginTransaction();
                transaction.replace(R.id.replaceId, interestFragment);
                transaction.commit();
                break;
            case R.id.rb_sub://加载订阅页面
                transaction = manager.beginTransaction();
                transaction.replace(R.id.replaceId, subscriptionFragment);
                transaction.commit();
                break;
            case R.id.rb_me://加载“我”的页面
                transaction = manager.beginTransaction();
                transaction.replace(R.id.replaceId, mineFragment);
                transaction.commit();
                break;
        }
    }
}
