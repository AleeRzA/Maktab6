package com.example.android.maktab6.controller;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.maktab6.R;
import com.example.android.maktab6.model.Task;
import com.example.android.maktab6.model.TaskRepo;

import java.util.List;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskCreationFragment extends Fragment  {

    private static final String TASK_ID = "task_id";
    private EditText mTitle;
    private EditText mDescription;
    private Button mCreateBtn;
    private List<Task> mTaskList;
    private static boolean isEditFrag = false;

    private Task newTask;

    public TaskCreationFragment() {
        // Required empty public constructor
    }

    public static TaskCreationFragment newInstance(UUID taskId) {

        Bundle args = new Bundle();
        args.putSerializable(TASK_ID,taskId);
        TaskCreationFragment fragment = new TaskCreationFragment();
        fragment.setArguments(args);
        isEditFrag = true;
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskList = TaskRepo.getInstance().getTasks();

        newTask = new Task();
        if(isEditFrag) {
            Task task = (Task) getArguments().getSerializable(TASK_ID);
            newTask = task;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_creation, container, false);
        mTitle = view.findViewById(R.id.creation_title);
        mDescription = view.findViewById(R.id.creation_description);
        mCreateBtn = view.findViewById(R.id.creation_btn_create);

        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                newTask.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                newTask.setDescription(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskRepo.getInstance().addToList(newTask);
//                Intent intent = ViewPagerActivity.newIntent(getActivity());
//                startActivity(intent);
                getActivity().onBackPressed();
                Toast.makeText(getActivity(), R.string.task_saved_message, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
