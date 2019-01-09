package com.example.android.maktab6.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.android.maktab6.model.Task;

import java.util.Date;
import java.util.UUID;

public class TaskCursorWrapper extends CursorWrapper {

    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Task getTasks(){
        UUID uuid = UUID.fromString(getString(getColumnIndex(DBSchema.TaskTable.TaskColumns.UUID)));
        String title = getString(getColumnIndex(DBSchema.TaskTable.TaskColumns.TITLE));
        String description = getString(getColumnIndex(DBSchema.TaskTable.TaskColumns.DESCRIPTION));
        Date date = new Date(getLong(getColumnIndex(DBSchema.TaskTable.TaskColumns.DATE)));
        boolean done = getInt(getColumnIndex(DBSchema.TaskTable.TaskColumns.DONE)) != 0;
        int userId = getInt(getColumnIndex(DBSchema.TaskTable.TaskColumns.USER_ID));

        Task task = new Task(uuid);
        task.setTitle(title);
        task.setDescription(description);
        task.setDate(date);
        task.setDone(done);
        task.setUserId(userId);

        return task;
    }
}
