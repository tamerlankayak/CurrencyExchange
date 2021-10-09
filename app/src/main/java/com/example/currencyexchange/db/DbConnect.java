package com.example.currencyexchange.db;

import android.app.Application;

import com.example.currencyexchange.entity.DaoMaster;
import com.example.currencyexchange.entity.DaoSession;

import org.greenrobot.greendao.database.Database;

public class DbConnect extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "remind_me", null);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
