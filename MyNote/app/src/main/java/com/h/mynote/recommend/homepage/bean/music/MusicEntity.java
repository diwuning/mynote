package com.h.mynote.recommend.homepage.bean.music;

import java.util.List;

/**
 * Created by wangchm on 2016/9/13 0013.
 * 音乐搜索
 */
public class MusicEntity {
    private int count;
    private int start;
    private int total;
    private List<Music> musics;

    public MusicEntity(int count, int start, int total, List<Music> musics) {
        this.setCount(count);
        this.setStart(start);
        this.setTotal(total);
        this.setMusics(musics);
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

    public List<Music> getMusics() {
        return musics;
    }

    public void setMusics(List<Music> musics) {
        this.musics = musics;
    }
}
