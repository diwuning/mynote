package com.h.mynote.recommend.homepage.bean.music;

import java.util.List;

/**
 * Created by wangchm on 2016/9/13 0013.
 * 音乐单项
 */
public class Music {
    private RatingMusic rating;
    private List<AuthorMusic> author;
    private String alt_title;
    private String image;
    private List<TagMusic> tags;
    private String mobile_link;
    private AttrsMusic attrs;
    private String title;
    private String alt;
    private String id;

    public Music(RatingMusic rating, List<AuthorMusic> author, String alt_title, String image, List<TagMusic> tags,
                 String mobile_link, AttrsMusic attrs, String title, String alt, String id) {
        this.setRating(rating);
        this.setAuthor(author);
        this.setAlt_title(alt_title);
        this.setImage(image);
        this.setTags(tags);
        this.setMobile_link(mobile_link);
        this.setAttrs(attrs);
        this.setTitle(title);
        this.setAlt(alt);
        this.setId(id);
    }

    public RatingMusic getRating() {
        return rating;
    }

    public void setRating(RatingMusic rating) {
        this.rating = rating;
    }

    public List<AuthorMusic> getAuthor() {
        return author;
    }

    public void setAuthor(List<AuthorMusic> author) {
        this.author = author;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<TagMusic> getTags() {
        return tags;
    }

    public void setTags(List<TagMusic> tags) {
        this.tags = tags;
    }

    public String getMobile_link() {
        return mobile_link;
    }

    public void setMobile_link(String mobile_link) {
        this.mobile_link = mobile_link;
    }

    public AttrsMusic getAttrs() {
        return attrs;
    }

    public void setAttrs(AttrsMusic attrs) {
        this.attrs = attrs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
