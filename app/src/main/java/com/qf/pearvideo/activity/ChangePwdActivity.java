package com.qf.pearvideo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.pearvideo.R;


public class ChangePwdActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView changePasswordBackwards;//返回
    private ImageView passwordNotSee;//密文显示
    private ImageView passwordNotSeeX;//密文显示
    private EditText oldPassword;//原密码
    private EditText newPassword;//新密码
    private TextView forgetPwd;//忘记密码
    private TextView finish;//完成
    boolean flag = false;//密文输入默认为不可见的图片
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        init();
    }

    private void init() {
        findView();
        setListener();
    }
    //设置监听
    private void setListener() {
        changePasswordBackwards.setOnClickListener(this);
        forgetPwd.setOnClickListener(this);
        finish.setOnClickListener(this);
        passwordNotSee.setOnClickListener(this);
        passwordNotSeeX.setOnClickListener(this);
    }
    //找控件
    private void findView() {
        oldPassword = (EditText) findViewById(R.id.oldPassword);
        newPassword = (EditText) findViewById(R.id.newPassword);
    }
    //事件处理
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.changePasswordBackwards://点击返回
                finish();
                break;
            case R.id.passwordNotSee:
                SwitchPictureOld();
                break;
            case R.id.passwordNotSeeX:
                SwitchPictureNew();
                break;
            case R.id.finish://完成
                break;
        }
    }
    //点击切换图片
    private void SwitchPictureOld() {
        if(flag){
            passwordNotSee.setImageResource(R.drawable.icon_browse);
            oldPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);//设置成密文可见
            flag=false;
        }else {
            passwordNotSee.setImageResource(R.drawable.icon_passwordnosee);
            oldPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//设置成密文不可见
            flag = true;
        }
    }
    private void SwitchPictureNew() {
        if(flag){
            passwordNotSeeX.setImageResource(R.drawable.icon_browse);
            newPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);//设置成密文可见
            flag=false;
        }else {
            passwordNotSeeX.setImageResource(R.drawable.icon_passwordnosee);
            newPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//设置成密文不可见
            flag = true;
        }
    }
}
