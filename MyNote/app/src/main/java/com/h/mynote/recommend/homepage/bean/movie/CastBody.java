package com.h.mynote.recommend.homepage.bean.movie;

import java.io.Serializable;

/**
 * Created by wangchm on 2016/9/6 0006.
 * 影人信息
 */
public class CastBody implements Serializable {
    private String alt;//影人条目URL
    private AvatarsBody avatars;//影人头像，分别提供420px x 600px(大)，140px x 200px(中) 70px x 100px(小)尺寸
    private String name;
    private String id;//影人条目id

    public CastBody(String alt, AvatarsBody avatars, String name, String id) {
        this.setAlt(alt);
        this.setAvatars(avatars);
        this.setName(name);
        this.setId(id);
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public AvatarsBody getAvatars() {
        return avatars;
    }

    public void setAvatars(AvatarsBody avatars) {
        this.avatars = avatars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return "CastBody{" +
                "alt='" + alt + '\'' +
                ", avatars='" + avatars + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
