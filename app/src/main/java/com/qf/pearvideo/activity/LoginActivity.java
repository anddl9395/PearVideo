package com.qf.pearvideo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.pearvideo.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView backwards;//后退图标
    TextView loginRegister;//登陆界面注册跳转
    EditText enterMessage;//输入手机和邮箱
    EditText enterPassword;//输入密码
    ImageView passwordNoSee;//密码不可见的图片
    TextView forgetPassword;//忘记密码
    TextView loginLogin;//登录界面的登录
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        findView();
        setListener();
    }

    private void setListener() {

    }

    //找控件
    private void findView() {
        backwards = (ImageView) findViewById(R.id.backwards);
        loginRegister= (TextView) findViewById(R.id.loginRegister);
        enterMessage = (EditText) findViewById(R.id.enterMessage);
        enterPassword = (EditText) findViewById(R.id.enterPassword);
        passwordNoSee = (ImageView) findViewById(R.id.passwordNoSee);
        forgetPassword = (TextView) findViewById(R.id.forgetPassword);
        loginLogin = (TextView) findViewById(R.id.loginLogin);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backwards:
                finish();
                break;
            case R.id.loginRegister:
                break;
            case R.id.passwordNoSee:
                break;
            case R.id.forgetPassword:
                break;
            case R.id.loginLogin:
                break;
        }
    }
}
