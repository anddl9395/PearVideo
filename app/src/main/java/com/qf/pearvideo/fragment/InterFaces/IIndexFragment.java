package com.qf.pearvideo.fragment.InterFaces;

import com.qf.pearvideo.bean.Category;

import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 */

public interface IIndexFragment {
    public void getCategoryList(List<Category> list);

    public void createFragment();
}
