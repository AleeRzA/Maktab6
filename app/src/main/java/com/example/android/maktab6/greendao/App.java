package com.example.android.maktab6.greendao;

import android.app.Application;

import com.example.android.maktab6.model.DaoMaster;
import com.example.android.maktab6.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class App extends Application {
    private DaoSession mDaoSession;
    private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        MaktabOpenHelper openHelper = new MaktabOpenHelper(this, "tasks.db");
        Database database = openHelper.getWritableDb();

        mDaoSession = new DaoMaster(database).newSession();
        mApp = this;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public static App getApp() {
        return mApp;
    }
}
