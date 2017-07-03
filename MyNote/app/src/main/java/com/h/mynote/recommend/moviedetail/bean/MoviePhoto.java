package com.h.mynote.recommend.moviedetail.bean;

import com.h.mynote.recommend.homepage.bean.movie.SubjectBody;

import java.util.List;

/**
 * Created by wangchm on 2016/9/21 0021.
 */
public class MoviePhoto {
    private int count;
    private int start;
    private int total;
    private SubjectBody subject;
    private List<PhotoDetail> photos;

    public MoviePhoto(int count, int start, int total, SubjectBody subject, List<PhotoDetail> photos) {
        this.setCount(count);
        this.setStart(start);
        this.setTotal(total);
        this.setSubject(subject);
        this.setPhotos(photos);
    }

    public String toString() {
        return "MoviePhoto{" +
                "count='" + count + '\'' +
                ", start='" + start + '\'' +
                ", total='" + total + '\'' +
                ", subject='" + subject + '\'' +
                ", photos='" + photos + '\'' +
                '}';
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

    public SubjectBody getSubject() {
        return subject;
    }

    public void setSubject(SubjectBody subject) {
        this.subject = subject;
    }

    public List<PhotoDetail> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDetail> photos) {
        this.photos = photos;
    }
}
