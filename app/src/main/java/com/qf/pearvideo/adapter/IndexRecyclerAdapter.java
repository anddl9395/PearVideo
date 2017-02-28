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
import com.qf.pearvideo.view.FullGridViewLayoutManager;
import com.qf.pearvideo.view.FullyLinearLayoutManager;

import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 * 总的recyclerView的适配器
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
        //给item设置数据
        int  type = getItemViewType(position);
        Node node = nodeList.get(position);
        switch (type){
            case TYPE_1://横屏布局
                //设置标题
                holder.tv_title.setText(node.getNodeName());
                //给recyclerView设置适配器
                FullyLinearLayoutManager fullyLinearLayoutManager = new FullyLinearLayoutManager(context, FullyLinearLayoutManager.HORIZONTAL, false);
                holder.recyclerView.setLayoutManager(fullyLinearLayoutManager);
                IndexItemRecyclerAdapter myAdapter = new IndexItemRecyclerAdapter(context, node.getContList(), IndexItemRecyclerAdapter.HORIZONTAL_TYPE);
                holder.recyclerView.setAdapter(myAdapter);
                //给标题上的那一栏设置点击事件
                //待写
                break;
            case TYPE_2: //纵向布局
                //设置标题
                holder.tv_title.setText(node.getNodeName());
                //给recyclerView设置适配器
                FullGridViewLayoutManager fullGridViewLayoutManager = new FullGridViewLayoutManager(context, 2);
                holder.recyclerView.setLayoutManager(fullGridViewLayoutManager);
                IndexItemRecyclerAdapter myAdapter1 = new IndexItemRecyclerAdapter(context, node.getContList(), IndexItemRecyclerAdapter.VERTICAL_TYPE);
                holder.recyclerView.setAdapter(myAdapter1);
                //给标题栏设置点击事件
                //待写
                //底部刷新栏
                if (node.getContList().size() < 6){
                    holder.rlRefresh.setVisibility(View.GONE);//隐藏
                }else {
                    //设置刷新事件
                    //代写
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return nodeList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (nodeList.get(position).getNodeName().equals("最新视频")){
            return TYPE_1;
        }else {
            return TYPE_2;
        }
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
