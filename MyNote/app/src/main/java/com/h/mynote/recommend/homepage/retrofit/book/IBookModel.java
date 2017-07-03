package com.h.mynote.recommend.homepage.retrofit.book;

import com.h.mynote.recommend.homepage.bean.book.BookEntity;
import com.h.mynote.recommend.homepage.bean.movie.MovieEntity;
import com.h.mynote.recommend.homepage.bean.music.MusicEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by wangchm on 2016/9/13 0013.
 * Model interface
 */
public interface IBookModel {
    void getBooks(Subscriber<BookEntity> subscriber,String baseUrl, String tag,String q, int count, int start);

    /*
    * 同时进行多个网络请求
    * */
    Observable<MovieEntity> getMovieList(String baseUrl,int count,int start);
    Observable<MusicEntity> getMusicList(String baseUrl,String tag,int count,int start);
    Observable<MovieEntity> getTvList(String baseUrl,String tag,int count,int start);
    Observable<BookEntity> getBookList(String baseUrl,String tag,String q,int count,int start);
}
