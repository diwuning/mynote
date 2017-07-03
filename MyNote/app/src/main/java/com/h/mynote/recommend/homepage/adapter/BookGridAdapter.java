package com.h.mynote.recommend.homepage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.h.mynote.R;
import com.h.mynote.recommend.homepage.bean.book.Book;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangchm on 2016/9/13 0013.
 * 图书
 */
public class BookGridAdapter extends BaseAdapter {
    private List<Book> books;
    private Context mContext;

    public BookGridAdapter(List<Book> books, Context mContext) {
        this.books = books;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return books == null? 0:books.size();
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
        BookHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item,null);
            holder = new BookHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (BookHolder) convertView.getTag();
        }

        Book book = books.get(position);
        ImageLoader.getInstance().displayImage(book.getImage(),holder.iv_img);
        holder.tv_name.setText(book.getTitle());
        return convertView;
    }

    class BookHolder{
        @BindView(R.id.iv_img)
        ImageView iv_img;

        @BindView(R.id.tv_name)
        TextView tv_name;

        @BindView(R.id.tv_rating)
        TextView tv_rating;

        @BindView(R.id.iv_tag)
        ImageView iv_tag;

        public BookHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
