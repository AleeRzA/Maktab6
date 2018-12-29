package com.example.android.maktab6.controller;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.maktab6.R;
import com.example.android.maktab6.model.Task;
import com.example.android.maktab6.model.TaskRepo;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditTaskFragment extends Fragment {


    private static final String TASK_ID = "com.example.android.maktab6.taskId";
    private static final int REQUEST_CODE = 0;
    private Task mTask;
    private TextView mEditText;
    private TextView mDeleteBtn;
    private TextView mEditBtn;
    private TextView mDoneBtn;
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
        mTask = TaskRepo.getInstance().getTaskById(taskId);
        getActivity().setTitle(mTask.getTitle());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_task, container, false);
        mEditText = view.findViewById(R.id.editFrag_desc);
        mDeleteBtn = view.findViewById(R.id.editFrag_deleteBtn);
        mEditBtn = view.findViewById(R.id.editFrag_editBtn);
        mDoneBtn = view.findViewById(R.id.editFrag_doneBtn);

        mDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Hi there", Toast.LENGTH_LONG).show();
            }
        });
        mDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Task is done.", Toast.LENGTH_SHORT).show();
                mTask.setDone(true);
                getActivity().finish();
            }
        });
        mEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Task is edit.", Toast.LENGTH_SHORT).show();
                TaskCreationFragment taskCreationFragment = TaskCreationFragment.newInstance(mTask.getId());
                taskCreationFragment.setTargetFragment(EditTaskFragment.this, REQUEST_CODE);
                getFragmentManager().beginTransaction()
                                    .add(R.id.container_layout, taskCreationFragment)
                                    .commit();
            }
        });
        mEditText.setText(mTask.getDescription());
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode != Activity.RESULT_OK)
            return;
        if(requestCode == REQUEST_CODE){
//            mTask = (Task) data.getSerializableExtra(NEW_TASK);
        }
    }
}

