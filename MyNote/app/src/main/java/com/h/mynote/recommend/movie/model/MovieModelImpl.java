package com.h.mynote.recommend.movie.model;

import android.util.Log;

import com.h.mynote.recommend.book.model.CustomRetrofit;
import com.h.mynote.recommend.homepage.bean.movie.MovieEntity;
import com.h.mynote.recommend.homepage.bean.movie.SubjectBody;
import com.h.mynote.recommend.homepage.retrofit.HttpService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class MovieModelImpl implements IMovieModel {
    private static final String TAG = MovieModelImpl.class.getSimpleName();
    HttpService httpService;

    @Override
    public Observable<MovieEntity> getMovieResult(String baseUrl, String tag, int start, int count) {
        httpService = CustomRetrofit.getInstance().getRetrofit(baseUrl).create(HttpService.class);
        return httpService.searchMovie(tag, start, count);
    }

    @Override
    public Observable<List<SubjectBody>> getTheaters(String baseUrl,String title, int start, int count) {
        httpService = CustomRetrofit.getInstance().getRetrofit(baseUrl).create(HttpService.class);
        return httpService.getTheaters(title,start, count)
                .flatMap(new Func1<MovieEntity, Observable<List<SubjectBody>>>() {
                    @Override
                    public Observable<List<SubjectBody>> call(MovieEntity movieEntity) {
                        List<SubjectBody> subjectBodies = new ArrayList<SubjectBody>();
                        Log.d(TAG,"movieEntity.toString()="+movieEntity.toString());
                        if(movieEntity.getSubjects() != null && movieEntity.getSubjects().size() != 0){
                            subjectBodies = movieEntity.getSubjects();;
                        }
                        return Observable.just(subjectBodies);
                    }
                });
    }
}
