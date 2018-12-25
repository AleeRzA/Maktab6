package com.example.android.maktab6.controller;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.maktab6.R;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditTaskFragment extends Fragment {


    private static final String TASK_ID = "com.example.android.maktab6.taskId";

    public EditTaskFragment() {
        // Required empty public constructor
    }

    public static EditTaskFragment newInstance(UUID taskId) {
        
        Bundle args = new Bundle();
        args.putSerializable(TASK_ID, taskId);
        EditTaskFragment fragment = new EditTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID taskId = (UUID) getArguments().getSerializable(TASK_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_task, container, false);

        return view;
    }

}
