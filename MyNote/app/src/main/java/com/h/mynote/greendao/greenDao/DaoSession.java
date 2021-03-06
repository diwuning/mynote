package com.h.mynote.greendao.greenDao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.h.mynote.greendao.greenBean.NewsCate;

import com.h.mynote.greendao.greenDao.NewsCateDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig newsCateDaoConfig;

    private final NewsCateDao newsCateDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        newsCateDaoConfig = daoConfigMap.get(NewsCateDao.class).clone();
        newsCateDaoConfig.initIdentityScope(type);

        newsCateDao = new NewsCateDao(newsCateDaoConfig, this);

        registerDao(NewsCate.class, newsCateDao);
    }
    
    public void clear() {
        newsCateDaoConfig.getIdentityScope().clear();
    }

    public NewsCateDao getNewsCateDao() {
        return newsCateDao;
    }

}
