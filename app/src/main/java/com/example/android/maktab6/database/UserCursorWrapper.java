package com.example.android.maktab6.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.android.maktab6.model.User;

import java.util.UUID;

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getUser(){
        int _id = getInt(getColumnIndex("user_id"));
        UUID uuid = UUID.fromString(getString(getColumnIndex(DBSchema.UserTable.UserColumns.UUID)));
        String name = getString(getColumnIndex(DBSchema.UserTable.UserColumns.NAME));
        String email = getString(getColumnIndex(DBSchema.UserTable.UserColumns.EMAIL));
        String password = getString(getColumnIndex(DBSchema.UserTable.UserColumns.PASSWORD));

        User user = new User(uuid);
        user.set_id(_id);
        user.setName(name);
        user.setUserName(email);
        user.setPassword(password);

        return user;
    }
}
