package com.h.mynote.recommend.book.model;

import com.h.mynote.recommend.homepage.retrofit.HttpService;
import com.h.mynote.recommend.homepage.retrofit.book.HttpLoggingInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.PUT;

/**
 * Created by wangchm on 2016/9/18 0018.
 */
public class CustomRetrofit {
    private static CustomRetrofit INSTANCE;

    public synchronized static CustomRetrofit getInstance(){
        if(null == INSTANCE){
            synchronized (CustomRetrofit.class){
                if(null == INSTANCE){
                    INSTANCE = new CustomRetrofit();
                }
            }
        }
        return INSTANCE;
    }

    public Retrofit getRetrofit(String baseUrl){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }
}
