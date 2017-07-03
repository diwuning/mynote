package com.h.mynote.recommend.movie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.h.mynote.R;
import com.h.mynote.recommend.homepage.bean.movie.CastBody;
import com.h.mynote.recommend.homepage.bean.movie.SubjectBody;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangchm on 2016/9/20 0020.
 * 影片搜索结果
 */
public class MovieAdapter extends BaseAdapter {
    private List<SubjectBody> moviesList;
    private Context mContext;

    public MovieAdapter(List<SubjectBody> moviesList, Context mContext) {
        this.moviesList = moviesList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return null == moviesList ? 0 : moviesList.size();
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.book_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //影片海报
        SubjectBody subjectBody = moviesList.get(position);
        ImageLoader.getInstance().displayImage(subjectBody.getImages().getLarge(), holder.ivBookImg);
        holder.labTitle.setText("片名：");
        holder.tvTitle.setText(subjectBody.getTitle());
        //导演
        holder.labWorker.setText("导演：");
        List<CastBody> directors = subjectBody.getDirectors();
        String director = "";
        for(int i=0;i<directors.size();i++){
            if(i<directors.size() -1){
                director = director + directors.get(i).getName()+",";
            }else if(i == directors.size() -1){
                director = director + directors.get(i).getName();
            }
        }
        holder.tvAuthor.setText(director);
        //演员
        holder.labPublisher.setText("主演：");
        List<CastBody> casts = subjectBody.getCasts();
        String cast = "";
        for(int i=0;i<casts.size();i++){
            if(i<casts.size() -1){
                cast = cast + casts.get(i).getName()+",";
            }else if(i == casts.size() -1){
                cast = cast + casts.get(i).getName();
            }
        }
        holder.tvPublisher.setText(cast);
        //上映时间
        holder.labPage.setText("时间：");
        holder.tvPageNum.setText(subjectBody.getYear());
        //评分
        holder.labPrice.setText("豆瓣评分：");
        holder.tvPrice.setText(String.valueOf(subjectBody.getRating().getAverage()));

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_bookImg)
        ImageView ivBookImg;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_publisher)
        TextView tvPublisher;
        @BindView(R.id.tv_pageNum)
        TextView tvPageNum;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        @BindView(R.id.lab_title)
        TextView labTitle;
        @BindView(R.id.lab_worker)
        TextView labWorker;
        @BindView(R.id.lab_publisher)
        TextView labPublisher;
        @BindView(R.id.lab_page)
        TextView labPage;
        @BindView(R.id.lab_price)
        TextView labPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
