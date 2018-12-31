package com.example.android.maktab6.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.maktab6.R;

import java.util.UUID;

public class TaskCreationActivity extends AppCompatActivity {
    private static final String TASK_UUID = "com.example.android.maktab6.controller.task_uuid";
    private UUID mTaskId;

    public static Intent newIntent(Context context, UUID id){
        Intent intent = new Intent(context, TaskCreationActivity.class);
        intent.putExtra(TASK_UUID,id);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation);
        mTaskId = (UUID) getIntent().getSerializableExtra(TASK_UUID);

        FragmentManager manager = getSupportFragmentManager();
        if (manager.findFragmentById(R.id.container_layout) == null) {
            manager.beginTransaction()
                    .add(R.id.container_layout, TaskCreationFragment.newInstance(mTaskId))
                    .commit();
        }

    }
}
