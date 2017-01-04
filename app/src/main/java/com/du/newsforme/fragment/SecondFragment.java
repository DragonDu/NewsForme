package com.du.newsforme.fragment;

import android.support.v4.widget.NestedScrollView;

import com.du.newsforme.R;
import com.du.newsforme.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/10/24.
 */

public class SecondFragment extends BaseFragment {
    @BindView(R.id.nsv_second)
    NestedScrollView scrollView;

    @Override
    public int initResource() {
        return R.layout.fg_two;
    }

    @Override
    public void initView() {
        super.initView();


    }
}
