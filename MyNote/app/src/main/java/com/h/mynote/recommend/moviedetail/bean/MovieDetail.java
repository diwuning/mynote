package com.h.mynote.recommend.moviedetail.bean;

import com.h.mynote.recommend.homepage.bean.movie.AvatarsBody;
import com.h.mynote.recommend.homepage.bean.movie.CastBody;
import com.h.mynote.recommend.homepage.bean.movie.RatingBody;

import java.util.List;

/**
 * Created by wangchm on 2016/9/21 0021.
 * 影片详情
 */
public class MovieDetail {
    private RatingBody rating;
    private int reviews_count;
    private int wish_count;//想看人数
    private String douban_site;//豆瓣小站
    private String year;
    private AvatarsBody images;//电影海报图，分别提供288px x 465px(大)，96px x 155px(中) 64px x 103px(小)尺寸
    private String alt;//条目页URL
    private String id;
    private String mobile_url;//移动版条目页URL
    private String title;//中文名
    private String do_count;//在看人数，如果是电视剧，默认值为0，如果是电影值为null
    private String share_url;
    private int seasons_count;//总季数(tv only)
    private String schedule_url;//影讯页URL(movie only)
    private int episodes_count;//当前季的集数(tv only)
    private String[] countries;
    private String[] genres;//影片类型，最多提供3个
    private int collect_count;
    private List<CastBody> casts;//主演
    private int current_season;//当前季数(tv only)
    private String original_title;//原名
    private String summary;//简介
    private String subtype;//条目分类, movie或者tv
    private List<CastBody> directors;//导演
    private int comments_count;//短评数量
    private int ratings_count;//评分人数
    private String[] aka;//又名

    public MovieDetail(RatingBody rating, int reviews_count, int wish_count, String douban_site, String year,
                       AvatarsBody images, String alt, String id, String mobile_url, String title, String do_count,
                       String share_url, int seasons_count, String schedule_url, int episodes_count, String[] countries,
                       String[] genres, int collect_count, List<CastBody> casts, int current_season, String original_title,
                       String summary, String subtype, List<CastBody> directors, int comments_count, int ratings_count, String[] aka) {
        this.setRating(rating);
        this.setReviews_count(reviews_count);
        this.setWish_count(wish_count);
        this.setDouban_site(douban_site);
        this.setYear(year);
        this.setImages(images);
        this.setAlt(alt);
        this.setId(id);
        this.setMobile_url(mobile_url);
        this.setTitle(title);
        this.setDo_count(do_count);
        this.setShare_url(share_url);
        this.setSeasons_count(seasons_count);
        this.setSchedule_url(schedule_url);
        this.setEpisodes_count(episodes_count);
        this.setCountries(countries);
        this.setGenres(genres);
        this.setCollect_count(collect_count);
        this.setCasts(casts);
        this.setCurrent_season(current_season);
        this.setOriginal_title(original_title);
        this.setSummary(summary);
        this.setSubtype(subtype);
        this.setDirectors(directors);
        this.setComments_count(comments_count);
        this.setRatings_count(ratings_count);
        this.setAka(aka);
    }

    public String toString() {
        return "MovieDetail{" +
                "rating='" + getRating() + '\'' +
                ", reviews_count='" + getReviews_count() + '\'' +
                ", wish_count='" + getWish_count() + '\'' +
                ", douban_site='" + getDouban_site() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", year='" + getYear() + '\'' +
                ", images='" + getImages() + '\'' +
                ", alt='" + getAlt() + '\'' +
                ", id='" + getId() + '\'' +
                ", mobile_url='" + getMobile_url() + '\'' +
                ", do_count='" + getDo_count() + '\'' +
                ", share_url='" + getShare_url() + '\'' +
                ", seasons_count='" + getSeasons_count() + '\'' +
                ", schedule_url='" + getSchedule_url() + '\'' +
                ", episodes_count='" + getEpisodes_count() + '\'' +
                ", countries='" + getCountries() + '\'' +
                ", genres='" + getGenres() + '\'' +
                ", collect_count='" + getCollect_count() + '\'' +
                ", casts='" + getCasts() + '\'' +
                ", current_season='" + getCurrent_season() + '\'' +
                ", original_title='" + getOriginal_title() + '\'' +
                ", comments_count='" + getComments_count() + '\'' +
                ", ratings_count='" + getRatings_count() + '\'' +
                ", subtype='" + getSubtype() + '\'' +
                ", summary='" + getSummary() + '\'' +
                '}';
    }

    public RatingBody getRating() {
        return rating;
    }

    public void setRating(RatingBody rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
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

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDo_count() {
        return do_count;
    }

    public void setDo_count(String do_count) {
        this.do_count = do_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public int getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(int seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public int getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(int episodes_count) {
        this.episodes_count = episodes_count;
    }

    public String[] getCountries() {
        return countries;
    }

    public void setCountries(String[] countries) {
        this.countries = countries;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public List<CastBody> getCasts() {
        return casts;
    }

    public void setCasts(List<CastBody> casts) {
        this.casts = casts;
    }

    public int getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(int current_season) {
        this.current_season = current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public String[] getAka() {
        return aka;
    }

    public void setAka(String[] aka) {
        this.aka = aka;
    }
}
