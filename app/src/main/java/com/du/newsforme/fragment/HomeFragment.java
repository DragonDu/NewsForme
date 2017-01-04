package com.du.newsforme.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.du.newsforme.R;
import com.du.newsforme.adapter.MyPagerAdapter;
import com.du.newsforme.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.type;

/**
 * Created by Administrator on 2016/10/24.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;


    public static final String NEWS_TYPE_TOP = "top";
    public static final String NEWS_TYPE_NBA = "shehui";
    public static final String NEWS_TYPE_CARS = "junshi";
    public static final String NEWS_TYPE_JOKES = "keji";


    public enum typelist {
        热点("top"),
        社会("shehui"),
        军事("junshi"),
        科技("keji"),
        国内("guonei"),
        国际("guoji");
//
//        top("热点"),
//        shehui("社会"),
//        junshi("军事"),
//        keji("科技"),
//        guonei("国内"),
//        guoji("国际");

        private String s;

        typelist(String s) {
            this.s = s;

        }

        public String getvalue() {
            return s;
        }


    }

    @Override
    public int initResource() {

        return R.layout.fg_home;
    }

    @Override
    public void initView() {
//        String getvalue = typelist.军事.getvalue();
//        Log.e("meijuni有用吗", getvalue);
//        Log.e("meijuni有用吗", typelist.热点 + "");
//        Log.e("meijuni有用吗", typelist.values().length+"");


        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        List tabList = new ArrayList();
        //设置缓存数
        viewPager.setOffscreenPageLimit(3);
        for (typelist t : typelist.values()) {

            tabLayout.addTab(tabLayout.newTab().setText(t.toString()));

        }


//        tabLayout.addTab(tabLayout.newTab().setText(R.string.redian));
//        tabLayout.addTab(tabLayout.newTab().setText("NBA"));
//        tabLayout.addTab(tabLayout.newTab().setText("汽车"));
//        tabLayout.addTab(tabLayout.newTab().setText("笑话"));
        //与vipewpager绑定
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int
// positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }

//    /**
//     * 调色器
//     */
//
//    public void onColorChange(int position){
//        // 用来提取颜色的Bitmap
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),);
//
//    }


    @Override
    public void initEvent() {


    }


    private void setupViewPager(ViewPager mViewPager) {
        //Fragment中嵌套使用Fragment一定要使用getChildFragmentManager(),否则会有问题
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
//        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_TOP), getString(R.string
//                .redian));
//        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_NBA), getString(R.string
// .nba));
//        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_CARS), getString(R.string
//                .qiche));
//        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_JOKES), getString(R.string
//                .xiaohua));

        for (typelist t : typelist.values()) {
            adapter.addFragment(NewsListFragment.newInstance(t.getvalue()), t.toString());
        }

        mViewPager.setAdapter(adapter);
    }
}