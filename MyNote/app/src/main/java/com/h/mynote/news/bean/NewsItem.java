package com.h.mynote.news.bean;

/**
 * Created by wangchm on 2017/7/3 0003.
 * 滚动新闻列表项
 */

public class NewsItem {
    private String channel;
    private String title;
    private String url;
    private String time;

    public NewsItem(){}
    public NewsItem(String channel, String title, String url, String time) {
        this.setChannel(channel);
        this.setTitle(title);
        this.setUrl(url);
        this.setTime(time);
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
