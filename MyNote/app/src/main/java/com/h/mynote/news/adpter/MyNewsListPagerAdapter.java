package com.h.mynote.news.adpter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangchm on 2017/6/29 0029.
 * viewPager中嵌套listView
 */

public class MyNewsListPagerAdapter extends PagerAdapter {
    private static final String TAG = "MyNewsListPagerAdapter";
    List<ListView> listViews = new ArrayList<ListView>();
    Context mContext;

    
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
