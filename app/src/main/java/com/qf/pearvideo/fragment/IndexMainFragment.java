package com.qf.pearvideo.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.qf.pearvideo.R;
import com.qf.pearvideo.adapter.IndexItemPagerAdapter;
import com.qf.pearvideo.bean.Cont;
import com.qf.pearvideo.bean.Node;
import com.qf.pearvideo.present.IIndexMainInfoPresenter;
import com.qf.pearvideo.present.impl.IndexMainInfoPresenter;
import com.qf.pearvideo.utils.ConnectUrl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */

public class IndexMainFragment extends Fragment implements IIndexMainFragment, ViewPager.OnPageChangeListener {

    private ViewPager viewPager;            //首页大标题视频展示
    private TextView tvTag;                 //推荐等标记
    private TextView tvNameAndTime, tvTitle;//时间和名称的文本框， 以及标题文本框
    private RecyclerView recyclerView;      //展示不同小模块的控件
    private Context context;
    private List<VideoView> mVideoViewList = new ArrayList<>(); //viewpager里面的video集合
    private Node node; //头条的node
    private int lastPosition = 0;//记录上一次播放位置

    private IIndexMainInfoPresenter mIIndexMainInfoPresenter;
    private String videoPath = Environment.getExternalStorageDirectory().getPath() + File.separator + "PearVideo";

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
        tvTag = (TextView) view.findViewById(R.id.index_main_tag);
        tvNameAndTime = (TextView) view.findViewById(R.id.index_main_NameAndTime);
        tvTitle = (TextView) view.findViewById(R.id.index_main_title);
        recyclerView = (RecyclerView) view.findViewById(R.id.index_main_recycler);
    }

    //得到数据返回
    @Override
    public void getNodeList(List<Node> list) {
        int length = list.size();
        for (int i = 0; i < length; i++) {
            if (i == 0){
                //对头条进行设置
                node = list.get(i);
                List<Cont> contList = node.getContList();
                int contLength = contList.size();
                //设置viewpager指示标题
                List<String> titleList = new ArrayList<>();
                for (int j = 0; j < contLength; j++){
                    final VideoView mVideoView = new VideoView(context);
                    mVideoViewList.add(mVideoView);
                    //设置准备好的监听
                    mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });
                    //设置播放完成的监听
                    mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mVideoView.seekTo(0);
                            mVideoView.resume();
                            mVideoView.start();
                        }
                    });
                    titleList.add("" + j + "-" + contLength);
                }
                IndexItemPagerAdapter pagerAdapter = new IndexItemPagerAdapter(context, mVideoViewList, titleList);
                viewPager.setAdapter(pagerAdapter);
                viewPager.addOnPageChangeListener(this);
            }else {
                //加载其他模块
            }
        }
    }

    @Override
    public void getFileSuccess(String path, int position) {
        //下载文件成功
        if (position == viewPager.getCurrentItem()){
            mVideoViewList.get(position).setVideoPath(path);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        if (node != null){
            Cont cont = node.getContList().get(position);//获取具体模块内容
            tvTitle.setText(cont.getContName());           //设置标题
            StringBuffer sBuff = new StringBuffer();
            sBuff.append(cont.getNodeInfo().getNodeName());
            sBuff.append(" | ");
            sBuff.append(cont.getDuration());
            tvNameAndTime.setText(sBuff.toString());        //设置名字和时长
            String tag = cont.getLabel();
            if (tag != null && tag != ""){
                tvTag.setText(tag);                         //设置推荐，首播等标记
            }

            VideoView mVideoView = mVideoViewList.get(lastPosition);
            if (mVideoView != null){
                if (mVideoView.isPlaying()){    //如果还在播放，则停止播放
                    mVideoView.stopPlayback();
                }
            }
            lastPosition = position;  //记录当前播放位置
            try{
                //判断文件夹是否存在，不存在则创建文件夹
                File file = new File(videoPath);
                if (!file.exists()){
                    file.mkdirs();
                }
                //判断文件夹中的缓存文件是否存在
                String name = getFileName(cont.getVideoPath());
                File videoFile = new File(file, name);
                if (videoFile.exists()){
                    mVideoView = mVideoViewList.get(position);
                    mVideoView.setVideoPath(videoPath + File.separator + name);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private String getFileName(String urlPath){
        String[] str = urlPath.split("/");
        return str[str.length - 1];
    }
}
