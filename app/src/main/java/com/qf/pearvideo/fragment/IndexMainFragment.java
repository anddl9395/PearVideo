package com.qf.pearvideo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qf.pearvideo.R;
import com.qf.pearvideo.bean.Node;
import com.qf.pearvideo.fragment.InterFaces.IIndexMainFragment;
import com.qf.pearvideo.present.IIndexMainInfoPresenter;
import com.qf.pearvideo.present.impl.IndexMainInfoPresenter;
import com.qf.pearvideo.utils.ConnectUrl;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */

public class IndexMainFragment extends Fragment implements IIndexMainFragment {

    private ViewPager viewPager;            //首页大标题视频展示
    private PagerTabStrip tabStrip;         //页面显示条
    private TextView tvTag;                 //推荐等标记
    private TextView tvNameAndTime, tvTitle;//时间和名称的文本框， 以及标题文本框
    private RecyclerView recyclerView;      //展示不同小模块的控件
    private Context context;

    private IIndexMainInfoPresenter mIIndexMainInfoPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        mIIndexMainInfoPresenter = new IndexMainInfoPresenter(context, this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //进行数据请求
        mIIndexMainInfoPresenter.getMainInfo(ConnectUrl.indexInfoUrl);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index_main_layout, null);
        init(view);
        return view;
    }

    private void init(View view) {
        findView(view);
    }

    private void findView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.index_main_vp);
        tabStrip = (PagerTabStrip) view.findViewById(R.id.index_main_pts);
        tvTag = (TextView) view.findViewById(R.id.index_main_tag);
        tvNameAndTime = (TextView) view.findViewById(R.id.index_main_NameAndTime);
        tvTitle = (TextView) view.findViewById(R.id.index_main_title);
        recyclerView = (RecyclerView) view.findViewById(R.id.index_main_recycler);
    }

    //得到数据返回
    @Override
    public void getNodeList(List<Node> list) {

    }
}
