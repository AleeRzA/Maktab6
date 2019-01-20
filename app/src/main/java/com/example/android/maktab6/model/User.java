package com.example.android.maktab6.model;

import java.util.UUID;

public class User {
    private int _idTable;
    private String mName;
    private String mUserName;
    private String mPassword;
    private UUID mId;

    public User() {
        this(UUID.randomUUID());
    }

    public User(UUID id) {
        mId = id;
    }

    public int get_idTable() {
        return _idTable;
    }

    public void set_idTable(int _idTable) {
        this._idTable = _idTable;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public UUID getId() {
        return mId;
    }

}
