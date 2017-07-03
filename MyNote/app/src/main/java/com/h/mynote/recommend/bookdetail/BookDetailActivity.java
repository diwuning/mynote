package com.h.mynote.recommend.bookdetail;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.h.mynote.R;
import com.h.mynote.recommend.addr.HttpAddr;
import com.h.mynote.recommend.book.adapter.TagAdapter;
import com.h.mynote.recommend.bookdetail.contract.IBookDetailView;
import com.h.mynote.recommend.bookdetail.presenter.BookDetailPresenterImpl;
import com.h.mynote.recommend.homepage.bean.book.Book;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookDetailActivity extends Activity implements IBookDetailView {
    private static final String TAG = BookDetailActivity.class.getSimpleName();

    //返回
    @BindView(R.id.iv_detailCancel)
    ImageView ivDetailCancel;
    @BindView(R.id.iv_bookImg)
    ImageView ivBookImg;
    //书名
    @BindView(R.id.tv_title)
    TextView tvTitle;
    //作者
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    //出版社
    @BindView(R.id.tv_detailPublisher)
    TextView tvDetailPublisher;
    //装订方式
    @BindView(R.id.tv_binding)
    TextView tvBinding;
    //定价
    @BindView(R.id.tv_price)
    TextView tvPrice;
    //简介
    @BindView(R.id.tv_summary)
    TextView tvSummary;
    //常用标签
    @BindView(R.id.ll_ge3)
    LinearLayout llGe3;
    @BindView(R.id.gv_bookTag)
    GridView gvBookTag;
    @BindView(R.id.ll_bookTag)
    LinearLayout llBookTag;
    //相关书籍
    @BindView(R.id.ll_ge2)
    LinearLayout llGe2;
    @BindView(R.id.gv_bookCast)
    GridView gvBookCast;
    @BindView(R.id.ll_bookCast)
    LinearLayout llBookCast;

    BookDetailPresenterImpl bookDetailPresenter;
    Context mContext;
    String bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);

        mContext = BookDetailActivity.this;

        //获取传递过来的id
        if(getIntent().getStringExtra("id") != null){
            bookId = getIntent().getStringExtra("id");
        }
        bookDetailPresenter = new BookDetailPresenterImpl(this);
        bookDetailPresenter.getBookDetail(HttpAddr.BASE_URL,bookId);
    }

    @OnClick(R.id.iv_detailCancel)
    public void onClick() {
        finish();
    }

    @Override
    public void showBookDetail(Book book) {
        tvTitle.setText(book.getTitle());
        tvAuthor.setText(Arrays.deepToString(book.getAuthor()));
        tvDetailPublisher.setText(book.getPublisher());
        tvBinding.setText(book.getBinding());
        tvPrice.setText(book.getPrice());
        tvSummary.setText(book.getSummary());

        if(book.getTags() != null && book.getTags().size() != 0){
            TagAdapter tagAdapter = new TagAdapter(book.getTags(),mContext);
            gvBookTag.setAdapter(tagAdapter);
            //设置gridView每一项的背景样式，不起作用
            for(int i=0;i<book.getTags().size();i++){
                gvBookTag.getChildAt(i).setBackgroundResource(R.drawable.cook_border_style);

            }
        }

    }
}
