package com.h.mynote.recommend.movie.presenter;

import android.util.Log;

import com.h.mynote.recommend.homepage.bean.movie.MovieEntity;
import com.h.mynote.recommend.homepage.bean.movie.SubjectBody;
import com.h.mynote.recommend.movie.contract.IMoviePresenter;
import com.h.mynote.recommend.movie.contract.IMovieView;
import com.h.mynote.recommend.movie.model.IMovieModel;
import com.h.mynote.recommend.movie.model.MovieModelImpl;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangchm on 2016/9/20 0020.
 */
public class MoviePresenter implements IMoviePresenter {
    private static final String TAG = MoviePresenter.class.getSimpleName();
    private IMovieView iMovieView;
    private IMovieModel iMovieModel;

    public MoviePresenter(IMovieView iMovieView) {
        this.iMovieView = iMovieView;
        this.iMovieModel = new MovieModelImpl();
    }

    @Override
    public void getMovieResult(String baseUrl, String tag, int count, int start) {
        iMovieModel.getMovieResult(baseUrl, tag, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieEntity>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG,"搜索成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"搜索失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(MovieEntity movieEntity) {
                        iMovieView.showMovieResult(movieEntity.getSubjects());
                    }
                });
    }

    @Override
    public void getTheaters(String baseUrl,String title, int count, int start) {
        iMovieModel.getTheaters(baseUrl, title,start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<SubjectBody>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG,"获取热映列表成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"获取热映列表失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(List<SubjectBody> subjectBodies) {
                        iMovieView.showTheaters(subjectBodies);
                    }
                });
    }
}
