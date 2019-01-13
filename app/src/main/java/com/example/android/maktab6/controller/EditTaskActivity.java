package com.example.android.maktab6.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;

public class EditTaskActivity extends AppCompatActivity {

    private static final String TASK_ID = "com.example.android.maktab6.controller.taskId";

    public static Intent newIntent(Context context, UUID taskId){
        Intent intent = new Intent(context, EditTaskActivity.class);
        intent.putExtra(TASK_ID,taskId);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_task);
//        UUID taskId = (UUID) getIntent().getSerializableExtra(TASK_ID);
//        User user = new User();
//        EditTaskFragment fragment = EditTaskFragment.newInstance(taskId, user.getId());
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        if(fragmentManager.findFragmentById(R.id.editTask_login_container) == null){
//            fragmentManager.beginTransaction()
//                    .add(R.id.editTask_login_container, fragment)
//                    .commit();
//        }
    }
}
