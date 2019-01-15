package com.example.android.maktab6.controller;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.maktab6.R;
import com.example.android.maktab6.model.Task;
import com.example.android.maktab6.model.TaskRepository;
import com.example.android.maktab6.model.User;

import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditTaskFragment extends DialogFragment {


    private static final String TASK_ID = "com.example.android.maktab6.taskId";
    private static final String USER_ID = "com.example.android.maktab6.userId";
    private static final String REAL_EDIT_TASK = "real_edit_task";
    public static final int REQUEST_CODE_EDITTASK = 33;


    private Task mTask;
    private TextView mTitleText;
    private TextView mDescriptionText;
    private TextView mDateText;
    private TextView mTimeText;
    private Button mEditButton;
    private Button mOkButton;
    private UUID mUUID;
    private User mUser;
    private UUID mUserUUID;

    public EditTaskFragment() {
        // Required empty public constructor
    }


    public static EditTaskFragment newInstance(UUID taskId, UUID userId) {
        Bundle args = new Bundle();
        args.putSerializable(TASK_ID, taskId);
        args.putSerializable(USER_ID, userId);
        EditTaskFragment fragment = new EditTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUUID = (UUID) getArguments().getSerializable(TASK_ID);
        mTask = TaskRepository.getInstance(getActivity()).getTaskById(mUUID);
        mUserUUID = (UUID) getArguments().getSerializable(USER_ID);
        mUser = TaskRepository.getInstance(getActivity()).getUserById(mUserUUID);
        getActivity().setTitle(mTask.getTitle());
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

        mTitleText.setText(mTask.getTitle());
        mDescriptionText.setText(mTask.getDescription());
        mDateText.setText(new SimpleDateFormat("yyyy-MMM-dd").format(mTask.getDate()));
        mTimeText.setText(new SimpleDateFormat("hh:mm a").format(mTask.getDate()));
        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RealEditTaskFragment realEditTaskFragment = RealEditTaskFragment.newInstance(mUUID, mUserUUID);
                setTargetFragment(EditTaskFragment.this, REQUEST_CODE_EDITTASK);
                realEditTaskFragment.show(getFragmentManager(), REAL_EDIT_TASK);
            }
        });
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;

    }

}

