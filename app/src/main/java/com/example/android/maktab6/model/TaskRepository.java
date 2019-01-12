package com.example.android.maktab6.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskRepository {
    private static TaskRepository instance;
    private List<Task> mTasks;
    private boolean mDoneChecker;

    private TaskRepository(){
        mTasks = new ArrayList<>();
    }

    public static TaskRepository getInstance(){
        if(instance == null)
            instance = new TaskRepository();
        return instance;
    }
    public List<Task> addToList(Task task){
         mTasks.add(task);
         return mTasks;
    }
    public List<Task> getTasks(){
        return mTasks;
    }
    public Task getTaskById(UUID id){
        for(Task task:mTasks){
            if(task.getId().equals(id)){
                return task;
            }
        }
        return null;
    }
    public void removeAllTasks(){
        mTasks.removeAll(mTasks);
    }
    public List<Task> getDoneTasks(){
        List<Task> mDones = new ArrayList<>();
        for(Task task:mTasks){
            if (!isDoneChecker()) {
                continue;
            }
            getUndones().remove(task);
            mDones.add(task);
        }
        return mDones;
    }
    public List<Task> getUndones(){
        List<Task> undones = new ArrayList<>();
        for(Task task:mTasks){
            if (isDoneChecker()) {
                continue;
            }
            undones.add(task);
        }
        return undones;
    }

    public boolean isDoneChecker() {
        return mDoneChecker;
    }

    public void setDoneChecker(boolean doneChecker) {
        mDoneChecker = doneChecker;
    }
}
