package com.example.android.maktab6.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.util.UUID;
@Entity
public class User {
    @Id(autoincrement = true)
    private Long _idTableUser;
    private String mName;
//    @Unique
    private String mUserName;
    private String mPassword;
    @Unique
    private String mUserUUID;

    public User() {
        this(UUID.randomUUID());
    }

    public User(UUID id) {
        mUserUUID = id.toString();
    }

    @Generated(hash = 1906006637)
    public User(Long _idTableUser, String mName, String mUserName, String mPassword,
            String mUserUUID) {
        this._idTableUser = _idTableUser;
        this.mName = mName;
        this.mUserName = mUserName;
        this.mPassword = mPassword;
        this.mUserUUID = mUserUUID;
    }

    public Long get_idTableUser() {
        return _idTableUser;
    }

    public void set_idTableUser(Long _idTableUser) {

        this._idTableUser = _idTableUser;
    }

    public String getMName() {
        return this.mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public String getMUserName() {
        return this.mUserName;
    }

    public void setMUserName(String mUserName) {

        this.mUserName = mUserName;
    }

    public String getMPassword() {
        return this.mPassword;
    }

    public void setMPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getMUserUUID() {
        return this.mUserUUID;
    }

    public void setMUserUUID(String mUserUUID) {
        this.mUserUUID = mUserUUID;
    }

}
