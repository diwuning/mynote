package com.h.mynote.recommend.movie.contract;

import com.h.mynote.recommend.homepage.bean.movie.SubjectBody;
import com.h.mynote.recommend.homepage.bean.music.TagMusic;

import java.util.List;

/**
 * Created by wangchm on 2016/9/20 0020.
 */
public interface IMovieView {
    //显示电影搜索结果
    void showMovieResult(List<SubjectBody> movies);
    //显示正在热映的电影
    void showTheaters(List<SubjectBody> theaterList);
}
