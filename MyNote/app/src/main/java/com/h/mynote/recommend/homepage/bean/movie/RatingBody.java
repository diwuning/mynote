package com.h.mynote.recommend.homepage.bean.movie;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public class RatingBody {
    private int max;
    private float average;//评分
    private String stars;//评星数
    private int min;//最低评分

    public RatingBody(int max, float average, String stars, int min) {
        this.setMax(max);
        this.setAverage(average);
        this.setStars(stars);
        this.setMin(min);
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String toString() {
        return "RatingBody{" +
                "max='" + max + '\'' +
                ", average='" + average + '\'' +
                ", stars='" + stars + '\'' +
                ", min='" + min + '\'' +
                '}';
    }
}
