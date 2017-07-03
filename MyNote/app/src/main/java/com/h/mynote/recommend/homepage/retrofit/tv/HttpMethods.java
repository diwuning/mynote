package com.h.mynote.recommend.homepage.retrofit.tv;

import com.h.mynote.recommend.addr.HttpAddr;
import com.h.mynote.recommend.homepage.bean.movie.MovieEntity;
import com.h.mynote.recommend.homepage.retrofit.HttpService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangchm on 2016/9/13 0013.
 * 封装
 */
public class HttpMethods {
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit retrofit;
    private HttpService httpService;

    //构造方法私有
    private HttpMethods(){
        //手动创建一个okHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(HttpAddr.BASE_URL)
                .build();
        httpService = retrofit.create(HttpService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /*\
    * 搜索影片
    * */
    public void searchMovie(Subscriber<MovieEntity> subscriber,int start,int count,String tag){
        httpService.searchMovie(tag, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
