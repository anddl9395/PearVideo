package com.qf.pearvideo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qf.pearvideo.R;

/**
 * 启动时的页面
 * 2017/2/17
 * dengliang
 */
public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    //跳转事件....

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
