package com.qf.pearvideo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.qf.pearvideo.R;
import com.qf.pearvideo.adapter.MyMessageListViewAdapter;
import com.qf.pearvideo.bean.SystemMessage;
import com.qf.pearvideo.present.IMyMessagep;
import com.qf.pearvideo.present.impl.MyMessage;
import com.qf.pearvideo.utils.ConnectUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 */

public class PushMessageFragment extends Fragment implements IPushMessageFragment{
    ListView myListView;
    IMyMessagep mIMyMessagep;
    Context context;
    MyMessageListViewAdapter adapter;
    List<SystemMessage> data = new ArrayList<>();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        mIMyMessagep = new MyMessage(context,this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        load();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.systemmessage_fragment,null);
        init(view);
        adapter = new MyMessageListViewAdapter(context,data);
        myListView.setAdapter(adapter);
        Log.i("-----====","wozhixiusfujshguisdg");
        adapter.notifyDataSetChanged();
        return view;
    }

    private void init(View view) {
        myListView = (ListView) view.findViewById(R.id.myListView);
    }

    private void load(){
        mIMyMessagep.getMyMessagep(ConnectUrl.messageUrl);
    }
    @Override
    public void successInfo(List<SystemMessage> list) {
        data.addAll(list);
    }

    @Override
    public void failInfo() {
        Toast.makeText(context,"没有获取到数据",Toast.LENGTH_LONG).show();
    }
}
