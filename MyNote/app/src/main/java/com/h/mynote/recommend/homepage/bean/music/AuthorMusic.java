package com.h.mynote.recommend.homepage.bean.music;

/**
 * Created by wangchm on 2016/9/13 0013.
 * 歌曲作者
 */
public class AuthorMusic {
    private String name;

    public AuthorMusic(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
