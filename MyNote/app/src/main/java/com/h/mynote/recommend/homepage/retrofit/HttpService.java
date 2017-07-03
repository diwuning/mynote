package com.h.mynote.recommend.homepage.retrofit;

import com.h.mynote.recommend.addr.HttpAddr;
import com.h.mynote.recommend.book.bean.TagEntity;
import com.h.mynote.recommend.homepage.bean.book.Book;
import com.h.mynote.recommend.homepage.bean.book.BookEntity;
import com.h.mynote.recommend.homepage.bean.movie.MovieEntity;
import com.h.mynote.recommend.homepage.bean.music.MusicEntity;
import com.h.mynote.recommend.homepage.bean.music.TagMusic;
import com.h.mynote.recommend.moviedetail.bean.MovieDetail;
import com.h.mynote.recommend.moviedetail.bean.MoviePhoto;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wangchm on 2016/9/12 0012.
 * 相当于httpApi
 */
public interface HttpService {
    //豆瓣电影TOP250
    @GET(HttpAddr.MOVIE_TOP250)
    Call<MovieEntity> getTopMovie(@Query("start") int start,@Query("count") int count);
    @GET(HttpAddr.MOVIE_TOP250)
    Observable<MovieEntity> getMovieList(@Query("start") int start,@Query("count") int count);

    //豆瓣音乐－按照标签搜索
    @GET(HttpAddr.MUSIC_SEARCH)
    Observable<MusicEntity> getMusic(@Query("tag") String tag,@Query("start") int start,@Query("count") int count);

    //搜索影片
    @GET(HttpAddr.TV_SEARCH)
    Observable<MovieEntity> searchMovie(@Query("tag") String tag,@Query("start") int start,@Query("count") int count);

    //搜索图书
    @GET(HttpAddr.BOOK_SEARCH)
    Observable<BookEntity> getBookList(@Query("tag") String tag,@Query("q") String q,@Query("start") int start,@Query("count") int count);

    /*
    * 图书页面
    * 图书标签
    * */
    @GET(HttpAddr.BOOK_HOTTAG)
    Observable<TagEntity> getHotTag(@Path("id") String userId, @Query("count") int count, @Query("start") int start);

    /*
    * 电影页面
    * */
    //正在热映
    @GET(HttpAddr.MOVIE_THEATERS)
    Observable<MovieEntity> getTheaters(@Query("title") String title,@Query("start") int start,@Query("count") int count);

    //影片详情
    @GET(HttpAddr.MOVIE_DETAIL)
    Observable<MovieDetail> getMovieDetail(@Path("id") String id);

    //剧照
    @GET(HttpAddr.MOVIE_PHOTO)
    Observable<MoviePhoto> getMoviePhoto(@Path("id") String id);

    //图书信息
    @GET(HttpAddr.BOOK_DETAIL)
    Observable<Book> getBookDetail(@Path("id") String id);

}
