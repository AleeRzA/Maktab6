package com.example.android.maktab6.model;

import android.content.Context;
import android.util.Log;

import com.example.android.maktab6.greendao.App;

import java.util.List;
import java.util.UUID;

public class TaskRepository {
    public static final String TAG_LOG_USER = "TAG_LOG_USER";
    private static TaskRepository instance;
    private Context mContext;
    private UserDao mUserDao;
    private TaskDao mTaskDao;

    private TaskRepository(Context context) {
        DaoSession daoSession = (App.getApp()).getDaoSession();
        mUserDao = daoSession.getUserDao();
        mTaskDao = daoSession.getTaskDao();

        mContext = context.getApplicationContext();
    }

    public static TaskRepository getInstance(Context context) {
        if (instance == null)
            instance = new TaskRepository(context);
        return instance;
    }

    public long addTaskToList(Task task) {
       return mTaskDao.insert(task);
    }

    public long addNewUser(User user) {
        Log.i(TAG_LOG_USER, "user added...");
        if(mUserDao.hasKey(user))
            return user.get_idTableUser();
        return mUserDao.insertOrReplace(user);
    }

    public void removeAllTasks(UUID uuid) {
//        String whereClause = DBSchema.TaskTable.TaskColumns.USER_ID + " = ?";
//        String[] whereArgs = new String[]{uuid.toString()};
//        mDatabase.delete(DBSchema.TaskTable.NAME, null, null);
    }

    public List<Task> getTasks() {
        return mTaskDao.loadAll();
    }

    public Task getTaskById(UUID id) {
        return mTaskDao.queryBuilder()
                .where(TaskDao.Properties.MTaskUUId.eq(id.toString())).limit(1).unique();
    }

    public List<Task> getDoneTasks() {
         List<Task> _doneList = mTaskDao.queryBuilder()
                                   .where(TaskDao.Properties.MUserTableId.eq(LoginUser.userLogin)
                                   , TaskDao.Properties.MDone.eq(true)).build().list();
        return _doneList;
    }

    public List<Task> getUndoneTasks() {
        List<Task> _undoneList = mTaskDao.queryBuilder()
                .where(TaskDao.Properties.MUserTableId.eq(LoginUser.userLogin)
                        , TaskDao.Properties.MDone.eq(false)).build().list();
        return _undoneList;
    }

    public Long validateUser(String username, String password) {
        LoginUser.userLogin = mUserDao.queryBuilder().where(UserDao.Properties.MUserName.eq(username)
                                      , UserDao.Properties.MPassword.eq(password)).build()
                                      .unique().get_idTableUser();
        return LoginUser.userLogin;
    }

    public void update(Task task) {
        mTaskDao.update(
        mTaskDao.queryBuilder()
                .where(TaskDao.Properties.MTaskUUId.eq(task.getMTaskUUId())).limit(1).unique()
        );
    }

}
