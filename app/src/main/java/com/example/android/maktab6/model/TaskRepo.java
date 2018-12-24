package com.example.android.maktab6.model;

public class TaskRepo {
    private static TaskRepo instance;
    private TaskRepo(){

    }
    public static TaskRepo getInstance(){
        if(instance == null)
            instance = new TaskRepo();
        return instance;
    }
}
