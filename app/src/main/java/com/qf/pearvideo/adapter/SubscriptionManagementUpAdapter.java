package com.qf.pearvideo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qf.pearvideo.R;
import com.qf.pearvideo.bean.SubscriptionManagementUp;

import java.util.List;

/**
 * 订阅管理左侧ListView的适配器
 * Created by Administrator on 2017/2/27.
 */

public class SubscriptionManagementUpAdapter extends BaseAdapter {
    private Context context;
    private List<SubscriptionManagementUp> data;
    ViewHolder viewHolder;

    public SubscriptionManagementUpAdapter(Context context, List<SubscriptionManagementUp> data) {
        super();
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.subscription_management_item, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.subscriptionManagementItem);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SubscriptionManagementUp sm=data.get(position);
        viewHolder.name.setText(sm.getName());
        return convertView;
    }
    class ViewHolder{
        TextView name;
    }
}
