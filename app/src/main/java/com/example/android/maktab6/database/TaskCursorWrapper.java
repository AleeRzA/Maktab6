package com.example.android.maktab6.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.android.maktab6.model.Task;

public class TaskCursorWrapper extends CursorWrapper {

    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Task getTasks(){
        return null;
    }
}
