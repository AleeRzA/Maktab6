package com.example.android.maktab6.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.android.maktab6.R;
import com.example.android.maktab6.model.Task;
import com.example.android.maktab6.model.TaskRepo;

import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    public static final String REAL_TASK_CREATION = "realTaskCreation";
    public static final String TASK_LIST_FRAGMENT = "taskListFragment";
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private FloatingActionButton mActionButton;
    private List<Task> mTasks;
    private FragmentStatePagerAdapter mAdapter;

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, ViewPagerActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.main_view_pager);
        mTabLayout = findViewById(R.id.main_tabLayout);
        mTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mActionButton = findViewById(R.id.main_floating_btn);


        mTabLayout.setupWithViewPager(mViewPager);
        mAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return TaskListFragment.newInstance(position);
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

        };

        mViewPager.setAdapter(mAdapter);

        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RealEditTaskFragment realEditTaskFragment = RealEditTaskFragment.newInstance();
                realEditTaskFragment.show(getSupportFragmentManager(), REAL_TASK_CREATION);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTasks = TaskRepo.getInstance().getTasks();
        if(!mTasks.isEmpty()){
            mAdapter.notifyDataSetChanged();
        }
    }


}
