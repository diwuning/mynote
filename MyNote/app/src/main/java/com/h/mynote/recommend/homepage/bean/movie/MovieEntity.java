package com.h.mynote.recommend.homepage.bean.movie;

import java.util.List;

/**
 * Created by wangchm on 2016/9/6 0006.
 */
public class MovieEntity {
    private int count;
    private int start;
    private int total;
    private List<SubjectBody> subjects;
    private String title;

    public MovieEntity(int count, int start, int total, List<SubjectBody> subjects, String title) {
        this.setCount(count);
        this.setStart(start);
        this.setTotal(total);
        this.setSubjects(subjects);
        this.setTitle(title);
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

    public List<SubjectBody> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectBody> subjects) {
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        return "MovieEntity{" +
                "count='" + count + '\'' +
                ", start='" + start + '\'' +
                ", total='" + total + '\'' +
                ", subjects='" + subjects + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
