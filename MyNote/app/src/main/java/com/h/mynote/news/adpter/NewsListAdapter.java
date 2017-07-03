package com.h.mynote.news.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.h.mynote.news.bean.NewsItem;

import java.util.List;

/**
 * Created by wangchm on 2017/6/29 0029.
 * 某一类别的新闻列表
 */

public class NewsListAdapter extends BaseAdapter {
    private static final String TAG = "NewsListAdapter";
    Context mContext;
    private List<NewsItem> newsItems;

    public NewsListAdapter(List<NewsItem> newsItems,Context mContext) {
        this.mContext = mContext;
        this.newsItems = newsItems;
    }

    @Override
    public int getCount() {
        return newsItems == null?0:newsItems.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){

        }
        return convertView;
    }
}
