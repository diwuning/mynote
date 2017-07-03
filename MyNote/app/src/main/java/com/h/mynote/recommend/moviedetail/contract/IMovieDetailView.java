package com.h.mynote.recommend.moviedetail.contract;

import com.h.mynote.recommend.moviedetail.bean.MovieDetail;
import com.h.mynote.recommend.moviedetail.bean.MoviePhoto;
import com.h.mynote.recommend.moviedetail.bean.PhotoDetail;

import java.util.List;

/**
 * Created by wangchm on 2016/9/21 0021.
 */
public interface IMovieDetailView {
    void showDetail(MovieDetail movieDetail);

    void showPhoto(List<PhotoDetail> photoDetails);
}
