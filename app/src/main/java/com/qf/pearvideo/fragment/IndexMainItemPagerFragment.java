package com.qf.pearvideo.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.qf.pearvideo.R;

import java.io.File;

/**
 * Created by Administrator on 2017/2/28.
 */

public class IndexMainItemPagerFragment extends Fragment {

    private Context context;
    private TextView tvTag;                 //推荐等标记
    private TextView tvNameAndTime, tvTitle;//时间和名称的文本框， 以及标题文本框
    public VideoView videoView;            //播放视频控件

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index_main_itempager_layout, container, false);
        findView(view);
        initData();
        setListener();
        return view;
    }

    private void setListener() {
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        //设置播放完成的监听
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.seekTo(0);
                videoView.resume();
                videoView.start();
            }
        });
    }

    private void initData() {
        Bundle bundle = getArguments();
        String tag = bundle.getString("tag");
        String nameAndTime = bundle.getString("nameAndTime", "流水人家 | 1'2''");
        String title = bundle.getString("title", "标题标题");
        String videoPath = bundle.getString("path");

        if (tag != null && tag != "")
            tvTag.setText(tag);
        tvNameAndTime.setText(nameAndTime);
        tvTitle.setText(title);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        videoView.setLayoutParams(layoutParams);

        File file = new File(videoPath);
        if (file.exists()){
            videoView.setVideoPath(videoPath);
        }

    }

    private void findView(View view) {
        tvTag = (TextView) view.findViewById(R.id.index_main_item_tag);
        tvNameAndTime = (TextView) view.findViewById(R.id.index_main_item_NameAndTime);
        tvTitle = (TextView) view.findViewById(R.id.index_main_item_title);
        videoView = (VideoView) view.findViewById(R.id.index_main_item_video);
    }
}
