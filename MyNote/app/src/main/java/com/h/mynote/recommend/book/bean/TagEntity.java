package com.h.mynote.recommend.book.bean;

import com.h.mynote.recommend.homepage.bean.music.TagMusic;

import java.util.List;

/**
 * Created by wangchm on 2016/9/18 0018.
 */
public class TagEntity {
    private int count;
    private int start;
    private int total;
    private List<TagMusic> tags;

    public TagEntity(int count, int start, int total, List<TagMusic> tags) {
        this.setCount(count);
        this.setStart(start);
        this.setTotal(total);
        this.setTags(tags);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TagMusic> getTags() {
        return tags;
    }

    public void setTags(List<TagMusic> tags) {
        this.tags = tags;
    }
}
