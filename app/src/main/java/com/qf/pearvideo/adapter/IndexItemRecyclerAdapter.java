package com.qf.pearvideo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.pearvideo.R;
import com.qf.pearvideo.bean.Cont;
import com.qf.pearvideo.bean.NodeInfo;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 * recyclerView中Item中recyclerView的适配器
 */

public class IndexItemRecyclerAdapter extends RecyclerView.Adapter<IndexItemRecyclerAdapter.ViewHolder>{

    List<Cont> contList;
    Context context;
    LayoutInflater inflater;
    int type;

    public static final int VERTICAL_TYPE = 1;
    public static final int HORIZONTAL_TYPE = 2;

    public IndexItemRecyclerAdapter(Context context, List<Cont> contList, int type) {
        this.context = context;
        this.contList = contList;
        inflater = LayoutInflater.from(context);
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (type == VERTICAL_TYPE){
            view = inflater.inflate(R.layout.index_content_item, null);
        }else if(type == HORIZONTAL_TYPE){
            view = inflater.inflate(R.layout.index_title_item, null);
        }
        return new ViewHolder(view, type);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cont cont = contList.get(position);
        //设置标题
        holder.tv_title.setText(cont.getContName());
        //设置标记
        if (cont.getLabel() == null || cont.getLabel() == ""){//如果没有推荐，首播等标签
            holder.tv_tag.setVisibility(View.GONE);//隐藏
        }else {
            holder.tv_tag.setText(cont.getLabel());
        }

        //设置作者和时长
        NodeInfo nodeInfo = cont.getNodeInfo();
        StringBuffer authorAndTime = new StringBuffer();
        authorAndTime.append(nodeInfo.getNodeName() + "|");
        authorAndTime.append(cont.getDuration());
        holder.tv_nameAndTime.setText(authorAndTime.toString());

        //设置图片
        ImageOptions options = new ImageOptions.Builder()
                .setLoadingDrawableId(R.mipmap.icon_load)
                .setFailureDrawableId(R.mipmap.icon_loadfailed)
                .setUseMemCache(true)
                .build();
        x.image().bind(holder.mImageView, cont.getPic(), options);

        //给item设置点击事件
        //待实现
    }

    @Override
    public int getItemCount() {
        return contList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView mImageView;   //图片
        TextView tv_title;      //标题
        TextView tv_nameAndTime;//时间和名称
        TextView tv_tag;        //推荐，首播等标记

        public ViewHolder(View itemView, int type) {
            super(itemView);
            if (type == VERTICAL_TYPE){
                mImageView = (ImageView) itemView.findViewById(R.id.index_content_img);
                tv_title = (TextView) itemView.findViewById(R.id.index_content_tv);
                tv_nameAndTime = (TextView) itemView.findViewById(R.id.index_content_nameAndTime);
                tv_tag = (TextView) itemView.findViewById(R.id.index_content_tag);
            }else if(type == HORIZONTAL_TYPE){
                mImageView = (ImageView) itemView.findViewById(R.id.index_title_item_img);
                tv_title = (TextView) itemView.findViewById(R.id.index_title_item_tv);
                tv_tag = (TextView) itemView.findViewById(R.id.index_title_item_tag);
                tv_nameAndTime = (TextView) itemView.findViewById(R.id.index_title_item_nameAndTime);
            }

        }
    }
}
