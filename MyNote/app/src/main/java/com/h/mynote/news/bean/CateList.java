package com.h.mynote.news.bean;

import java.io.Serializable;
import java.util.List;
import com.h.mynote.greendao.greenBean.NewsCate;

/**
 * Created by wangchm on 2017/6/29 0029.
 */

public class CateList implements Serializable {
    private List<NewsCate> newsCates;

    public CateList(List<NewsCate> newsCates) {
        this.setNewsCates(newsCates);
    }

    public List<NewsCate> getNewsCates() {
        return newsCates;
    }

    public void setNewsCates(List<NewsCate> newsCates) {
        this.newsCates = newsCates;
    }
}
