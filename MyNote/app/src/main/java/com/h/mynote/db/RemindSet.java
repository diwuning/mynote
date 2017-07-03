package com.h.mynote.db;

/**
 * Created by h on 2016/3/24 0024.
 */
public class RemindSet {
    public int id;
    public String msg;
    public int taskType;
    public String date;
    public String time;
    public String isRepeat;
    public String isPre;
    public String alert;

    public RemindSet(){

    }

    public RemindSet(int id,String msg,int taskType,String date,String time,String isRepeat,String isPre,String alert){
        this.id = id;
        this.msg = msg;
        this.taskType = taskType;
        this.isRepeat = isRepeat;
        this.date = date;
        this.time = time;
        this.isPre = isPre;
        this.alert = alert;
    }
}
