package com.qf.pearvideo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qf.pearvideo.R;
import com.qf.pearvideo.bean.SystemMessage;

import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public class MyMessageListViewAdapter extends BaseAdapter {
    private Context context;
    private List<SystemMessage> data;
    ViewHolder viewHolder;

    public MyMessageListViewAdapter(Context context, List<SystemMessage> data) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.index_systemmessage_item, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.systemMessageTitle);
            viewHolder.pubTime = (TextView) convertView.findViewById(R.id.systemMessagePubTime);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
       SystemMessage sm = data.get(position);
        viewHolder.title.setText(sm.getTitle());
        viewHolder.pubTime.setText(sm.getPubTime());
        return convertView;
    }
    class ViewHolder{
         TextView title;
         TextView pubTime;
    }
}
