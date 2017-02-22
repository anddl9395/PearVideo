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
 * 订阅页面碎片
 * Created by Administrator on 2017/2/22 0022.
 */

public class SubscriptionFragment extends Fragment{
    private TextView title;//actionbar上的标题
    private TextView right;//actionbar上的

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.subscription_fragment,null);
        title = (TextView) view.findViewById(R.id.action_title);
        right= (TextView) view.findViewById(R.id.action_right);
        init();

        return view;
    }
    /**
     * 初始化操作
     */
    private void init() {
        setProperty();
    }
    /**
     * 设置属性
     */
    private void setProperty(){
        title.setText("订阅");
        right.setText("更多");
    }
}
