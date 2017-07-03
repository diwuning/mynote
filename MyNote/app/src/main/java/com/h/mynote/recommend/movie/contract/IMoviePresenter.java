package com.h.mynote.recommend.movie.contract;

/**
 * Created by wangchm on 2016/9/20 0020.
 */
public interface IMoviePresenter {
    //电影搜索
    void getMovieResult(String baseUrl,String tag,int count,int start);
    //正在热映
    void getTheaters(String baseUrl,String title,int count,int start);
}
