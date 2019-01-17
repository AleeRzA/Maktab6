package com.example.android.maktab6.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context) {
        super(context, DBSchema.NAME, null, DBSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + DBSchema.UserTable.NAME + "(" +
                "user_id INTEGER primary key autoincrement, " +
                DBSchema.UserTable.UserColumns.UUID + ", " +
                DBSchema.UserTable.UserColumns.NAME + ", " +
                DBSchema.UserTable.UserColumns.EMAIL + ", " +
                DBSchema.UserTable.UserColumns.PASSWORD + ")");


        sqLiteDatabase.execSQL(
            "create table " + DBSchema.TaskTable.NAME + "(" +
            "_id INTEGER primary key autoincrement, " +
            DBSchema.TaskTable.TaskColumns.UUID + ", " +
            DBSchema.TaskTable.TaskColumns.TITLE + ", " +
            DBSchema.TaskTable.TaskColumns.DESCRIPTION + ", " +
            DBSchema.TaskTable.TaskColumns.DATE + ", " +
            DBSchema.TaskTable.TaskColumns.DONE + ", " +
            DBSchema.TaskTable.TaskColumns.USER_ID + ", " +
                    " foreign key" + "(" +
                    DBSchema.TaskTable.TaskColumns.USER_ID + ")" +
                    " references " + DBSchema.UserTable.NAME + "("
                    + "user_id" + ")" +
                ")"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
