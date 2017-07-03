package com.h.mynote.db.countdown;

/**
 * Created by h on 2016/4/18 0018.
 * 倒计时
 */
public class CountDownSet {
    public Long id;
    private String content;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private String path;
    private int keepPause;

    public CountDownSet() {
    }
    public CountDownSet(Long id) {
        this.id = id;
    }

    public CountDownSet(Long id,String content,int year,int month,int day,int hour,int minute,int second,String path,int keepPause) {
        this.id = id;
        this.content = content;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.path = path;
        this.keepPause = keepPause;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getKeepPause() {
        return keepPause;
    }

    public void setKeepPause(int keepPause) {
        this.keepPause = keepPause;
    }
}
