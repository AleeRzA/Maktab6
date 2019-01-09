package com.example.android.maktab6.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.android.maktab6.model.User;

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getUser(){
        return null;
    }
}
