package com.example.android.maktab6.controller;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.maktab6.R;
import com.example.android.maktab6.model.LoginUser;
import com.example.android.maktab6.model.Task;
import com.example.android.maktab6.model.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskListFragment extends Fragment {


    public static final String ARGS_TAK_ID = "com.example.android.maktab6.controller.args_takId";
    private static final String SHOW_TASK = "show_task";
    private static final int REQUEST_CODE_TASK_LIST = 31;
    private static final String TASK_UUID = "task_uuid";


    private RecyclerView mRecyclerView;
    private List<Task> mTaskLists;
    private TaskAdapter mTaskAdapter;
    private TextView mEmptyText;
    private ImageView mEmptyImage;
    private int _position;
    private Task mTask;
    private UUID mTaskUUID;
    private TaskRepository mRepository;
    private int _viewId;

    public TaskListFragment() {
        // Required empty public constructor
    }

    public static TaskListFragment newInstance(int viewId, UUID taskId) {
        Bundle args = new Bundle();
        args.putInt(ARGS_TAK_ID, viewId);
        args.putSerializable(TASK_UUID, taskId);
        TaskListFragment fragment = new TaskListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = TaskRepository.getInstance(getActivity());
        _viewId = getArguments().getInt(ARGS_TAK_ID);
        viewChecker(mRepository, _viewId);
        if (getArguments().getSerializable(TASK_UUID) != null) {
            mTaskUUID = (UUID) getArguments().getSerializable(TASK_UUID);
            mTask = mRepository.getTaskById(mTaskUUID);
        } else {
            mTask = new Task(LoginUser.userLogin);
        }
        setHasOptionsMenu(true);
    }

    private void viewChecker(TaskRepository repository, int viewId) {
        if(viewId == 0){
            mTaskLists = repository.getTasks();
            Log.i("mTaskList", "task list position zero: " + viewId + " -- " +mTaskLists.size());
        }
        if (viewId == 1){
            mTaskLists = repository.getDoneTasks();
            Log.i("mTaskList", "task list position one: " + viewId + " -- " +mTaskLists.size());
        }
        if (viewId == 2){
            mTaskLists = repository.getUndoneTasks();
            Log.i("mTaskList", "task list position two: " + viewId + " -- " +mTaskLists.size());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_view_listFragment);
        mEmptyText = view.findViewById(R.id.textView_sample_empty);
        mEmptyImage = view.findViewById(R.id.imageView_sample_empty);

        if(mTaskLists.isEmpty()) {
            mRecyclerView.setVisibility(View.GONE);

        }else {
            mEmptyText.setVisibility(View.GONE);
            mEmptyImage.setVisibility(View.GONE);
        }
        mRecyclerView
                .addItemDecoration(
                        new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_task_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== R.id.removeAll_btn) {
            updateUI();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK)
            return;
        if(requestCode == REQUEST_CODE_TASK_LIST){
            updateUI();
        }
    }

    private void removeAll() {
        mTaskLists.clear();
        mEmptyText.setVisibility(View.VISIBLE);
        mEmptyImage.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }


    private void updateUI() {

        if (mRecyclerView != null) {
            if (mTaskAdapter == null) {
                mTaskAdapter = new TaskAdapter(mTaskLists);
                mRecyclerView.setAdapter(mTaskAdapter);
            } else {
                mTaskAdapter.setTasks(mTaskLists);
                mTaskAdapter.notifyDataSetChanged();
            }
        }
    }


    //--------------------------------------------------/
    private class SampleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextTitle;
        private TextView mChar;


        public SampleHolder(View itemView) {
            super(itemView);
            mTextTitle = itemView.findViewById(R.id.sampleTask_textView);
            mChar = itemView.findViewById(R.id.sampleTask_circleShapeView);

            itemView.setOnClickListener(this);

        }
        public void bindSample(Task task){
            mTask = task;
            String title = task.getTitle();
            mTextTitle.setText(title);
            char firstChar = title.charAt(0);
            mChar.setText(String.valueOf(firstChar));
        }
        @Override
        public void onClick(View view) {
            _position = getAdapterPosition();
            EditTaskFragment editTaskFragment = EditTaskFragment.newInstance(mTask.getTaskUUId());
            editTaskFragment.setTargetFragment(TaskListFragment.this, REQUEST_CODE_TASK_LIST);
            editTaskFragment.show(getFragmentManager(), SHOW_TASK);
        }
    }
    //--------------------------------------------------/
    private class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private List<Task> mTasks = new ArrayList<>();

        public TaskAdapter(){

        }
        public TaskAdapter(List<Task> tasks){
            mTasks = tasks;
        }

        public List<Task> getTasks() {
            return mTasks;
        }
        public void setTasks(List<Task> tasks) {
            mTasks = tasks;
        }
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
                View view = inflater.inflate(R.layout.sample_fragment_task, parent, false);
                SampleHolder sampleHolder = new SampleHolder(view);
                return sampleHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            Task task = mTasks.get(position);
            ((SampleHolder)holder).bindSample(task);
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }

    }
    }


