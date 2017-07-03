package com.h.mynote.recommend.homepage.retrofit.book;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wangchm on 2016/9/18 0018.
 * 拦截器
 */
public class HttpLoggingInterceptor implements Interceptor {
    public static final String TAG = HttpLoggingInterceptor.class.getSimpleName();
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        Log.d(TAG,String.format("响应请求:%s %.1fms",response.request().url(),(t2-t1)/1e6d));
        Log.d(TAG,response.body().toString());
        return response;
    }
}
