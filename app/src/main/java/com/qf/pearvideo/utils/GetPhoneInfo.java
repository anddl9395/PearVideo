package com.qf.pearvideo.utils;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.qf.pearvideo.bean.PhoneInfo;

/**
 * Created by Administrator on 2017/2/22 0022.
 */

public class GetPhoneInfo {

    public static PhoneInfo getPhoneInfo(Context context){
        PhoneInfo phoneInfo = new PhoneInfo();
        TelephonyManager mtm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        phoneInfo.setId(mtm.getDeviceId());//设置ID
        String num = "" + Build.TIME/1000;
        phoneInfo.setNum(num);
        phoneInfo.setClientAgent(Build.MODEL);
        phoneInfo.setPlatformVersion(Build.VERSION.RELEASE);
        Log.e("======phoneInfo", phoneInfo.toString());
        return phoneInfo;
    }
}
