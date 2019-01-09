package com.example.android.maktab6.controller;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.maktab6.R;
import com.example.android.maktab6.model.Task;
import com.example.android.maktab6.model.TaskRepo;

import java.text.SimpleDateFormat;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class RealEditTaskFragment extends DialogFragment implements View.OnClickListener {

    private static final String TASK_ID_EDIT = "task_id_edit";
    private static final int REQUEST_CODE_DATE = 10;
    private static final int REQUEST_CODE_TIME = 11;
    private static final String TAG_DATE_PICKER = "date_datePicker";
    private static final String TAG_TIME_PICKER = "time_timePicker";

    private EditText mEditTitle;
    private EditText mEditDescription;
    private TextView mRealDate;
    private TextView mRealTime;
    private Button mEditDateBtn;
    private Button mEditTimeBtn;
    private Task mTask;

    public RealEditTaskFragment() {
        // Required empty public constructor
    }

    public static RealEditTaskFragment newInstance(UUID taskId) {

        Bundle args = new Bundle();
        args.putSerializable(TASK_ID_EDIT, taskId);
        RealEditTaskFragment fragment = new RealEditTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID id = (UUID) getArguments().getSerializable(TASK_ID_EDIT);
        mTask = TaskRepo.getInstance().getTaskById(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_real_edit_task, container, false);
        mEditTitle = view.findViewById(R.id.editReal_title);
        mEditDescription = view.findViewById(R.id.editReal_desc);
        mRealDate = view.findViewById(R.id.realFragDate_date);
        mRealTime = view.findViewById(R.id.realFragTime_time);
        mEditDateBtn = view.findViewById(R.id.realFragDate_btn_edit);
        mEditTimeBtn = view.findViewById(R.id.realFragTime_btn_edit);


        mEditTitle.setText(mTask.getTitle());
        mEditDescription.setText(mTask.getDescription());
        mRealDate.setText(new SimpleDateFormat("yyyy-MMM-dd").format(mTask.getDate()));
        mRealTime.setText(new SimpleDateFormat("hh:mm a").format(mTask.getDate()));

        mEditDateBtn.setOnClickListener(this);
        mEditTimeBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.realFragDate_btn_edit){
            Toast.makeText(getActivity(), "date", Toast.LENGTH_SHORT).show();
            DateTimeFragment dateTimeFragment = DateTimeFragment.newInstance(mTask.getDate());
            dateTimeFragment.setTargetFragment(RealEditTaskFragment.this, REQUEST_CODE_DATE);
            dateTimeFragment.show(getFragmentManager(), TAG_DATE_PICKER);
        }
        if(view.getId() == R.id.realFragTime_btn_edit){
            Toast.makeText(getActivity(), "time", Toast.LENGTH_SHORT).show();
            DateTimeFragment dateTimeFragment = DateTimeFragment.newInstance(mTask.getDate());
            dateTimeFragment.setTargetFragment(RealEditTaskFragment.this, REQUEST_CODE_TIME);
            dateTimeFragment.show(getFragmentManager(), TAG_TIME_PICKER);
        }
    }
}
