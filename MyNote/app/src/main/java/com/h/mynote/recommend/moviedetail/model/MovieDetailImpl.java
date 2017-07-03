package com.h.mynote.recommend.moviedetail.model;

import com.h.mynote.recommend.book.model.CustomRetrofit;
import com.h.mynote.recommend.homepage.retrofit.HttpService;
import com.h.mynote.recommend.movie.model.IMovieModel;
import com.h.mynote.recommend.moviedetail.bean.MovieDetail;
import com.h.mynote.recommend.moviedetail.bean.MoviePhoto;

import rx.Observable;

/**
 * Created by wangchm on 2016/9/21 0021.
 */
public class MovieDetailImpl implements IMovieDetailModel {
    HttpService httpService;
    @Override
    public Observable<MovieDetail> getMovieDetail(String baseUrl, String id) {
        httpService = CustomRetrofit.getInstance().getRetrofit(baseUrl).create(HttpService.class);
        return httpService.getMovieDetail(id);
    }

    @Override
    public Observable<MoviePhoto> getMoviePhoto(String baseUrl, String id) {
        httpService = CustomRetrofit.getInstance().getRetrofit(baseUrl).create(HttpService.class);
        return httpService.getMoviePhoto(id);
    }
}
