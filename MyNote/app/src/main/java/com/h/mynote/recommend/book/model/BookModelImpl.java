package com.h.mynote.recommend.book.model;

import android.util.Log;

import com.h.mynote.recommend.addr.HttpAddr;
import com.h.mynote.recommend.book.bean.TagEntity;
import com.h.mynote.recommend.homepage.bean.book.Book;
import com.h.mynote.recommend.homepage.bean.book.BookEntity;
import com.h.mynote.recommend.homepage.bean.music.TagMusic;
import com.h.mynote.recommend.homepage.retrofit.HttpService;
import com.h.mynote.recommend.homepage.retrofit.book.HttpLoggingInterceptor;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wangchm on 2016/9/18 0018.
 */
public class BookModelImpl implements IBookModel {

    Retrofit retrofit;
    HttpService httpService;

    public BookModelImpl(){

    }

    @Override
    public Observable<List<TagMusic>> getHotTag(String baseUrl,String userId, int count, int start) {
        httpService = CustomRetrofit.getInstance().getRetrofit(baseUrl).create(HttpService.class);

        return httpService.getHotTag(userId, count, start)
                .flatMap(new Func1<TagEntity, Observable<List<TagMusic>>>() {
                    @Override
                    public Observable<List<TagMusic>> call(TagEntity tagEntity) {
                        List<TagMusic> tagMusicList = tagEntity.getTags();
                        Log.d("BookModelImpl","标签个数是："+ tagMusicList.size());
                        return Observable.just(tagMusicList);
                    }
                });
    }

    @Override
    public Observable<BookEntity> getBookList(String baseUrl,String tag,String q, int count, int start) {
        httpService = CustomRetrofit.getInstance().getRetrofit(baseUrl).create(HttpService.class);
        return httpService.getBookList(tag, q,start, count);
    }
}
