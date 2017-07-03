package com.h.mynote.recommend.book.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.h.mynote.R;
import com.h.mynote.recommend.homepage.bean.music.TagMusic;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class TagAdapter extends BaseAdapter {
    private List<TagMusic> tagMusics;
    private Context mContext;

    public TagAdapter(List<TagMusic> tagMusics, Context mContext) {
        this.tagMusics = tagMusics;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return null == tagMusics ? 0 : tagMusics.size();
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
        TagHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.tag_item, null);
            holder = new TagHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (TagHolder) convertView.getTag();
        }

        TagMusic tagMusic = tagMusics.get(position);
        holder.tvCateName.setText(tagMusic.getName());
        return convertView;
    }

    class TagHolder {
        @BindView(R.id.tv_cateName)
        TextView tvCateName;

        TagHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
