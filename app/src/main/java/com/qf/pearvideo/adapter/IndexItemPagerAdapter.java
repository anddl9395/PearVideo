package com.qf.pearvideo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 */

public class IndexItemPagerAdapter extends PagerAdapter {

    Context context;
    List<VideoView> videoList;
    List<String> titleList;

    public IndexItemPagerAdapter(Context context, List<VideoView> videoList,List<String> titleList) {
        this.context = context;
        this.videoList = videoList;
        this.titleList = titleList;
    }

    @Override
    public int getCount() {
        return videoList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        VideoView videoView = videoList.get(position);
        container.addView(videoView);
        return videoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((VideoView)object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
