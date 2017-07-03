package com.h.mynote.recommend.book.contract;

import com.h.mynote.recommend.homepage.bean.book.Book;
import com.h.mynote.recommend.homepage.bean.music.TagMusic;

import java.util.List;

/**
 * Created by wangchm on 2016/9/18 0018.
 * 图书页面View接口
 */
public interface IBookView {
    void showHotTag(List<TagMusic> tagMusics);
    void showSearchBook(List<Book> books);
}
