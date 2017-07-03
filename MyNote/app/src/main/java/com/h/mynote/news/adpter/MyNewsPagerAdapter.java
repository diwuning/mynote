package com.h.mynote.news.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by wangchm on 2017/6/29 0029.
 * viewPager中嵌套Fragment
 */

public class MyNewsPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    public MyNewsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyNewsPagerAdapter(FragmentManager fm, List<Fragment> fragments){
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
