package com.h.mynote.recommend.homepage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.h.mynote.R;
import com.h.mynote.recommend.homepage.bean.movie.SubjectBody;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangchm on 2016/9/12 0012.
 * 推荐首页电影列表
 */
public class MovieGridAdapter extends BaseAdapter {
    private List<SubjectBody> subjectBodies;
    private Context mContext;

    public MovieGridAdapter(List<SubjectBody> subjectBodies, Context mContext) {
        this.subjectBodies = subjectBodies;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return subjectBodies == null? 0:subjectBodies.size();
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
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        SubjectBody subject = subjectBodies.get(position);
        ImageLoader.getInstance().displayImage(subject.getImages().getLarge(),holder.iv_img);
        holder.tv_name.setText(subject.getTitle());
        holder.tv_rating.setText(String.valueOf(subject.getRating().getAverage()));
        return convertView;
    }

    class ViewHolder{
        @BindView(R.id.iv_img)
        ImageView iv_img;

        @BindView(R.id.tv_name)
        TextView tv_name;

        @BindView(R.id.tv_rating)
        TextView tv_rating;

        @BindView(R.id.iv_tag)
        ImageView iv_tag;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
