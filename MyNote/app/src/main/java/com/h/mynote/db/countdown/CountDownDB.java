package com.h.mynote.db.countdown;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by h on 2016/4/18 0018.
 */
public class CountDownDB {
    private static final String DB_NAME = "countdown.db";
    private static final String DB_TABLE = "countdowninfo";
    private static final int DB_VERSION = 1;

    private static final String KEY_ID = "id";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_YEAR = "year";
    private static final String KEY_MONTH = "month";
    private static final String KEY_DAY = "day";
    private static final String KEY_HOUR = "hour";
    private static final String KEY_MINUTE = "minute";
    private static final String KEY_SECOND = "second";
    private static final String KEY_PATH = "path";
    private static final String KEY_KEEPPAUSE = "keeppause";

    private SQLiteDatabase db;
    private Context context;
    private CountDownDBOpenHelper countDownDBOpenHelper;

    public CountDownDB(Context context){
        this.context = context;
    }

    public void close(){
        if(db != null){
            db.close();
            db = null;
        }
    }

    public void open(){
        countDownDBOpenHelper = new CountDownDBOpenHelper(context,DB_NAME,null,DB_VERSION);
        try{
            db = countDownDBOpenHelper.getWritableDatabase();
        }catch (SQLiteException se){
            db = countDownDBOpenHelper.getReadableDatabase();
        }
    }

    public long insert(CountDownSet cds){
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_CONTENT,cds.getContent());
        newValues.put(KEY_YEAR,cds.getYear());
        newValues.put(KEY_MONTH,cds.getMonth());
        newValues.put(KEY_DAY,cds.getDay());
        newValues.put(KEY_HOUR,cds.getHour());
        newValues.put(KEY_MINUTE,cds.getMinute());
        newValues.put(KEY_SECOND,cds.getSecond());
        newValues.put(KEY_PATH,cds.getPath());
        newValues.put(KEY_KEEPPAUSE,cds.getKeepPause());
        return db.insert(DB_TABLE,null,newValues);
    }

    public List<CountDownSet> queryAllData(){
        Cursor results = db.query(false,DB_TABLE,new String[]{KEY_ID,KEY_CONTENT,KEY_YEAR,KEY_MONTH,KEY_DAY,KEY_HOUR,
                KEY_MINUTE,KEY_SECOND,KEY_PATH,KEY_KEEPPAUSE},null,null,null,null,null,null,null);
        return convertToStorage(results);
    }

    //透过游标取出所有数据
    private List<CountDownSet> convertToStorage(Cursor cursor){
        int resultCounts = cursor.getCount();
        if(resultCounts == 0 || !cursor.moveToFirst()){
            return null;
        }
        List<CountDownSet> storages = new ArrayList<CountDownSet>();
        for(int i=0;i<resultCounts;i++){
            CountDownSet cds = new CountDownSet();
            cds.setId(cursor.getLong(0));
            cds.setContent(cursor.getString(cursor.getColumnIndex(KEY_CONTENT)));
            cds.setYear(cursor.getInt(cursor.getColumnIndex(KEY_YEAR)));
            cds.setMonth(cursor.getInt(cursor.getColumnIndex(KEY_MONTH)));
            cds.setDay(cursor.getInt(cursor.getColumnIndex(KEY_DAY)));
            cds.setHour(cursor.getInt(cursor.getColumnIndex(KEY_HOUR)));
            cds.setMinute(cursor.getInt(cursor.getColumnIndex(KEY_MINUTE)));
            cds.setSecond(cursor.getInt(cursor.getColumnIndex(KEY_SECOND)));
            cds.setPath(cursor.getString(cursor.getColumnIndex(KEY_PATH)));
            cds.setKeepPause(cursor.getInt(cursor.getColumnIndex(KEY_KEEPPAUSE)));
            storages.add(cds);
            cursor.moveToNext();
        }
        return storages;
    }

    //查询单条数据
    public CountDownSet queryOneData(long id){
        Cursor cursor = db.query(DB_TABLE,new String[]{KEY_ID,KEY_CONTENT,KEY_YEAR,KEY_MONTH,KEY_DAY,KEY_HOUR,
                KEY_MINUTE,KEY_SECOND,KEY_PATH,KEY_KEEPPAUSE},KEY_ID+"="+id,null,null,null,null,null);
        CountDownSet cds = new CountDownSet();
        cds.setId(cursor.getLong(0));
        cds.setContent(cursor.getString(cursor.getColumnIndex(KEY_CONTENT)));
        cds.setYear(cursor.getInt(cursor.getColumnIndex(KEY_YEAR)));
        cds.setMonth(cursor.getInt(cursor.getColumnIndex(KEY_MONTH)));
        cds.setDay(cursor.getInt(cursor.getColumnIndex(KEY_DAY)));
        cds.setHour(cursor.getInt(cursor.getColumnIndex(KEY_HOUR)));
        cds.setMinute(cursor.getInt(cursor.getColumnIndex(KEY_MINUTE)));
        cds.setSecond(cursor.getInt(cursor.getColumnIndex(KEY_SECOND)));
        cds.setPath(cursor.getString(cursor.getColumnIndex(KEY_PATH)));
        cds.setKeepPause(cursor.getInt(cursor.getColumnIndex(KEY_KEEPPAUSE)));
        return cds;
    }

    //删除所有数据
    public long delAllData(){
        return db.delete(DB_TABLE,null,null);
    }

    public long delOneData(Long id){
        return db.delete(DB_TABLE,KEY_ID +"="+id,null);
    }

    private static class CountDownDBOpenHelper extends SQLiteOpenHelper{

        public CountDownDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        private static String COUNTDOWN_DB_CREATE = "create table "+DB_TABLE +" ("+KEY_ID+" integer primary key autoincrement,"
                +KEY_CONTENT+" text not null,"+KEY_YEAR+" integer not null,"+KEY_MONTH+" integer not null,"+KEY_DAY+" integer not null,"
                +KEY_HOUR+" integer not null,"+KEY_MINUTE+" integer not null,"+KEY_SECOND +" integer not null,"+KEY_PATH +" text not null,"
                +KEY_KEEPPAUSE +" integer)";

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(COUNTDOWN_DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
        }
    }

}
