package com.qf.pearvideo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.pearvideo.R;

/**
 * 浏览历史的界面
 */
public class BrowsingHistoryActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView management;//管理图标
    private ImageView browsingHistoryBackwards;//后退图标
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browsing_history);
        init();
    }
    //初始化
    private void init() {
        findView();
        setListener();
    }
    //设置监听
    private void setListener(){
        management.setOnClickListener(this);
        browsingHistoryBackwards.setOnClickListener(this);
    }
    //找控件
    private void findView() {
        management = (TextView) findViewById(R.id.management);
        browsingHistoryBackwards = (ImageView) findViewById(R.id.browsingHistoryBackwards);
    }
    //事件处理
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.management://管理
                break;
            case R.id.browsingHistoryBackwards://返回
                finish();
                break;
        }
    }
}
