package com.qf.pearvideo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.qf.pearvideo.R;


public class SubscriptionActivity extends AppCompatActivity {
    private TextView title;//actionbar上的标题
    private TextView right;//actionbar上的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        init();
    }
    /**
     * 初始化操作
     */
    private void init() {
        findView();
        setProperty();
    }
    /**
     * 找控件
     */
    private void findView() {
        title = (TextView) findViewById(R.id.action_title);
        right= (TextView) findViewById(R.id.action_right);
    }
    /**
     * 设置属性
     */
    private void setProperty(){
        title.setText("订阅");
        right.setText("+");
        right.setTextSize(25);
    }
}
