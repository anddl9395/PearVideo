package com.qf.pearvideo.adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.qf.pearvideo.R;
import com.qf.pearvideo.bean.Interest;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.IOException;
import java.util.List;
/**
 * 兴趣页面适配器
 * Created by Administrator on 2017/2/24 0024.
 */

public class InterestFragmentAdapter extends RecyclerView.Adapter<InterestFragmentAdapter.MyViewHolder> implements
        View.OnClickListener,MediaPlayer.OnPreparedListener,MediaPlayer.OnCompletionListener,MediaPlayer.OnInfoListener{
    private Context context;
    private List<Interest> list;
    LayoutInflater inflater;
    MediaPlayer mMediaPlayer;//媒体播放器
    RecyclerView.ViewHolder mViewHolder;
    String currenUrl = null;//当前播放的地址
    int currenPosition = -1;//当前播放的位置
    MyViewHolder holder;
    int pos = -1;

    public InterestFragmentAdapter(Context context,List<Interest> list){
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMediaPlayer = new MediaPlayer();//初始化媒体播放器对象
        mMediaPlayer.setOnPreparedListener(this);//准备播放资源
        mMediaPlayer.setOnInfoListener(this);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.interset_item,parent,false);
        holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        pos = position;
        holder.interset_item_title.setText(list.get(pos).getName());
        holder.interset_item_text.setText(list.get(pos).getLogoname());
        holder.interset_item_totaltime.setText(list.get(pos).getDuration());
        holder.interset_item_nowtime.setText("00:00/"+list.get(pos).getDuration());
        //头像加载失败
        ImageOptions options = new ImageOptions.Builder()
                .setLoadingDrawableId(R.drawable.icon_men)
                .setRadius(DensityUtil.dip2px(10))
                .build();
        x.image().bind(holder.interset_item_portarit,list.get(pos).getLogoimg(),options);
        x.image().bind(holder.mImageView,list.get(pos).getSharePic());

        //给ImageView设置点击事件
        holder.mImageView.setOnClickListener(this);
        //当视频播放的时候滑动我们看不到的地方就停止
        Object oldTag = holder.mSurfaceView.getTag();
        if(oldTag != null){
            int value = (int) oldTag;
            if(currenPosition == value&&position != value){
                currenUrl = null;
                mMediaPlayer.stop();
                currenPosition = -1;
            }
        }
        //在适配数据的时候给每一个object设置tag标记位置
        holder.mImageView.setTag(position);
        holder.mSurfaceView.setTag(position);
        //播放视频
        //播放视频
        if(currenPosition == pos && currenUrl.equals(list.get(pos).getShareUrl())){
            holder.mImageView.setVisibility(View.GONE);
            holder.interset_item_stop.setVisibility(View.GONE);
            holder.mSurfaceView.setVisibility(View.VISIBLE);
            holder.progress_img.setImageResource(R.drawable.icon_progress_start);
            holder.progress_img.getLayoutParams().width = 45;
            holder.progress_img.getLayoutParams().height = 45;
            //给MediaPlayer设置数据
            try {
                mMediaPlayer.reset();
                SurfaceHolder surfaceHolder = holder.mSurfaceView.getHolder();
                surfaceHolder.addCallback(new SurfaceHolder.Callback() {
                    @Override
                    public void surfaceCreated(SurfaceHolder holder) {
                        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mMediaPlayer.setDisplay(holder);
                        try {
                            mMediaPlayer.setDataSource(context, Uri.parse(list.get(pos).getShareUrl()));
                            mMediaPlayer.prepare();
                            mMediaPlayer.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                    }

                    @Override
                    public void surfaceDestroyed(SurfaceHolder holder) {
                        mMediaPlayer.setOnCompletionListener(InterestFragmentAdapter.this);//播放完了
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            holder.progress_img.setImageResource(R.drawable.icon_progress_stop);
            holder.mImageView.setVisibility(View.VISIBLE);
            holder.interset_item_stop.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        currenUrl = list.get(position).getShareUrl();
        currenPosition = position;
        notifyDataSetChanged();
    }
    //媒体资源已经准备好了
    @Override
    public void onPrepared(MediaPlayer mp) {
        mMediaPlayer.start();
    }
    //视频播放完了
    @Override
    public void onCompletion(MediaPlayer mp) {
        mMediaPlayer.stop();
        currenUrl=null;
        currenPosition = -1;
        holder.progress_img.setImageResource(R.drawable.icon_progress_stop);
        holder.mImageView.setVisibility(View.VISIBLE);
        holder.interset_item_stop.setVisibility(View.VISIBLE);
    }
    //加载信息的监听
    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what){
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:   //网络不好的时候 开始缓冲
                holder.mLoad.setVisibility(View.VISIBLE);
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:    //结束缓冲
                holder.mLoad.setVisibility(View.GONE);
                break;
        }
        return false;
    }

    //模板
    static class MyViewHolder extends RecyclerView.ViewHolder{
        SurfaceView mSurfaceView;
        ImageView mImageView;//加载的图片
        ImageView interset_item_stop;//视频上的暂停图片
        LinearLayout mProgress;//视频下方的进度布局
        ImageView progress_img;//进度条旁边状态的图片
        SeekBar seekbar;//显示的进度条
        TextView interset_item_nowtime;//显示的时间
        LinearLayout mLoad;//用于展示正在缓冲
        TextView mLoadText;//缓冲进度的信息
        TextView interset_item_title;//视频的标题
        ImageView interset_item_portarit;//发视频者的头像
        TextView interset_item_text;//发视频者的名字
        TextView interset_item_totaltime;//时长

        public MyViewHolder(View view) {
            super(view);
            mSurfaceView = (SurfaceView) view.findViewById(R.id.mSurfaceView);
            mImageView = (ImageView) view.findViewById(R.id.mImageView);
            interset_item_stop = (ImageView) view.findViewById(R.id.interset_item_stop);
            mProgress = (LinearLayout) view.findViewById(R.id.mProgress);
            progress_img = (ImageView) view.findViewById(R.id.progress_img);
            seekbar = (SeekBar) view.findViewById(R.id.seekbar);
            interset_item_nowtime = (TextView) view.findViewById(R.id.interset_item_nowtime);
            mLoad = (LinearLayout) view.findViewById(R.id.mLoad);
            mLoadText = (TextView) view.findViewById(R.id.mLoadText);
            interset_item_title = (TextView) view.findViewById(R.id.interset_item_title);
            interset_item_portarit = (ImageView) view.findViewById(R.id.interset_item_portarit);
            interset_item_text = (TextView) view.findViewById(R.id.interset_item_text);
            interset_item_totaltime = (TextView) view.findViewById(R.id.interset_item_totaltime);
        }
    }
}
