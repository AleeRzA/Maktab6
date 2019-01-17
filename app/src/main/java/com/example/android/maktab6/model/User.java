package com.example.android.maktab6.model;

import java.util.UUID;

public class User {
    private int _id;
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

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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
