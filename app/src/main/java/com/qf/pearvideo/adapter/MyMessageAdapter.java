package com.qf.pearvideo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public class MyMessageAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> pageList;
    private List<String> titleList;
    public MyMessageAdapter(FragmentManager fm, List<Fragment> pageList, List<String> titleList) {
        super(fm);
        this.pageList = pageList;
        this.titleList = titleList;
    }
    @Override
    public Fragment getItem(int position) {
        return pageList.get(position);
    }

    @Override
    public int getCount() {
        return pageList.size();

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
