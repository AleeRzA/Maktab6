package com.example.android.maktab6.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskRepo {
    private static TaskRepo instance;
    private List<Task> mTasks;
    private TaskRepo(){
        mTasks = new ArrayList<>();
    }
    public static TaskRepo getInstance(){
        if(instance == null)
            instance = new TaskRepo();
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
    public List<Task> removeTask(UUID uuid){
        for(Task task:mTasks){
            if(task.getId().equals(uuid)){
                mTasks.remove(task);
            }
        }
        return mTasks;
    }
}
