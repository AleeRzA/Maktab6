package com.example.android.maktab6.controller;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.maktab6.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        LoginFragment fragment = LoginFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.findFragmentById(R.id.editTask_login_container) == null){
            fragmentManager.beginTransaction()
                    .add(R.id.editTask_login_container, fragment)
                    .commit();
        }
    }
}
