package com.h.mynote.recommend.book.adapter;

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
 * Created by wangchm on 2016/9/19 0019.
 * 图书列表
 */
public class BookListAdapter extends BaseAdapter {
    private List<Book> books;
    private Context mContext;

    public BookListAdapter(List<Book> books, Context mContext) {
        this.books = books;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return null == books ? 0 : books.size();
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
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.book_item, null);
            holder = new BookHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (BookHolder) convertView.getTag();
        }

        Book book = books.get(position);
        ImageLoader.getInstance().displayImage(book.getImage(), holder.ivBookImg);
        holder.tvTitle.setText(book.getTitle());
        String author = "";
        for (int i = 0; i < book.getAuthor().length; i++) {
            if (i < book.getAuthor().length - 1)
                author = author + book.getAuthor()[i] + ",";
            else if (i == book.getAuthor().length - 1) {
                author = author + book.getAuthor()[i];
            }
        }
        holder.tvAuthor.setText(author);
        holder.tvPublisher.setText(book.getPublisher());
        holder.tvPageNum.setText(book.getPages());
        holder.tvPrice.setText(book.getPrice());
        return convertView;
    }

    class BookHolder {
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

        BookHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
