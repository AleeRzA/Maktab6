package com.example.android.maktab6.controller;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.maktab6.R;
import com.example.android.maktab6.model.Task;
import com.example.android.maktab6.model.TaskRepo;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskCreationFragment extends Fragment {

    private EditText mTitle;
    private EditText mDescription;
    private Button mCreateBtn;
    private List<Task> mTaskList;
    private Task newTask;
    public TaskCreationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskList = TaskRepo.getInstance().getTasks();
        newTask = new Task();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_creation, container, false);
        mTitle = view.findViewById(R.id.creation_title);
        mDescription = view.findViewById(R.id.creation_description);
        mCreateBtn = view.findViewById(R.id.creation_btn_create);

        return view;
    }

}
