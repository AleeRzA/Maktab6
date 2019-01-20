package com.example.android.maktab6.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.android.maktab6.R;
import com.example.android.maktab6.model.LoginUser;

import java.util.UUID;

public class ViewPagerActivity extends AppCompatActivity {

    public static final String TAG = "ViewPagerActivity_TAG";
    public static final String REAL_TASK_CREATION = "realTaskCreation";
    private static final int REQUEST_CODE_TASKLIST = 31;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private FloatingActionButton mActionButton;
    private FragmentStatePagerAdapter mAdapter;
    private int mUserId;

    private UUID mTaskUUID;

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, ViewPagerActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserId = LoginUser.userLogin;
        Log.i(TAG, "onCreate: " + mUserId);

        mViewPager = findViewById(R.id.main_view_pager);
        mTabLayout = findViewById(R.id.main_tabLayout);
        mTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mActionButton = findViewById(R.id.main_floating_btn);

        mTabLayout.setupWithViewPager(mViewPager);
        mAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                return TaskListFragment.newInstance(position, mTaskUUID);
            }

            @Override
            public int getCount() {
                return 3;
            }
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                String title = "";
                switch (position){
                    case 0:
                        title = "ALL";
                        break;
                    case 1:
                        title = "DONE";
                        break;
                    case 2:
                        title = "UNDONE";
                        break;
                }
                return title;
            }

            @Override
            public int getItemPosition(@NonNull Object object) {
                return POSITION_NONE;
            }
        };

        mViewPager.setAdapter(mAdapter);

        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RealEditTaskFragment realEditTaskFragment
                        = RealEditTaskFragment.newInstance(null);
                realEditTaskFragment.show(getSupportFragmentManager(), REAL_TASK_CREATION);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
        for (int i = 0; i < mAdapter.getCount(); i++) {
            mAdapter.getItem(i).onResume();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_CODE_TASKLIST){
            mTaskUUID = (UUID) data.getSerializableExtra(RealEditTaskFragment.getTaskUuid());
            Log.i("view_pager", "Check task Id: " + mTaskUUID);
        }
    }
}
