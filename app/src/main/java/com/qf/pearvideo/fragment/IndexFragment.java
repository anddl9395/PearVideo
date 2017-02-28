package com.qf.pearvideo.fragment;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qf.pearvideo.R;
import com.qf.pearvideo.adapter.IndexFragmentPagerAdapter;
import com.qf.pearvideo.bean.Category;
import com.qf.pearvideo.present.ITitleInfoPresenter;
import com.qf.pearvideo.present.impl.TitleInfoPresenter;
import com.qf.pearvideo.utils.ConnectUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */

public class IndexFragment extends Fragment implements  IIndexFragment, RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener{

    private List<Category> dataTitleList = new ArrayList<>();//用于存放头部List集合
    private List<Fragment> fragmentList = new ArrayList<>();//用于存放所有ViewPager里面的内容
    private RadioGroup radioGroup;
    private Context context;
    private ViewPager viewPager;
    private HorizontalScrollView hsv;  //滑动标题栏
    private ITitleInfoPresenter mITitleInfoPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        mITitleInfoPresenter = new TitleInfoPresenter(context, this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //进行网络请求
        mITitleInfoPresenter.getTitleInfo(ConnectUrl.topUrl);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index_layout, null);
        init(view);
        return view;
    }

    private void init(View view) {
        findView(view);
        setListener();
    }

    private void setAdapter(List<Fragment> fragmentList) {
        FragmentManager fm = getFragmentManager();
        IndexFragmentPagerAdapter mIndexFragmentPagerAdapter = new IndexFragmentPagerAdapter(fm, fragmentList);
        viewPager.setAdapter(mIndexFragmentPagerAdapter);
    }

    private void setListener() {
        radioGroup.setOnCheckedChangeListener(this);
        viewPager.addOnPageChangeListener(this);
    }

    private void findView(View view) {
        radioGroup = (RadioGroup) view.findViewById(R.id.index_title_rg);
        viewPager = (ViewPager) view.findViewById(R.id.index_vp);
        hsv = (HorizontalScrollView) view.findViewById(R.id.index_title);
    }

    /**
     * 该方法用于获取标题栏数据，并加载RadioGroup
     * @param list 返回的标题数据源
     */
    @Override
    public void getCategoryList(List<Category> list) {
        if (radioGroup == null){//如果radioGroup没有初始化，则不进行操作
            return;
        }
        dataTitleList.clear();
        dataTitleList.addAll(list);
        int length = dataTitleList.size();
        for (int i = 0; i <= length; i++) {
            RadioButton radioButton = new RadioButton(context);
            //设置文字
            if(i == 0){
                radioButton.setText("头条");
            }else{
                radioButton.setText(dataTitleList.get(i - 1).getName());
            }
            //将RadioButton按钮样式隐藏
            radioButton.setButtonDrawable(android.R.color.transparent);
            //设置RadioButton颜色选择
            ColorStateList csl = ContextCompat.getColorStateList(context, R.color.index_rb);
            radioButton.setTextColor(csl);
            //设置宽和高以及边距
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(70, 40);
            if (i != 0){
                params.setMargins(30, 0, 0, 0);
            }
            //添加到radioGroup中
            radioGroup.addView(radioButton, i, params);
        }

        //获取数据以后加载Fragment
        createFragment();
    }

    @Override
    public void createFragment() {
        if (dataTitleList != null && dataTitleList.size() != 0){
            fragmentList.clear();
            fragmentList.add(new IndexMainFragment());
            int k = dataTitleList.size();
            for (int i = 0; i < k; i++) {
                Bundle bundle = new Bundle();
                bundle.putInt("categoryId", dataTitleList.get(i).getCategoryId());
                IndexOtherFragment mIndexOtherFragment = new IndexOtherFragment();
                mIndexOtherFragment.setArguments(bundle);
                fragmentList.add(mIndexOtherFragment);//添加到集合中
            }
            //设置适配器
            setAdapter(fragmentList);
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < group.getChildCount(); i++) {
            RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
            if (rb.isChecked()){
                viewPager.setCurrentItem(i);
            }
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        RadioButton rb = (RadioButton) radioGroup.getChildAt(position);
        rb.setChecked(true);
        onCheckedChanged(radioGroup, position);
        float x = rb.getX();
        hsv.smoothScrollTo((int)x - 150, 0);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
