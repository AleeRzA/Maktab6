package com.example.android.maktab6.controller;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.maktab6.R;
import com.example.android.maktab6.model.Task;
import com.example.android.maktab6.model.TaskRepository;

import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditTaskFragment extends DialogFragment {


    private static final String TASK_ID = "com.example.android.maktab6.taskId";
    private static final String REAL_EDIT_TASK = "real_edit_task";
    public static final int REQ_CODE_REAL_EDITTASK = 33;


    private Task mTask;
    private TextView mTitleText;
    private TextView mDescriptionText;
    private TextView mDateText;
    private TextView mTimeText;
    private Button mEditButton;
    private Button mOkButton;
    private UUID mTaskUUID;
    private TextView mDoneChecker;



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
        mTaskUUID = (UUID) getArguments().getSerializable(TASK_ID);
        mTask = TaskRepository.getInstance(getActivity()).getTaskById(mTaskUUID);
        Log.i("tas_finding", "Task: " + mTask + " Task id: " + mTaskUUID);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_task, container, false);
        mTitleText = view.findViewById(R.id.editFrag_title);
        mDescriptionText = view.findViewById(R.id.editFrag_desc);
        mDateText = view.findViewById(R.id.editFrag_date);
        mTimeText = view.findViewById(R.id.editFrag_time);
        mEditButton = view.findViewById(R.id.editFrag_btn_edit);
        mOkButton = view.findViewById(R.id.editFrag_btn_ok);
        mDoneChecker = view.findViewById(R.id.doneChecker_text);

        mTitleText.setText(mTask.getMTitle());
        mDescriptionText.setText(mTask.getMDescription());
        mDateText.setText(new SimpleDateFormat("yyyy-MMM-dd").format(mTask.getMDate()));
        mTimeText.setText(new SimpleDateFormat("hh:mm a").format(mTask.getMDate()));
        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RealEditTaskFragment realEditTaskFragment = RealEditTaskFragment.newInstance(mTaskUUID);
                realEditTaskFragment.setTargetFragment(EditTaskFragment.this, REQ_CODE_REAL_EDITTASK);
                realEditTaskFragment.show(getFragmentManager(), REAL_EDIT_TASK);
                EditTaskFragment.this.dismiss();
            }
        });
        if(mTask.getMDone()){
            mDoneChecker.setText(R.string.task_isDone);
        }else {
            mDoneChecker.setText(R.string.task_notDone);
        }
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;

    }

}

