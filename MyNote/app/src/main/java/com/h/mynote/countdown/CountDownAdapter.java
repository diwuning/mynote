package com.h.mynote.countdown;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.h.mynote.R;
import com.h.mynote.db.countdown.CountDownSet;

import java.util.List;

/**
 * Created by h on 2016/4/18 0018.
 */
public class CountDownAdapter extends BaseAdapter {
    private Context mContext;
    private List<CountDownSet> countdownList;

    public CountDownAdapter(Context context,List<CountDownSet> list1){
        this.mContext = context;
        this.countdownList = list1;
    }
    @Override
    public int getCount() {
        if(countdownList != null)
            return countdownList.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return countdownList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.count_down_item,null);
            holder = new ViewHolder();
            holder.tv_count_content = (TextView)convertView.findViewById(R.id.tv_count_content);
            holder.tv_count_hour = (TextView)convertView.findViewById(R.id.tv_count_hour);
            holder.tv_count_min = (TextView)convertView.findViewById(R.id.tv_count_minute);
            holder.tv_count_sec = (TextView)convertView.findViewById(R.id.tv_count_second);
            holder.iv_count_start = (ImageView)convertView.findViewById(R.id.iv_count_start);
            holder.iv_count_pause = (ImageView)convertView.findViewById(R.id.iv_count_pause);
            holder.iv_count_del = (ImageView)convertView.findViewById(R.id.iv_count_del);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        CountDownSet countDownSet = countdownList.get(position);
        holder.tv_count_content.setText(countDownSet.getContent());
        holder.tv_count_hour.setText(countDownSet.getHour());

        return convertView;
    }

    class ViewHolder{
        public TextView tv_count_content;
        public TextView tv_count_hour,tv_count_min,tv_count_sec;
        public ImageView  iv_count_start,iv_count_pause,iv_count_del;
    }
}
