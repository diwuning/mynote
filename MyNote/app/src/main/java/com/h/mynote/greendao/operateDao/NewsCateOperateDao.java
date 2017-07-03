package com.h.mynote.greendao.operateDao;

import com.h.mynote.common.MyApplication;
import com.h.mynote.greendao.greenBean.NewsCate;
import com.h.mynote.greendao.greenDao.NewsCateDao;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2017/6/30 0030.
 */

public class NewsCateOperateDao {
    /**
     * 增加新闻类别
     * @author wangchm
     * @2017年06月30日
     * @param name
     * @param value
     */
    public static void insertData(String name,String value,String isSel){
        NewsCate newsCate = new NewsCate();
        newsCate.setName(name);
        newsCate.setValue(value);
        newsCate.setIsSel(isSel);
        MyApplication.getDaoSession().getNewsCateDao().insertOrReplace(newsCate);
    }

    public static void insertData(NewsCate newsCate){
        MyApplication.getDaoSession().getNewsCateDao().insertOrReplace(newsCate);
    }

    /*
    * 获取类别列表
    * */
    public static List<NewsCate> getAllData(){
        QueryBuilder<NewsCate> qb = MyApplication.getDaoSession().getNewsCateDao().queryBuilder();
        qb.orderAsc(NewsCateDao.Properties.Id);
        return qb.list();
    }

    /*
    * 删除类别
    * */
    public static void deleteData(String nid){
        QueryBuilder<NewsCate> qb = MyApplication.getDaoSession().getNewsCateDao().queryBuilder();
        qb.where(NewsCateDao.Properties.Id.eq(nid));
        NewsCate bean = qb.unique();
        if(null != bean){
            MyApplication.getDaoSession().getNewsCateDao().delete(bean);
        }
    }

    /*
    * 删除所有数据
    *
    * */
    public static void deleteAllData(){
        MyApplication.getDaoSession().getNewsCateDao().deleteAll();
    }

}
