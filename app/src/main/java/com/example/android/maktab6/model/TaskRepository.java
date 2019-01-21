package com.example.android.maktab6.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.android.maktab6.database.DBSchema;
import com.example.android.maktab6.database.DataBaseHelper;
import com.example.android.maktab6.database.TaskCursorWrapper;
import com.example.android.maktab6.database.UserCursorWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskRepository {
    public static final String TAG_LOG_USER = "TAG_LOG_USER";
    private static TaskRepository instance;
    private SQLiteDatabase mDatabase;
    private Context mContext;


    private TaskRepository(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new DataBaseHelper(mContext).getWritableDatabase();
    }

    public static TaskRepository getInstance(Context context) {
        if (instance == null)
            instance = new TaskRepository(context);
        return instance;
    }

    public ContentValues getTaskContentValues(Task task) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBSchema.TaskTable.TaskColumns.UUID, task.getTaskUUId().toString());
        contentValues.put(DBSchema.TaskTable.TaskColumns.TITLE, task.getTitle());
        contentValues.put(DBSchema.TaskTable.TaskColumns.DESCRIPTION, task.getDescription());
        contentValues.put(DBSchema.TaskTable.TaskColumns.DATE, task.getDate().getTime());
        contentValues.put(DBSchema.TaskTable.TaskColumns.DONE, task.isDone() ? 1 : 0);
        contentValues.put(DBSchema.TaskTable.TaskColumns.USER_ID, task.getUserTableId());
        return contentValues;
    }

    public ContentValues getUserContentValues(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBSchema.UserTable.UserColumns.UUID, user.getUserUUID().toString());
        contentValues.put(DBSchema.UserTable.UserColumns.NAME, user.getName());
        contentValues.put(DBSchema.UserTable.UserColumns.EMAIL, user.getUserName());
        contentValues.put(DBSchema.UserTable.UserColumns.PASSWORD, user.getPassword());
        return contentValues;
    }

    public TaskCursorWrapper queryTask(String whereClause, String[] whereArgs) {
        Cursor taskCursor = mDatabase.query(
                DBSchema.TaskTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);

        return new TaskCursorWrapper(taskCursor, mContext);
    }

    public UserCursorWrapper queryUser(String where, String[] whereClause) {
        Cursor cursor = mDatabase.query(
                DBSchema.UserTable.NAME,
                null,
                where,
                whereClause,
                null,
                null,
                null);
        return new UserCursorWrapper(cursor);
    }

    public long addTaskToList(Task task) {
       return mDatabase.insert(DBSchema.TaskTable.NAME, null, getTaskContentValues(task));
    }

    public long addNewUser(User user) {
        Log.i(TAG_LOG_USER, "user added...");
        return mDatabase.insert(DBSchema.UserTable.NAME, null, getUserContentValues(user));
    }

    public void removeAllTasks(UUID uuid) {
//        String whereClause = DBSchema.TaskTable.TaskColumns.USER_ID + " = ?";
//        String[] whereArgs = new String[]{uuid.toString()};
        mDatabase.delete(DBSchema.TaskTable.NAME, null, null);
    }

    public List<Task> getTasks() {
        List<Task> mTasks = new ArrayList<>();
        String whereClause = DBSchema.TaskTable.TaskColumns.USER_ID + " = " + LoginUser.userLogin;

        TaskCursorWrapper cursorWrapper = queryTask(whereClause, null);
        try {
            if (cursorWrapper.getCount() == 0)
                return mTasks;
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                Task task = cursorWrapper.getTasks();
                mTasks.add(task);
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }
        return mTasks;
    }

    public Task getTaskById(UUID id) {
        String whereClause = DBSchema.TaskTable.TaskColumns.UUID + " = ?";
        String[] whereArgs = new String[]{id.toString()};
        TaskCursorWrapper taskCursorWrapper = queryTask(whereClause, whereArgs);
        try {
            if (taskCursorWrapper.getCount() == 0)
                return null;
            taskCursorWrapper.moveToFirst();
            return taskCursorWrapper.getTasks();
        } finally {
            taskCursorWrapper.close();
        }
    }

    public List<Task> getDoneTasks() {
        List<Task> doneTask = new ArrayList<>();
        String whereClause = DBSchema.TaskTable.TaskColumns.USER_ID + " = " + LoginUser.userLogin + " AND " +
                DBSchema.TaskTable.TaskColumns.DONE + " = ?";
        String[] whereArgs = new String[]{"1"};
        TaskCursorWrapper taskCursorWrapper = queryTask(whereClause, whereArgs);
        try {
            if (taskCursorWrapper.getCount() == 0) {
                return doneTask;
            }
            taskCursorWrapper.moveToFirst();
            while (!taskCursorWrapper.isAfterLast()) {
                Task taskDone = taskCursorWrapper.getTasks();
                doneTask.add(taskDone);
                taskCursorWrapper.moveToNext();
            }
        } finally {
            taskCursorWrapper.close();
        }
        return doneTask;
    }

    public List<Task> getUndoneTasks() {
        List<Task> undoneTask = new ArrayList<>();
        String whereClause = DBSchema.TaskTable.TaskColumns.USER_ID + " = " + LoginUser.userLogin + " AND " +
                DBSchema.TaskTable.TaskColumns.DONE + " = ?";
        String[] whereArgs = new String[]{"0"};
        TaskCursorWrapper taskCursorWrapper = queryTask(whereClause, whereArgs);
        try {
            if (taskCursorWrapper.getCount() == 0) {
                return undoneTask;
            }
            taskCursorWrapper.moveToFirst();
            while (!taskCursorWrapper.isAfterLast()) {
                Task taskUndone = taskCursorWrapper.getTasks();
                undoneTask.add(taskUndone);
                taskCursorWrapper.moveToNext();
            }
        } finally {
            taskCursorWrapper.close();
        }
        return undoneTask;
    }

    public Long validateUser(String username, String password) {
        String whereClause = DBSchema.UserTable.UserColumns.EMAIL + " = ? " + "AND " +
                DBSchema.UserTable.UserColumns.PASSWORD + " = ?";
        String[] whereArgs = new String[]{username, password};
        UserCursorWrapper userCursorWrapper = queryUser(whereClause, whereArgs);
        try {
            if (userCursorWrapper.getCount() == 0) {
                return null;
            }
            userCursorWrapper.moveToFirst();
            LoginUser.userLogin = userCursorWrapper.getUser().get_idTableUser();
            return LoginUser.userLogin;
        } finally {
            userCursorWrapper.close();
        }
    }

    public void update(Task task) {
        mDatabase.update(
                DBSchema.TaskTable.NAME,
                getTaskContentValues(task),
                DBSchema.TaskTable.TaskColumns.UUID + " = ? ",
                new String[]{task.getTaskUUId().toString()});
    }

}
