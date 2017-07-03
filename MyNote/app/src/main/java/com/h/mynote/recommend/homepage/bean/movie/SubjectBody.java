package com.h.mynote.recommend.homepage.bean.movie;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangchm on 2016/9/6 0006.
 */
public class SubjectBody implements Serializable {
    private RatingBody rating;//评分
    private String[] genres;
    private String title;
    private List<CastBody> casts;
    private int collect_count;
    private String original_title;//原名
    private String subtype;//条目分类, movie或者tv
    private List<CastBody> directors;
    private String year;
    private AvatarsBody images;//电影海报图，分别提供288px x 465px(大)，96px x 155px(中) 64px x 103px(小)尺寸
    private String alt;//条目URL
    private String id;

    public SubjectBody(RatingBody rating, String[] genres, String title, List<CastBody> casts, int collect_count,
                       String original_title, String subtype, List<CastBody> directors, String year, AvatarsBody images,
                       String alt, String id) {
        this.setRating(rating);
        this.setGenres(genres);
        this.setTitle(title);
        this.setCasts(casts);
        this.setCollect_count(collect_count);
        this.setOriginal_title(original_title);
        this.setSubtype(subtype);
        this.setDirectors(directors);
        this.setYear(year);
        this.setImages(images);
        this.setAlt(alt);
        this.setId(id);
    }

    public RatingBody getRating() {
        return rating;
    }

    public void setRating(RatingBody rating) {
        this.rating = rating;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CastBody> getCasts() {
        return casts;
    }

    public void setCasts(List<CastBody> casts) {
        this.casts = casts;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public List<CastBody> getDirectors() {
        return directors;
    }

    public void setDirectors(List<CastBody> directors) {
        this.directors = directors;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public AvatarsBody getImages() {
        return images;
    }

    public void setImages(AvatarsBody images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return "SubjectBody{" +
                "rating='" + rating + '\'' +
                ", genres='" + genres + '\'' +
                ", title='" + title + '\'' +
                ", casts='" + casts + '\'' +
                ", collect_count='" + collect_count + '\'' +
                ", original_title='" + original_title + '\'' +
                ", subtype='" + subtype + '\'' +
                ", directors='" + directors + '\'' +
                ", year='" + year + '\'' +
                ", images='" + images + '\'' +
                ", alt='" + alt + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
