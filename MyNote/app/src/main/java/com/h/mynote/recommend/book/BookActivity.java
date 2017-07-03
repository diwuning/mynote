package com.h.mynote.recommend.book;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.h.mynote.R;
import com.h.mynote.common.TextField;
import com.h.mynote.recommend.addr.HttpAddr;
import com.h.mynote.recommend.book.adapter.BookListAdapter;
import com.h.mynote.recommend.book.adapter.TagAdapter;
import com.h.mynote.recommend.book.contract.IBookView;
import com.h.mynote.recommend.book.presenter.BookPresenter;
import com.h.mynote.recommend.homepage.bean.book.Book;
import com.h.mynote.recommend.homepage.bean.music.TagMusic;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnTextChanged;

public class BookActivity extends Activity implements IBookView {

    public static final String TAG = BookActivity.class.getSimpleName();

    BookPresenter bookPresenter;
    TagAdapter tagAdapter;
    Context mContext;
    //返回
    @BindView(R.id.iv_searchCancel)
    ImageView ivSearchCancel;
    //搜索框
    @BindView(R.id.cooksearch_edit)
    TextField cooksearchEdit;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    //热门标签
    @BindView(R.id.tv_hotTag)
    TextView tvHotTag;
    @BindView(R.id.gv_hotTag)
    GridView gvHotTag;
    @BindView(R.id.ll_hotTag)
    LinearLayout llHotTag;
    //搜索结果
    @BindView(R.id.ll_result)
    LinearLayout ll_result;
    @BindView(R.id.lv_result)
    ListView lvResult;

    List<TagMusic> tagMusicList;
    BookListAdapter bookListAdapter;
    List<Book> bookList;
    private boolean isTag = false;//是否是标签搜索

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ButterKnife.bind(this);

        mContext = BookActivity.this;
        //热门标签
        bookPresenter = new BookPresenter(this);
        bookPresenter.getHotTag(HttpAddr.BASE_URL, "GayScript", 12, 0);

    }

    /*
    * 显示热门标签
    * */
    @Override
    public void showHotTag(List<TagMusic> tagMusics) {
        if (tagMusics != null) {
            tagMusicList = tagMusics;
            tagAdapter = new TagAdapter(tagMusics, mContext);
            gvHotTag.setAdapter(tagAdapter);
        }

    }

    /*
    * 搜索结果显示
    * */
    @Override
    public void showSearchBook(List<Book> books) {
        if(books != null && books.size() != 0){
            Log.d(TAG,"books.size():"+books.size());
            llHotTag.setVisibility(View.GONE);
            ll_result.setVisibility(View.VISIBLE);
            bookListAdapter = new BookListAdapter(books,mContext);
            lvResult.setAdapter(bookListAdapter);
        }
    }

    @OnClick(R.id.iv_searchCancel)
    public void onClick() {
        finish();
    }

    /*
    * 搜索框内容改变时
    * */
    @OnTextChanged(value = R.id.cooksearch_edit,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterTextChanged(){
        if(cooksearchEdit.getText().toString().trim().equals("")){
            llHotTag.setVisibility(View.VISIBLE);
            ll_result.setVisibility(View.GONE);
        }else{
            bookPresenter.getBookResult(HttpAddr.BASE_URL,cooksearchEdit.getText().toString(),"",10,0);
        }
    }

    @OnItemClick(R.id.gv_hotTag)
    void hotTagItemClick(int position){
        isTag = true;
        bookPresenter.getBookResult(HttpAddr.BASE_URL,tagMusicList.get(position).getName(),"",10,0);
    }
}
