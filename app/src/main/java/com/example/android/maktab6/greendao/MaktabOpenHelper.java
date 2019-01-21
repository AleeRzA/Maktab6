package com.example.android.maktab6.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.maktab6.model.DaoMaster;

import org.greenrobot.greendao.database.Database;

public class MaktabOpenHelper extends DaoMaster.DevOpenHelper {

    public MaktabOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MaktabOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
