package com.qf.pearvideo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.qf.pearvideo.R;

/**
 * Created by Administrator on 2017/2/23.
 */

public class PushMessageFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.systemmessage_fragment,null);
        ListView myListView = (ListView) view.findViewById(R.id.myListView);
        return view;
    }
}
