package com.qf.pearvideo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qf.pearvideo.R;
import com.qf.pearvideo.bean.Node;

import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 */

public class IndexRecyclerAdapter extends RecyclerView.Adapter<IndexRecyclerAdapter.MyViewHolder> {

    List<Node> nodeList;
    Context context;
    LayoutInflater inflater;
    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;

    public IndexRecyclerAdapter(List<Node> nodeList, Context context) {
        this.nodeList = nodeList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType){
            case TYPE_1:
                view = inflater.inflate(R.layout.index_recyclerview_item_h, null);
                break;
            case TYPE_2:
                view = inflater.inflate(R.layout.index_recyclerview_item_v, null);
                break;
        }
        return new MyViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return nodeList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        int viewType;
        TextView tv_title;
        RecyclerView recyclerView;
        RelativeLayout rlTitle, rlRefresh;

        public MyViewHolder(View itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;
            switch (viewType){
                case TYPE_1:
                    //横屏布局
                    tv_title = (TextView) itemView.findViewById(R.id.index_recyclerh_title);
                    recyclerView = (RecyclerView) itemView.findViewById(R.id.index_recyclerh_rv);
                    rlTitle = (RelativeLayout) itemView.findViewById(R.id.index_recyclerh_rl);
                    break;
                case TYPE_2:
                    //纵向布局
                    tv_title = (TextView) itemView.findViewById(R.id.index_recycler_title);
                    recyclerView = (RecyclerView) itemView.findViewById(R.id.index_recycler_rv);
                    rlTitle = (RelativeLayout) itemView.findViewById(R.id.index_recycler_rl);
                    rlRefresh = (RelativeLayout) itemView.findViewById(R.id.index_recycler_new);
                    break;
            }
        }
    }
}
