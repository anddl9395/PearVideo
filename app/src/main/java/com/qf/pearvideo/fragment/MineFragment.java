package com.qf.pearvideo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qf.pearvideo.R;

/**
 * Created by Administrator on 2017/2/17.
 */

public class MineFragment extends Fragment{
    TextView myNickName;//用户名
    TextView mySubscription;//我的订阅
    TextView browsingHistory;//浏览历史
    TextView videoRebellion;//视频报料
    TextView myMessage;//我的消息
    TextView myCollection;//我的收藏
    TextView changePassword;//修改密码
    TextView myInterest;//我的兴趣
    TextView quit;//退出登录
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.activity_mine,null);
        init(view);
        return view;
    }

    private void init(View view) {
        findView(view);
        setOnListener();
    }
    //绑定监听
    private void setOnListener() {
        MyClickListener myListener  = new MyClickListener();
        myNickName.setOnClickListener(myListener);
        mySubscription.setOnClickListener(myListener);
        browsingHistory.setOnClickListener(myListener);
        videoRebellion.setOnClickListener(myListener);
        myMessage.setOnClickListener(myListener);
        myCollection.setOnClickListener(myListener);
        changePassword.setOnClickListener(myListener);
        myInterest.setOnClickListener(myListener);
        quit.setOnClickListener(myListener);
    }

    private void findView(View view) {
        myNickName = (TextView) view.findViewById(R.id.myNickName);
        mySubscription = (TextView) view.findViewById(R.id.mySubscription);
        browsingHistory = (TextView) view.findViewById(R.id.browsingHistory);
        videoRebellion = (TextView) view.findViewById(R.id.videoRebellion);
        myMessage = (TextView) view.findViewById(R.id.myMessage);
        myCollection = (TextView) view.findViewById(R.id.myCollection);
        changePassword = (TextView) view.findViewById(R.id.changePassword);
        myInterest = (TextView) view.findViewById(R.id.myInterest);
        quit = (TextView) view.findViewById(R.id.quit);
    }

    private class MyClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
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
}
}
