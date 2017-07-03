package com.h.mynote.recommend.moviedetail.model;

import com.h.mynote.recommend.moviedetail.bean.MovieDetail;
import com.h.mynote.recommend.moviedetail.bean.MoviePhoto;

import rx.Observable;

/**
 * Created by wangchm on 2016/9/21 0021.
 */
public interface IMovieDetailModel {
    //获取影片详情
    Observable<MovieDetail> getMovieDetail(String baseUrl,String id);
    //剧照
    Observable<MoviePhoto> getMoviePhoto(String baseUrl,String id);
}
