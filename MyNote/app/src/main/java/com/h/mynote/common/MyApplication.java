package com.h.mynote.common;

import android.app.Application;
import android.content.Context;

import com.h.mynote.greendao.greenDao.DaoMaster;
import com.h.mynote.greendao.greenDao.DaoSession;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by wangchm on 2016/9/12 0012.
 */
public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private static MyApplication instance = null;
    private static Context mContext;
    private static final String DB_NAME = "note.db";

    public static MyApplication getInstance() {
        return instance;
    }

    public static void setInstance(MyApplication instance) {
        MyApplication.instance = instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        setInstance(this);
        mContext = this.getApplicationContext();
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration imageLoaderConfiguration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(imageLoaderConfiguration);
        getDaoMaster();
    }

    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    /**
     * 取得DaoMaster
     *
     * @return
     */
    private static DaoMaster getDaoMaster(){
        if(daoMaster == null){
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(mContext,DB_NAME,null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    public static DaoSession getDaoSession(){
        if(daoSession == null){
            if(daoMaster == null){
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
