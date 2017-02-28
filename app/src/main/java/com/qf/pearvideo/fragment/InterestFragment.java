package com.qf.pearvideo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.qf.pearvideo.R;
import com.qf.pearvideo.adapter.InterestFragmentAdapter;
import com.qf.pearvideo.bean.Interest;
import com.qf.pearvideo.fragment.InterFaces.IInterestFragment;
import com.qf.pearvideo.present.IInterestPresenter;
import com.qf.pearvideo.present.impl.InterestPresenter;
import com.qf.pearvideo.utils.ConnectUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * 兴趣页面
 * Created by Administrator on 2017/2/22 0022.
 */

public class InterestFragment extends Fragment implements
        IInterestFragment{
    private IInterestPresenter mIInterestPresenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Context context;
    private List<Interest> data = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private InterestFragmentAdapter adapter;
    private String nextUrl = null;
    private int lastVisibleItem;
    private LinearLayoutManager layoutManager;
    private LinearLayout interest_mFrush;//刷新
    private LinearLayout mMore;//加载

    private boolean isVisible = false;//是否加载完成，是否可见。
    private View view;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        mIInterestPresenter = new InterestPresenter(context,this);
    }

    /**
     * 加载数据
     */
    private void load() {
        mIInterestPresenter.getInterest(ConnectUrl.intestUrl);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.interest_fragment,null);
        isVisible = true;
        init(view);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            isVisible = true;
            init(view);
        }
    }

    private void init(View view) {
        data.clear();
        findView(view);
        setOnLister();
        load();
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        adapter = new InterestFragmentAdapter(context,data);
        mRecyclerView.setAdapter(adapter);
    }

    /**
     * 设置监听
     */
    private void setOnLister() {
        mSwipeRefreshLayout.isRefreshing();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                interest_mFrush.setVisibility(View.VISIBLE);
                data.clear();
                load();
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == recyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()){
                    //mSwipeRefreshLayout.setRefreshing(true);
                    mMore.setVisibility(View.VISIBLE);
                    mIInterestPresenter.getInterest(nextUrl);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    /**
     * 找控件id
     * @param view
     */
    private void findView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.mSwipeRefreshLayout);
        interest_mFrush = (LinearLayout) view.findViewById(R.id.interest_mFrush);
        mMore = (LinearLayout) view.findViewById(R.id.mMore);
    }

    @Override
    public void successInfo(List<Interest> list) {
        nextUrl = list.get(0).getNextUrl();
        data.addAll(list);
        interest_mFrush.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
        mMore.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
        Log.i("----------",nextUrl);
    }
}
