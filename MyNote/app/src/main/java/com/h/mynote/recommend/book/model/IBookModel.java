package com.h.mynote.recommend.book.model;

import com.h.mynote.recommend.book.bean.TagEntity;
import com.h.mynote.recommend.homepage.bean.book.Book;
import com.h.mynote.recommend.homepage.bean.book.BookEntity;
import com.h.mynote.recommend.homepage.bean.music.TagMusic;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by wangchm on 2016/9/18 0018.
 * 图书页 Model接口
 */
public interface IBookModel {
//    void getHotTage1(Subscriber<TagEntity> subscriber, String baseUrl, String userId, int count, int start);
    Observable<List<TagMusic>> getHotTag(String baseUrl,String userId, int count, int start);
    Observable<BookEntity> getBookList(String baseUrl,String tag,String q, int count, int start);
}
