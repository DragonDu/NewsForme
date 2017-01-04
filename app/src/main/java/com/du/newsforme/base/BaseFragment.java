package com.du.newsforme.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/24.
 */

public abstract class BaseFragment extends Fragment {
    public  Context mContext;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View inflate = inflater.inflate(initResource(), null);
        ButterKnife.bind(this, inflate);
        initView();
        return inflate;

    }


    public void initView() {

    }
    @Nullable
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = BaseApplication.getInstance();
    }

    /**
     * 初始化布局
     *
     * @return 布局ID
     */
    public abstract int initResource();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEvent();
        initdata();
    }

    /**
     * 初始化事件
     */
    public void initEvent() {

    }

    /**
     * 初始化数据
     */

    public void initdata() {


    }


}
