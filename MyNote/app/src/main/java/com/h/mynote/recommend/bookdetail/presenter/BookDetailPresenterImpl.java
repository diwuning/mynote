package com.h.mynote.recommend.bookdetail.presenter;

import android.util.Log;

import com.h.mynote.recommend.bookdetail.contract.IBookDetailView;
import com.h.mynote.recommend.bookdetail.model.BookDetailModelImpl;
import com.h.mynote.recommend.bookdetail.model.IBookDetailModel;
import com.h.mynote.recommend.homepage.bean.book.Book;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangchm on 2016/9/22 0022.
 * 图书详情
 */
public class BookDetailPresenterImpl implements IBookDetailPresenter {
    private static final String TAG = BookDetailPresenterImpl.class.getSimpleName();
    private IBookDetailModel bookDetailModel;
    private IBookDetailView bookDetailView;

    public BookDetailPresenterImpl(IBookDetailView bookDetailView) {
        this.bookDetailView = bookDetailView;
        this.bookDetailModel = new BookDetailModelImpl();
    }

    @Override
    public void getBookDetail(String baseUrl, String id) {
        bookDetailModel.getBookDetail(baseUrl,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Book>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG,"获取图书详情成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"获取图书详情失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(Book book) {
                        bookDetailView.showBookDetail(book);
                    }
                });
    }
}
