package com.h.mynote.recommend.moviedetail.bean;

/**
 * Created by wangchm on 2016/9/21 0021.
 */
public class PhotoDetail {
    private String id;
    private String alt;
    private String icon;
    private String image;
    private String thumb;
    private String cover;
    private String desc;

    public PhotoDetail(String id, String alt, String icon, String image, String thumb, String cover, String desc) {
        this.setId(id);
        this.setAlt(alt);
        this.setIcon(icon);
        this.setImage(image);
        this.setThumb(thumb);
        this.setCover(cover);
        this.setDesc(desc);
    }

    public String toString() {
        return "PhotoDetail{" +
                "id='" + getId() + '\'' +
                ", alt='" + getAlt() + '\'' +
                ", icon='" + getIcon() + '\'' +
                ", image='" + getImage() + '\'' +
                ", thumb='" + getThumb() + '\'' +
                ", cover='" + getCover() + '\'' +
                ", desc='" + getDesc() + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
