package com.qf.pearvideo.application;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2017/2/22.
 */

public class PVApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
