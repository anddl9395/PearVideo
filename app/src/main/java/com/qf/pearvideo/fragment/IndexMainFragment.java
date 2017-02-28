package com.qf.pearvideo.fragment;

import android.content.Context;
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
import android.widget.VideoView;

import com.qf.pearvideo.R;
import com.qf.pearvideo.adapter.IndexFragmentPagerAdapter;
import com.qf.pearvideo.adapter.IndexRecyclerAdapter;
import com.qf.pearvideo.bean.Cont;
import com.qf.pearvideo.bean.Node;
import com.qf.pearvideo.present.IIndexMainInfoPresenter;
import com.qf.pearvideo.present.impl.IndexMainInfoPresenter;
import com.qf.pearvideo.utils.ConnectUrl;
import com.qf.pearvideo.view.FullyLinearLayoutManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */

public class IndexMainFragment extends Fragment implements IIndexMainFragment, ViewPager.OnPageChangeListener {

    private ViewPager viewPager;            //首页大标题视频展示
    private RecyclerView recyclerView;      //展示不同小模块的控件
    private Context context;
    private List<Fragment> fragmentPagerList = new ArrayList<>(); //ViewPager中播放视频的数据源
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
        recyclerView = (RecyclerView) view.findViewById(R.id.index_main_recycler);
    }

    //得到数据返回
    @Override
    public void getNodeList(List<Node> list) {
        int length = list.size();
        //除头条外首页的其他数据源全部放这个集合中
        List<Node> dataNodeList = new ArrayList<>();
        fragmentPagerList.clear();
        for (int i = 0; i < length; i++) {
            if (i == 0){
                //对头条进行设置
                node = list.get(i);
                List<Cont> contList = node.getContList();
                int contLength = contList.size();
                for (int j = 0; j < contLength; j++){
                    Cont cont = contList.get(j);
                    //下载视频
                    mIIndexMainInfoPresenter.getMainFile(cont.getVideoPath(), j);
                    //设置Bundle
                    Bundle bundle = new Bundle();
                    bundle.putString("tag",cont.getLabel());        //设置首播等标记

                    StringBuffer sBuff = new StringBuffer();
                    sBuff.append(cont.getNodeInfo().getNodeName());
                    sBuff.append(" | ");
                    sBuff.append(cont.getDuration());

                    bundle.putString("nameAndTime", sBuff.toString());//设置名称和时长
                    bundle.putString("title", cont.getContName());    //设置标题
                    String name = getFileName(cont.getVideoPath());
                    bundle.putString("path", videoPath + File.separator + name);//设置播放地址

                    IndexMainItemPagerFragment fragment = new IndexMainItemPagerFragment();
                    fragment.setArguments(bundle);
                    //添加到集合中
                    fragmentPagerList.add(fragment);
                }
                IndexFragmentPagerAdapter pagerAdapter = new IndexFragmentPagerAdapter(getFragmentManager(), fragmentPagerList);
                viewPager.setAdapter(pagerAdapter);
                viewPager.addOnPageChangeListener(this);
            }else {
                //加载其他模块
                dataNodeList.add(list.get(i));
            }
            //给其他模块设置适配器
            FullyLinearLayoutManager fullyLinearLayoutManager = new FullyLinearLayoutManager(context, FullyLinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(fullyLinearLayoutManager);
            IndexRecyclerAdapter mIndexRecyclerAdapter = new IndexRecyclerAdapter(dataNodeList, context);
            recyclerView.setAdapter(mIndexRecyclerAdapter);
        }
    }

    @Override
    public void getFileSuccess(String path, int position) {
        //下载文件成功
        if (position == viewPager.getCurrentItem()){
            IndexMainItemPagerFragment mainItemPagerFragment = (IndexMainItemPagerFragment) fragmentPagerList.get(position);
            mainItemPagerFragment.videoView.setVideoPath(path);
            if (!mainItemPagerFragment.videoView.isPlaying()){
                mainItemPagerFragment.videoView.start();
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        if (node != null){
            IndexMainItemPagerFragment mainItemPagerFragment = (IndexMainItemPagerFragment) fragmentPagerList.get(lastPosition);
            if(mainItemPagerFragment.videoView != null){
                if (mainItemPagerFragment.videoView.isPlaying())
                    mainItemPagerFragment.videoView.stopPlayback();
            }
            lastPosition = position;  //记录当前播放位置
            VideoView mVideoView = ((IndexMainItemPagerFragment)fragmentPagerList.get(position)).videoView;
            mVideoView.start();
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
