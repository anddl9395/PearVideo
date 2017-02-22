package com.qf.pearvideo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
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
    boolean flag = false;//密文输入默认为不可见的图片
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
    //监听
    private void setListener() {
        backwards.setOnClickListener(this);
        loginRegister.setOnClickListener(this);
        passwordNoSee.setOnClickListener(this);
        forgetPassword.setOnClickListener(this);
        loginLogin.setOnClickListener(this);
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
    //点击事件的处理
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backwards://后退
                finish();
                break;
            case R.id.loginRegister://进入注册页面
                EnterToRegister();
                break;
            case R.id.passwordNoSee://点击切换密文可见
                SwitchPicture();
                break;
            case R.id.forgetPassword://点击跳到忘记密码页面
                startActivity(new Intent(this,ForgetPwdActivity.class));
                break;
            case R.id.loginLogin://登录
                break;
        }
    }
    //跳转到注册界面
    private void EnterToRegister() {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    //点击切换图片
    private void SwitchPicture() {
        if(flag){
            passwordNoSee.setImageResource(R.drawable.icon_browse);
            enterPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);//设置成密文可见
            flag=false;
        }else {
            passwordNoSee.setImageResource(R.drawable.icon_passwordnosee);
            enterPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//设置成密文不可见
            flag = true;
        }
    }
}
