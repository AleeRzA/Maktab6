package com.example.android.maktab6.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.maktab6.R;

public class TaskCreationActivity extends AppCompatActivity {

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, TaskCreationActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation);

        TaskCreationFragment fragment = new TaskCreationFragment();
        FragmentManager manager = getSupportFragmentManager();
        if(manager.findFragmentById(R.id.container_layout) == null){
            manager.beginTransaction()
                    .add(R.id.container_layout, fragment)
                    .commit();
        }
    }
}
