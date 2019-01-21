package com.example.android.maktab6.model;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;

import java.util.Date;
import java.util.UUID;

/**
 * Here is the Task class which has four fields
 * Title, Description, Date and an unique Id...
 */
@Entity
public class Task {
    @Id(autoincrement = true)
    private Long _idTableTask;
    @Unique
    private String mTaskUUId;

    private Long mUserTableId;
    @ToOne(joinProperty = "mUserTableId")
    private User mUser;

    private String mTitle;
    private String mDescription;
    private Date mDate;
    private boolean mDone = false;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1469429066)
    private transient TaskDao myDao;
    @Generated(hash = 1377221062)
    private transient Long mUser__resolvedKey;

    public Task(Long userID){

        this(UUID.randomUUID(), userID);
    }
    public Task(UUID taskId, Long userID){
        mTaskUUId = taskId.toString();
        mUserTableId = userID;
        mDate = new Date();
    }
    @Generated(hash = 666303291)
    public Task(Long _idTableTask, String mTaskUUId, Long mUserTableId,
            String mTitle, String mDescription, Date mDate, boolean mDone) {
        this._idTableTask = _idTableTask;
        this.mTaskUUId = mTaskUUId;
        this.mUserTableId = mUserTableId;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mDate = mDate;
        this.mDone = mDone;
    }
    @Generated(hash = 733837707)


    public Long get_idTableTask() {
        return this._idTableTask;
    }
    public void set_idTableTask(Long _idTableTask) {
        this._idTableTask = _idTableTask;
    }
    public String getMTaskUUId() {
        return this.mTaskUUId;
    }
    public void setMTaskUUId(String mTaskUUId) {
        this.mTaskUUId = mTaskUUId;
    }
    public Long getMUserTableId() {
        return this.mUserTableId;
    }
    public void setMUserTableId(Long mUserTableId) {
        this.mUserTableId = mUserTableId;
    }
    public String getMTitle() {
        return this.mTitle;
    }
    public void setMTitle(String mTitle) {
        this.mTitle = mTitle;
    }
    public String getMDescription() {
        return this.mDescription;
    }
    public void setMDescription(String mDescription) {
        this.mDescription = mDescription;
    }
    public Date getMDate() {
        return this.mDate;
    }
    public void setMDate(Date mDate) {
        this.mDate = mDate;
    }
    public boolean getMDone() {
        return this.mDone;
    }
    public void setMDone(boolean mDone) {
        this.mDone = mDone;
    }
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1642338601)
    public User getMUser() {
        Long __key = this.mUserTableId;
        if (mUser__resolvedKey == null || !mUser__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            User mUserNew = targetDao.load(__key);
            synchronized (this) {
                mUser = mUserNew;
                mUser__resolvedKey = __key;
            }
        }
        return mUser;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1187129349)
    public void setMUser(User mUser) {
        synchronized (this) {
            this.mUser = mUser;
            mUserTableId = mUser == null ? null : mUser.get_idTableUser();
            mUser__resolvedKey = mUserTableId;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1442741304)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTaskDao() : null;
    }
}
