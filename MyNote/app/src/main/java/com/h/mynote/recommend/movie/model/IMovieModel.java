package com.h.mynote.recommend.movie.model;

import com.h.mynote.recommend.homepage.bean.movie.MovieEntity;
import com.h.mynote.recommend.homepage.bean.movie.SubjectBody;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public interface IMovieModel {
    //获取电影搜索结果
    Observable<MovieEntity> getMovieResult(String baseUrl,String tag,int start,int count);

    //正在热映
    Observable<List<SubjectBody>> getTheaters(String baseUrl,String title, int start, int count);
}
