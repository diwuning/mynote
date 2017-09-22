package com.h.mynote.news.adpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.h.mynote.R;
import com.h.mynote.news.NewsDetailActivity;
import com.h.mynote.news.bean.NewsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangchm on 2017/6/29 0029.
 * 某一类别的新闻列表
 */

public class NewsListAdapter extends BaseAdapter {
    private static final String TAG = "NewsListAdapter";
    Context mContext;
    private List<NewsItem> newsItems;

    public NewsListAdapter(List<NewsItem> newsItems, Context mContext) {
        this.mContext = mContext;
        this.newsItems = newsItems;
    }

    @Override
    public int getCount() {
        return newsItems == null ? 0 : newsItems.size();
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.newsitem_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        final NewsItem item = newsItems.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvTime.setText(item.getTime());
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                intent.putExtra("url",item.getUrl());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_column)
        TextView tvColumn;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
