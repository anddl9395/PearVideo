package com.qf.pearvideo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.pearvideo.R;

public class ForgetPwdActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView forgetPasswordBackwards;//忘记密码页面的返回
    EditText forgetPasswordEnterMessage;//输入手机或邮箱
    EditText forgetPasswordEnterPassword;//输入密码
    Button forgetPasswordGetInvitationCode;//获取邀请码
    TextView forgetPasswordNextStep;//下一步
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
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
        forgetPasswordBackwards.setOnClickListener(this);
        forgetPasswordGetInvitationCode.setOnClickListener(this);
        forgetPasswordNextStep.setOnClickListener(this);
    }

    private void findView() {
        forgetPasswordBackwards = (ImageView) findViewById(R.id.forgetPasswordBackwards);
        forgetPasswordEnterMessage = (EditText) findViewById(R.id.forgetPasswordEnterMessage);
        forgetPasswordEnterPassword = (EditText) findViewById(R.id.forgetPasswordEnterPassword);
        forgetPasswordNextStep = (TextView) findViewById(R.id.forgetPasswordNextStep);
        forgetPasswordGetInvitationCode = (Button) findViewById(R.id.forgetPasswordGetInvitationCode);
    }



    //点击事件的处理
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forgetPasswordBackwards://返回上一界面
                finish();
                break;
            case R.id.forgetPasswordGetInvitationCode://获取邀请码
                break;
            case R.id.forgetPasswordNextStep://下一步
                break;
        }
    }
}
