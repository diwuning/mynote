package com.h.mynote.recommend.homepage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.h.mynote.R;
import com.h.mynote.recommend.homepage.bean.music.Music;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangchm on 2016/9/13 0013.
 * 音乐推荐
 */
public class MusicGridAdapter extends BaseAdapter {
    private List<Music> musics;
    private Context mContext;

    public MusicGridAdapter(List<Music> musics, Context mContext) {
        this.musics = musics;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return null == musics? 0:musics.size();
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
        MusicHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item,null);
            holder = new MusicHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (MusicHolder) convertView.getTag();
        }

        Music music = musics.get(position);
        ImageLoader.getInstance().displayImage(music.getImage(),holder.iv_img);
        holder.tv_name.setText(music.getTitle());
        return convertView;
    }

    class MusicHolder{
        @BindView(R.id.iv_img)
        ImageView iv_img;

        @BindView(R.id.tv_name)
        TextView tv_name;

        @BindView(R.id.tv_rating)
        TextView tv_rating;

        @BindView(R.id.iv_tag)
        ImageView iv_tag;

        public MusicHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
