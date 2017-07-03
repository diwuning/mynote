package com.h.mynote.recommend.bookdetail.model;

import com.h.mynote.recommend.homepage.bean.book.Book;

import rx.Observable;

/**
 * Created by wangchm on 2016/9/22 0022.
 */
public interface IBookDetailModel {
    //根据图书ID获取图书详情
    Observable<Book> getBookDetail(String baseUrl,String id);
}
