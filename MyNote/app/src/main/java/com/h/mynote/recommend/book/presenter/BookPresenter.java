package com.h.mynote.recommend.book.presenter;

import android.util.Log;

import com.h.mynote.recommend.book.bean.TagEntity;
import com.h.mynote.recommend.book.contract.IBookView;
import com.h.mynote.recommend.book.model.BookModelImpl;
import com.h.mynote.recommend.book.model.IBookModel;
import com.h.mynote.recommend.homepage.bean.book.BookEntity;
import com.h.mynote.recommend.homepage.bean.music.TagMusic;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangchm on 2016/9/18 0018.
 */
public class BookPresenter {

    private static final String TAG = BookPresenter.class.getSimpleName();
    private IBookView iBookView;
    private IBookModel iBookModel;

    public BookPresenter(IBookView iBookView) {
        this.iBookView = iBookView;
        this.iBookModel = new BookModelImpl();
    }


    /*
    * 获取热门标签
    * */
    public void getHotTag(String baseUrl,String userId,int count,int start){
        iBookModel.getHotTag(baseUrl,userId,count,start)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<List<TagMusic>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG,"获取热门标签成功");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"获取热门标签失败"+e.getMessage());
            }

            @Override
            public void onNext(List<TagMusic> tagMusics) {
                iBookView.showHotTag(tagMusics);
                Log.d(TAG,"获取热门标签成功"+tagMusics.toArray());
            }
        });
    }

    /*
    * 获取搜索结果
    * */
    public void getBookResult(String baseUrl,String tag,String q,int count,int start){
        iBookModel.getBookList(baseUrl,tag,q,count,start)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BookEntity>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG,"搜索成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"搜索失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(BookEntity bookEntity) {
                        iBookView.showSearchBook(bookEntity.getBooks());
                    }
                });
    }
}
