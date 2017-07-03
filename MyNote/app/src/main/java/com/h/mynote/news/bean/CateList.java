package com.h.mynote.news.bean;

import java.io.Serializable;
import java.util.List;
import com.h.mynote.greendao.greenBean.NewsCate;

/**
 * Created by wangchm on 2017/6/29 0029.
 */

public class CateList<T> implements Serializable {
    private T newsCates;

    public CateList(T newsCates) {
        this.setNewsCates(newsCates);
    }

    public T getNewsCates() {
        return newsCates;
    }

    public void setNewsCates(T newsCates) {
        this.newsCates = newsCates;
    }
}
