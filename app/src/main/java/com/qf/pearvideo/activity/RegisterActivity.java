package com.qf.pearvideo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.pearvideo.R;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView registerBackwards;//后退
    TextView registerLogin;//跳到登录界面
    TextView registerEnterMessage;//输入手机或邮箱
    TextView registerEnterPassword;//输入密码
    Button getInvitationCode;//获取邀请码
    TextView nextStep;//下一步
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

    }

    /**
     * 初始化
     */
    private void init() {
        findView();
        setOnListener();
    }
    //设置监听
    private void setOnListener() {
        registerBackwards.setOnClickListener(this);
        registerLogin.setOnClickListener(this);
        getInvitationCode.setOnClickListener(this);
        nextStep.setOnClickListener(this);
    }

    private void findView() {
        registerBackwards = (ImageView) findViewById(R.id.registerBackwards);
        registerLogin = (TextView) findViewById(R.id.registerLogin);
        registerEnterMessage = (TextView) findViewById(R.id.registerEnterMessage);
        registerEnterPassword = (TextView) findViewById(R.id.registerEnterPassword);
        nextStep = (TextView) findViewById(R.id.nextStep);
        getInvitationCode = (Button) findViewById(R.id.getInvitationCode);
    }
    //点击事件的处理
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerBackwards://返回上一界面
                finish();
                break;
            case R.id.registerLogin://返回登录界面
                finish();
                break;
            case R.id.getInvitationCode://获取邀请码
                break;
            case R.id.nextStep://下一步
                break;
        }
    }
}
