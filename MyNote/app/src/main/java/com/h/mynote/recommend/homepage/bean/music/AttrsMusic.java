package com.h.mynote.recommend.homepage.bean.music;

/**
 * Created by wangchm on 2016/9/13 0013.
 */
public class AttrsMusic {
    private String[] publisher;
    private String[] singer;
    private String[] discs;
    private String[] pubdate;
    private String[] title;
    private String[] media;
    private String[] tracks;
    private String[] version;

    public AttrsMusic(String[] publisher, String[] singer, String[] discs, String[] pubdate, String[] title,
                      String[] media, String[] tracks, String[] version) {
        this.setPublisher(publisher);
        this.setSinger(singer);
        this.setDiscs(discs);
        this.setPubdate(pubdate);
        this.setTitle(title);
        this.setMedia(media);
        this.setTracks(tracks);
        this.setVersion(version);
    }

    public String[] getPublisher() {
        return publisher;
    }

    public void setPublisher(String[] publisher) {
        this.publisher = publisher;
    }

    public String[] getSinger() {
        return singer;
    }

    public void setSinger(String[] singer) {
        this.singer = singer;
    }

    public String[] getDiscs() {
        return discs;
    }

    public void setDiscs(String[] discs) {
        this.discs = discs;
    }

    public String[] getPubdate() {
        return pubdate;
    }

    public void setPubdate(String[] pubdate) {
        this.pubdate = pubdate;
    }

    public String[] getTitle() {
        return title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    public String[] getMedia() {
        return media;
    }

    public void setMedia(String[] media) {
        this.media = media;
    }

    public String[] getTracks() {
        return tracks;
    }

    public void setTracks(String[] tracks) {
        this.tracks = tracks;
    }

    public String[] getVersion() {
        return version;
    }

    public void setVersion(String[] version) {
        this.version = version;
    }
}
