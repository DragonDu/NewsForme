package com.du.newsforme.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/23.
 */

public class FtovpAdapter extends FragmentPagerAdapter {
        private List<Fragment> list ;
        private FragmentManager fm;
    public FtovpAdapter(FragmentManager fm, List<Fragment> list ) {
        super(fm);
        this.list=list;
        this.fm=fm;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
