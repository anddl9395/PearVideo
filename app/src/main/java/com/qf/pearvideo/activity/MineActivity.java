package com.qf.pearvideo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.qf.pearvideo.R;


public class MineActivity extends AppCompatActivity {
    TextView myNickName;//用户名
    TextView mySubscription;//我的订阅
    TextView browsingHistory;//浏览历史
    TextView videoRebellion;//视频报料
    TextView myMessage;//我的消息
    TextView myCollection;//我的收藏
    TextView changePassword;//修改密码
    TextView myInterest;//我的兴趣
    TextView quit;//退出登录
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        init();
    }

    private void init() {
        findView();
    }

    private void mineClick(View v) {
        switch (v.getId()){
            case R.id.myCollection://我的收藏
                break;
            case R.id.mySubscription://我的订阅
                break;
            case R.id.browsingHistory://浏览历史
                break;
            case R.id.videoRebellion://视频报料
                break;
            case R.id.myInterest://我的兴趣
                break;
            case R.id.myMessage://我的消息
                break;
            case R.id.changePassword://修改密码
                break;
            case R.id.quit://退出登录
                break;
        }
    }

    //找控件
    private void findView() {
        myNickName = (TextView) findViewById(R.id.myNickName);
    }

}
