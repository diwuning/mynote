package com.h.mynote.recommend.moviedetail.presenter;

import android.util.Log;

import com.h.mynote.recommend.moviedetail.bean.MovieDetail;
import com.h.mynote.recommend.moviedetail.bean.MoviePhoto;
import com.h.mynote.recommend.moviedetail.bean.PhotoDetail;
import com.h.mynote.recommend.moviedetail.contract.IMovieDetailView;
import com.h.mynote.recommend.moviedetail.model.IMovieDetailModel;
import com.h.mynote.recommend.moviedetail.model.MovieDetailImpl;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangchm on 2016/9/21 0021.
 */
public class MovieDetailPresenter implements IMovieDetailPresenter {
    private static final String TAG = MovieDetailPresenter.class.getSimpleName();
    private IMovieDetailView movieDetailView;
    private IMovieDetailModel movieDetailModel;

    public MovieDetailPresenter(IMovieDetailView movieDetailView) {
        this.movieDetailView = movieDetailView;
        this.movieDetailModel = new MovieDetailImpl();
    }

    @Override
    public void getMovieDetail(String baseUrl, String id) {
        Observable movieDetailObservable = movieDetailModel.getMovieDetail(baseUrl,id);
        Observable moviePhotoObservable = movieDetailModel.getMoviePhoto(baseUrl, id);

        Observable.merge(movieDetailObservable,moviePhotoObservable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"获取影片详情失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.d(TAG,"获取影片详情成功"+o.toString());
                        if(o instanceof MovieDetail){
                            movieDetailView.showDetail((MovieDetail) o);
                        }else if(o instanceof MoviePhoto){
                            List<PhotoDetail> photoDetails = ((MoviePhoto) o).getPhotos();
                            if(photoDetails != null && photoDetails.size() != 0){
                                movieDetailView.showPhoto(((MoviePhoto) o).getPhotos());
                            }
                        }
                    }
                });
    }

}
