package com.h.mynote.recommend.homepage.retrofit.book;

import com.h.mynote.recommend.book.model.CustomRetrofit;
import com.h.mynote.recommend.homepage.bean.book.BookEntity;
import com.h.mynote.recommend.homepage.bean.movie.MovieEntity;
import com.h.mynote.recommend.homepage.bean.music.MusicEntity;
import com.h.mynote.recommend.homepage.retrofit.HttpService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangchm on 2016/9/13 0013.
 */
public class BookListModelImpl implements IBookModel {
    Retrofit retrofit;
    HttpService httpService;

    @Override
    public void getBooks(Subscriber<BookEntity> subscriber,String baseUrl, String tag,String q, int count, int start) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor())
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        httpService = retrofit.create(HttpService.class);
        httpService.getBookList(tag,q,start,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /*
    * 同时进行多个网络请求
    * */
    @Override
    public Observable<MovieEntity> getMovieList(String baseUrl, int count, int start) {
        httpService = CustomRetrofit.getInstance().getRetrofit(baseUrl).create(HttpService.class);
        return httpService.getMovieList(start, count);
    }

    @Override
    public Observable<MusicEntity> getMusicList(String baseUrl, String tag, int count, int start) {
        httpService = CustomRetrofit.getInstance().getRetrofit(baseUrl).create(HttpService.class);
        return httpService.getMusic(tag, start, count);
    }

    @Override
    public Observable<MovieEntity> getTvList(String baseUrl, String tag, int count, int start) {
        httpService = CustomRetrofit.getInstance().getRetrofit(baseUrl).create(HttpService.class);
        return httpService.searchMovie(tag, start, count);
    }

    @Override
    public Observable<BookEntity> getBookList(String baseUrl, String tag,String q, int count, int start) {
        httpService = CustomRetrofit.getInstance().getRetrofit(baseUrl).create(HttpService.class);
        return httpService.getBookList(tag,q, start, count);
    }
}
