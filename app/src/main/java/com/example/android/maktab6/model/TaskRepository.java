package com.example.android.maktab6.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.maktab6.database.DBSchema;
import com.example.android.maktab6.database.DataBaseHelper;
import com.example.android.maktab6.database.TaskCursorWrapper;
import com.example.android.maktab6.database.UserCursorWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskRepository {
    private static TaskRepository instance;
    private SQLiteDatabase mDatabase;
    private Context mContext;


    private TaskRepository(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new DataBaseHelper(mContext).getWritableDatabase();
    }

    public static TaskRepository getInstance(Context context){
        if(instance == null)
            instance = new TaskRepository(context);
        return instance;
    }
    public ContentValues getTaskContentValues(Task task){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBSchema.TaskTable.TaskColumns.UUID, task.getId().toString());
        contentValues.put(DBSchema.TaskTable.TaskColumns.TITLE, task.getTitle());
        contentValues.put(DBSchema.TaskTable.TaskColumns.DESCRIPTION, task.getDescription());
        contentValues.put(DBSchema.TaskTable.TaskColumns.DATE, task.getDate().getTime());
        contentValues.put(DBSchema.TaskTable.TaskColumns.DONE, task.isDone() ? 1 : 0);
        contentValues.put(DBSchema.TaskTable.TaskColumns.USER_ID, task.getUserId());
        return contentValues;
    }
    public ContentValues getUserContentValues(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBSchema.UserTable.UserColumns.UUID, user.getId().toString());
        contentValues.put(DBSchema.UserTable.UserColumns.NAME, user.getName());
        contentValues.put(DBSchema.UserTable.UserColumns.EMAIL, user.getUserName());
        contentValues.put(DBSchema.UserTable.UserColumns.PASSWORD, user.getPassword());
        return contentValues;
    }
    public TaskCursorWrapper queryTask(String whereClause, String[] whereArgs){
        Cursor taskCursor = mDatabase.query(
                DBSchema.TaskTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new TaskCursorWrapper(taskCursor);
    }
    public UserCursorWrapper queryUser(String where, String[] whereClause){
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
    public void addTaskToList(Task task){
         mDatabase.insert(DBSchema.TaskTable.NAME, null,getTaskContentValues(task));
    }
    public void addNewUser(User user){
        mDatabase.insert(DBSchema.UserTable.NAME, null, getUserContentValues(user));
    }
    public void removeAllTasks(UUID uuid){
//        String whereClause = DBSchema.TaskTable.TaskColumns.USER_ID + " = ?";
//        String[] whereArgs = new String[]{uuid.toString()};
        mDatabase.delete(DBSchema.TaskTable.NAME,   null,    null);
    }
    public List<Task> getTasks(){
        List<Task> mTasks = new ArrayList<>();
        String whereClause = DBSchema.TaskTable.TaskColumns.USER_ID + " = ?";
        String[] whereArgs = new String[]{};
        TaskCursorWrapper cursorWrapper = queryTask(null, null);
        try {
            if(cursorWrapper.getCount() == 0)
                return mTasks;
            cursorWrapper.moveToFirst();
            while(!cursorWrapper.isAfterLast()){
                Task task = cursorWrapper.getTasks();
                mTasks.add(task);
                cursorWrapper.moveToNext();
            }
        }finally {
            cursorWrapper.close();
        }
        return mTasks;
    }
    public Task getTaskById(UUID id){
        String whereClause = DBSchema.TaskTable.TaskColumns.UUID + " = ?";
        String[] whereArgs = new String[]{id.toString()};
        TaskCursorWrapper taskCursorWrapper = queryTask(whereClause, whereArgs);
        try {
            if(taskCursorWrapper.getCount() == 0)
                return null;
            taskCursorWrapper.moveToFirst();
            return taskCursorWrapper.getTasks();
        }finally {
            taskCursorWrapper.close();
        }
    }
    public User getUserById(UUID userId){
        String whereClause = DBSchema.UserTable.UserColumns.UUID + " = ?";
        String[] whereArgs = new String[]{userId.toString()};
        UserCursorWrapper userCursorWrapper = queryUser(whereClause, whereArgs);
        try{
            if(userCursorWrapper.getCount() == 0)
                return null;
            userCursorWrapper.moveToFirst();
            return userCursorWrapper.getUser();
        }finally {
            userCursorWrapper.close();
        }
    }
//    public List<Task> getDoneTasks(Task task){
//        List<Task> mDones = new ArrayList<>();
//        String whereClause = DBSchema.TaskTable.TaskColumns.DONE + " = ?";
//        String[] whereArgs = new String[]{task.isDone()}
//        TaskCursorWrapper taskCursorWrapper = queryTask()
//        return mDones;
//    }
//    public List<Task> getUndones(){
//        List<Task> undones = new ArrayList<>();
//        for(Task task:mTasks){
//            if (isDoneChecker()) {
//                continue;
//            }
//            undones.add(task);
//        }
//        return undones;
//    }
}
