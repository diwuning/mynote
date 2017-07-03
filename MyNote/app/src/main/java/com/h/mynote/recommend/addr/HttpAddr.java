package com.h.mynote.recommend.addr;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class HttpAddr {
    public static final String BASE_URL = "https://api.douban.com/v2/";

    //豆瓣电影TOP250
    public static final String MOVIE_TOP250 = "movie/top250";
    //https://api.douban.com/v2/movie/top250?start=0&count=10

    //豆瓣音乐－搜索音乐
    public static final String MUSIC_SEARCH = "music/search";
    //https://api.douban.com/v2/music/search?start=0&count=10&tag=影视

    //豆瓣电影－搜索
    public static final String TV_SEARCH = "movie/search";
    //https://api.douban.com/v2/movie/search?tag=国产剧

    /*
    * ------图书----
    * */
    //豆瓣图书－搜索
    public static final String BOOK_SEARCH = "book/search";

    //图书标签
    public static final String BOOK_HOTTAG = "book/user_tags/{id}";

    //图书信息
    public static final String BOOK_DETAIL = "book/{id}";


    //------电影----------
    //正在热映
    public static final String MOVIE_THEATERS = "movie/in_theaters";

    //电影详情
    public static final String MOVIE_DETAIL = "movie/subject/{id}";

    //剧照 movie/subject/:id/photos
    public static final String MOVIE_PHOTO = "movie/subject/{id}/photos";
}
