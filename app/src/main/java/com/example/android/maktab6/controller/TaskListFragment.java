package com.example.android.maktab6.controller;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.maktab6.R;
import com.example.android.maktab6.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskListFragment extends Fragment {


    public static final String ARGS_TAK_ID = "com.example.android.maktab6.controller.args_takId";
    private RecyclerView mRecyclerView;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view_listFragment);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        TaskRepo taskRepo = TaskRepo.getInstance();
//        List<Task> tasks = taskRepo.getTasks();
        TaskAdapter adapter = new TaskAdapter();
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    private class EmptyHolder extends RecyclerView.ViewHolder{
        private ImageView mImageView;
        private TextView mTextView;
        public EmptyHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView_sample_empty);
            mTextView = itemView.findViewById(R.id.textView_sample_empty);
        }
        public void bind(){
            mImageView.setVisibility(View.VISIBLE);
            mTextView.setVisibility(View.VISIBLE);
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
            View view = inflater.inflate(R.layout.sample_fragment_task_empty, parent, false);
            EmptyHolder emptyHolder = new EmptyHolder(view);
            return emptyHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((EmptyHolder)holder).bind();
        }

        @Override
        public int getItemCount() {
            return 1;
        }

//        @Override
//        public int getItemViewType(int position) {
//            if(mTasks == null)
//                return 0;
//            return 1;
//            }
        }
    }


