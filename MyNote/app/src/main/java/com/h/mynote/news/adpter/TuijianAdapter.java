package com.h.mynote.news.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.h.mynote.R;
import com.h.mynote.greendao.greenBean.NewsCate;
import com.h.mynote.news.TuijianActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangchm on 2017/6/28 0028.
 * 新闻推荐分类
 */

public class TuijianAdapter extends BaseAdapter {
    private Context mContext;
    private List<NewsCate> newsCates;
    private String isFrom;

    public TuijianAdapter(Context mContext, List<NewsCate> newsCates,String isFrom) {
        this.mContext = mContext;
        this.newsCates = newsCates;
        this.isFrom = isFrom;
    }

    @Override
    public int getCount() {
        return newsCates == null ? 0 : newsCates.size();
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
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.tag_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        final NewsCate newsCate = newsCates.get(position);
        holder.tvCateName.setText(newsCate.getName());

        if(newsCate.getIsSel() != null && newsCate.getIsSel().equals("1")){
            if(isFrom.equals("tuijian")){
                holder.tvCateName.setBackgroundResource(R.drawable.orange_border_style);
                holder.tvCateName.setTextColor(mContext.getResources().getColor(R.color.text_white));
            }else{
                holder.tvCateName.setBackgroundResource(R.drawable.white_border_style);
                holder.tvCateName.setTextColor(mContext.getResources().getColor(R.color.text_color_middle_gray));
            }

        }else{
            holder.tvCateName.setBackgroundResource(R.drawable.white_border_style);
            holder.tvCateName.setTextColor(mContext.getResources().getColor(R.color.text_color_middle_gray));
        }

        holder.tvCateName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newsCate.getIsSel().equals("0")){
                    newsCate.setIsSel("1");
                    TuijianActivity.selCate.add(newsCate);
                    holder.tvCateName.setBackgroundResource(R.drawable.orange_border_style);
                    holder.tvCateName.setTextColor(mContext.getResources().getColor(R.color.text_white));
                }else{
                    newsCate.setIsSel("0");
                    TuijianActivity.selCate.remove(newsCate);
                    holder.tvCateName.setBackgroundResource(R.drawable.white_border_style);
                    holder.tvCateName.setTextColor(mContext.getResources().getColor(R.color.text_color_middle_gray));
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_cateName)
        TextView tvCateName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
