package com.h.mynote.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by h on 2016/3/24 0024.
 */
public class DBControl {
    private static final String DB_NAME="remind.db";
    private static final String DB_TABLE = "remindinfo";
    public static final int DB_VERSION = 1;

    public static final String KEY_ID = "_id";
    public static final String KEY_MSG = "message";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME = "time";
    public static final String KEY_TASKTYPE = "tasktype";
    public static final String KEY_REPEAT = "isrepeat";
    public static final String KEY_PRE = "ispre";
    public static final String KEY_ALERT = "alert";

    private SQLiteDatabase db;
    private final Context context;
    private DBOpenHelper dbOpenHelper;

    public DBControl(Context context) {
        this.context = context;
    }

    public void close(){
        if(db != null){
            db.close();
            db = null;
        }
    }

    //open db
    public void open(){
        dbOpenHelper = new DBOpenHelper(context,DB_NAME,null,DB_VERSION);
        try{
            db = dbOpenHelper.getWritableDatabase();
        }catch (SQLiteException se){
            db = dbOpenHelper.getReadableDatabase();
        }
    }

    //添加数据
    public long insert(RemindSet rs){
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_MSG,rs.msg);
        newValues.put(KEY_DATE,rs.date);
        newValues.put(KEY_TIME,rs.time);
        newValues.put(KEY_TASKTYPE,rs.taskType);
        newValues.put(KEY_REPEAT,rs.isRepeat);
        newValues.put(KEY_PRE,rs.isPre);
        newValues.put(KEY_ALERT,rs.alert);
        return db.insert(DB_TABLE,null,newValues);
    }

    //查询所有数据
    public List<RemindSet> queryAllData(){
        Cursor results = db.query(false,DB_TABLE,new String[]{KEY_ID,KEY_MSG,KEY_TASKTYPE,KEY_DATE,KEY_TIME,KEY_REPEAT,KEY_PRE,KEY_ALERT},
                null,null,null,null,null,null,null);
        return convertToStorage(results);
    }

    //透过游标取出所有数据
    private List<RemindSet> convertToStorage(Cursor cursor){
        int resultCounts = cursor.getCount();
        if(resultCounts == 0 || !cursor.moveToFirst()){
            return null;
        }
        List<RemindSet> storages = new ArrayList<RemindSet>();
        for(int i=0;i<resultCounts;i++){
            RemindSet remindSet = new RemindSet();
            remindSet.id = cursor.getInt(0);
            remindSet.msg = cursor.getString(cursor.getColumnIndex(KEY_MSG));
            remindSet.taskType = cursor.getInt(cursor.getColumnIndex(KEY_TASKTYPE));
            remindSet.date = cursor.getString(cursor.getColumnIndex(KEY_DATE));
            remindSet.time = cursor.getString(cursor.getColumnIndex(KEY_TIME));
            remindSet.isRepeat = cursor.getString(cursor.getColumnIndex(KEY_REPEAT));
            remindSet.isPre = cursor.getString(cursor.getColumnIndex(KEY_PRE));
            remindSet.alert = cursor.getString(cursor.getColumnIndex(KEY_ALERT));
            storages.add(remindSet);
            cursor.moveToNext();
        }
        return storages;
    }

    //查询单个数据
    public RemindSet queryOneData(int id){
        Cursor result = db.query(DB_TABLE, new String[]{KEY_ID, KEY_MSG, KEY_TASKTYPE, KEY_DATE,KEY_TIME, KEY_REPEAT, KEY_PRE, KEY_ALERT},
                KEY_ID + "=" + id, null, null, null, null, null);
        int resultCounts = result.getCount();
        if(resultCounts == 0 || !result.moveToFirst()){
            return null;
        }
        RemindSet rs = new RemindSet();
        rs.id = result.getInt(0);
        rs.msg = result.getString(result.getColumnIndex(KEY_MSG));
        rs.taskType = result.getInt(result.getColumnIndex(KEY_TASKTYPE));
        rs.date = result.getString(result.getColumnIndex(KEY_DATE));
        rs.time = result.getString(result.getColumnIndex(KEY_TIME));
        rs.isRepeat = result.getString(result.getColumnIndex(KEY_REPEAT));
        rs.isPre = result.getString(result.getColumnIndex(KEY_PRE));
        rs.alert = result.getString(result.getColumnIndex(KEY_ALERT));
        return rs;
    }

    //查询某一类数据
    public List<RemindSet> queryCateData(int taskType){
        Cursor cateResults = db.query(DB_TABLE, new String[]{KEY_ID, KEY_MSG, KEY_TASKTYPE, KEY_DATE,KEY_TIME, KEY_REPEAT, KEY_PRE, KEY_ALERT},
                KEY_TASKTYPE + "=" + taskType, null, null, null, null);
        return convertToStorage(cateResults);
    }

    //清除所有数据
    public long deleteAllData(){
        return db.delete(DB_TABLE, null, null);
    }
    //清除单个数据
    public long deleteOneData(long id){
        return db.delete(DB_TABLE, KEY_ID + "=" + id, null);
    }

    //更新单个数据
    public long updateOneData(long id,RemindSet rs){
        ContentValues updateValues = new ContentValues();
        updateValues.put(KEY_MSG,rs.msg);
        updateValues.put(KEY_DATE,rs.date);
        updateValues.put(KEY_TIME,rs.time);
        updateValues.put(KEY_TASKTYPE,rs.taskType);
        updateValues.put(KEY_REPEAT,rs.isRepeat);
        updateValues.put(KEY_PRE,rs.isPre);
        updateValues.put(KEY_ALERT,rs.alert);
        return db.update(DB_TABLE,updateValues,KEY_ID+"="+id,null);
    }

    //静态Helper类，用于建立、更新和打开数据库
    private static class DBOpenHelper extends SQLiteOpenHelper{

        public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        private static String DB_CREATE = "create table "+DB_TABLE+" ("+KEY_ID+" integer primary key autoincrement,"
                +KEY_MSG+" text not null,"+KEY_DATE+" text,"+KEY_TIME+" text,"+KEY_REPEAT+" text,"+KEY_PRE +" text,"+KEY_TASKTYPE
                +" integer,"+KEY_ALERT+" text)";

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
        }
    }
}
