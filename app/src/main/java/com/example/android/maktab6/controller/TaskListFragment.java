package com.example.android.maktab6.controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.maktab6.R;
import com.example.android.maktab6.model.Task;
import com.example.android.maktab6.model.TaskRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskListFragment extends Fragment {


    public static final String ARGS_TAK_ID = "com.example.android.maktab6.controller.args_takId";
    public static final int REQ_INT = 111;
    private RecyclerView mRecyclerView;
    private List<Task> mTaskLists;
    private TaskAdapter mTaskAdapter;
    public TaskListFragment() {
        // Required empty public constructor
    }

    public static TaskListFragment newInstance() {
        Bundle args = new Bundle();
//        args.putSerializable(ARGS_TAK_ID, taskId);
        TaskListFragment fragment = new TaskListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskLists = TaskRepo.getInstance().getTasks();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view_listFragment);
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

    private void updateUI() {
        TaskRepo taskRepo = TaskRepo.getInstance();
        mTaskLists = taskRepo.getTasks();
        mTaskAdapter  = new TaskAdapter(mTaskLists);
        mRecyclerView.setAdapter(mTaskAdapter);
    }


    //--------------------------------------------------/
    private class SampleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextTitle;
        private TextView mChar;
        private Task mTask;

        public SampleHolder(View itemView) {
            super(itemView);
            mTextTitle = itemView.findViewById(R.id.sampleTask_textView);
            mChar = itemView.findViewById(R.id.sampleTask_imageView);
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
//            Toast.makeText(getActivity(), mTask.getTitle(), Toast.LENGTH_SHORT).show();
            Intent intent = EditTaskActivity.newIntent(getActivity(), mTask.getId());
            startActivityForResult(intent, REQ_INT);
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

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }
    }
    }


