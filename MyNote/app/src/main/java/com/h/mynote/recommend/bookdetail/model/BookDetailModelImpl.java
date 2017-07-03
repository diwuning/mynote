package com.h.mynote.recommend.bookdetail.model;

import com.h.mynote.recommend.book.model.CustomRetrofit;
import com.h.mynote.recommend.homepage.bean.book.Book;
import com.h.mynote.recommend.homepage.retrofit.HttpService;

import rx.Observable;

/**
 * Created by wangchm on 2016/9/22 0022.
 * 图书详情
 */
public class BookDetailModelImpl implements IBookDetailModel {
    HttpService httpService;
    @Override
    public Observable<Book> getBookDetail(String baseUrl, String id) {
        httpService = CustomRetrofit.getInstance().getRetrofit(baseUrl).create(HttpService.class);
        return httpService.getBookDetail(id);
    }
}
