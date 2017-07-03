package com.h.mynote.recommend.homepage.retrofit.book;

import android.graphics.Movie;

import com.h.mynote.recommend.homepage.bean.book.Book;
import com.h.mynote.recommend.homepage.bean.movie.SubjectBody;
import com.h.mynote.recommend.homepage.bean.music.Music;

import java.util.List;

/**
 * Created by wangchm on 2016/9/13 0013.
 * View interface
 */
public interface IBookView {
    void showBookList(List<Book> bookList);
    /*
    * 同时进行多个网络请求
    * */
    void showMovieList(List<SubjectBody> movies);
    void showTvList(List<SubjectBody> tvs);
    void showMusicList(List<Music> musics);
    void showBookListNew(List<Book> books);
}
