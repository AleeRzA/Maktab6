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
    public static final String TASK_EDITED = "task_edited";
    private EditText mTitle;
    private EditText mDescription;
    private Button mCreateBtn;
    private List<Task> mTaskList;
    private UUID mTaskId;
    private Task mTask;
    private TaskRepo mRepository;
    private boolean isNew = false;

    public TaskCreationFragment() {
        // Required empty public constructor
    }

    public static TaskCreationFragment newInstance(UUID taskId) {

        TaskCreationFragment fragment = new TaskCreationFragment();
        if(taskId != null){
            Bundle args = new Bundle();
            args.putSerializable(TASK_ID,taskId);
            fragment.setArguments(args);
        }
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = TaskRepo.getInstance();
        mTaskList = mRepository.getTasks();
        Bundle arguments = getArguments();
        if(arguments != null && arguments.containsKey(TASK_ID)) {

            mTaskId = (UUID) getArguments().getSerializable(TASK_ID);
            mTask = mRepository.getTaskById(mTaskId);

        }
        else if(savedInstanceState != null)
            return;
        else {
            mTask = new Task();
            mTaskId = mTask.getId();
            isNew = true;
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

        mTitle.setText(mTask.getTitle());
        mDescription.setText(mTask.getDescription());
        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTask.setTitle(charSequence.toString());
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
                mTask.setDescription(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNew){
                    mRepository.addToList(mTask);
                }

//                getActivity().setResult(Activity.RESULT_OK,
//                        getActivity().setIntent(new Intent(TASK_EDITED, mTask.getId())));
                getActivity().onBackPressed();
            }
        });
        return view;
    }

}
