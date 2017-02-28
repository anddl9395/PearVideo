package com.qf.pearvideo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qf.pearvideo.R;
import com.qf.pearvideo.view.CircleImageView;

/**
 * Created by Administrator on 2017/2/28.
 */

public class SubscriptionManagementRecyclerViewAdapter extends RecyclerView.Adapter
        <SubscriptionManagementRecyclerViewAdapter.MyViewHolder> {
    private Context context;

    public SubscriptionManagementRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public SubscriptionManagementRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.subscription_managment_recyclerview_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(SubscriptionManagementRecyclerViewAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;//标题
        TextView desc;//内容
        CircleImageView logoImg;//头像的地址

        public MyViewHolder(View view)
        {
            super(view);
            name = (TextView) view.findViewById(R.id.subscribeManagementTitle);
            desc = (TextView) view.findViewById(R.id.subscribeManagementContent);
            logoImg = (CircleImageView) view.findViewById(R.id.subscribeManagementIcon);
        }
}
}
