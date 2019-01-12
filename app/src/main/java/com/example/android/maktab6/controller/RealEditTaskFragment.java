package com.example.android.maktab6.controller;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.maktab6.R;
import com.example.android.maktab6.model.Task;
import com.example.android.maktab6.model.TaskRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    private TextView mEditDateBtn;
    private TextView mEditTimeBtn;
    private Button mSubmit;
    private TextView mCancel;

    private Task mTask;
    private UUID mUUID;

    public RealEditTaskFragment() {
        // Required empty public constructor
    }

    public static RealEditTaskFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RealEditTaskFragment fragment = new RealEditTaskFragment();
        fragment.setArguments(args);
        return fragment;
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
        if(getArguments().getSerializable(TASK_ID_EDIT) != null) {
            mUUID = (UUID) getArguments().getSerializable(TASK_ID_EDIT);
            mTask = TaskRepository.getInstance().getTaskById(mUUID);
        } else {
            mTask = new Task();
        }
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
        mSubmit = view.findViewById(R.id.realFragDate_submitBtn);
        mCancel = view.findViewById(R.id.realFragTime_btn_cancel);

        if (mTask != null) {
            mEditTitle.setText(mTask.getTitle());
            mEditDescription.setText(mTask.getDescription());
            mRealDate.setText(new SimpleDateFormat("yyyy-MMM-dd").format(mTask.getDate()));
            mRealTime.setText(new SimpleDateFormat("hh:mm a").format(mTask.getDate()));
        }

        mEditTitle.addTextChangedListener(new TextWatcher() {
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
        mEditDescription.addTextChangedListener(new TextWatcher() {
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


        mEditDateBtn.setOnClickListener(this);
        mEditTimeBtn.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.realFragDate_btn_edit){
            DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mTask.getDate());
            datePickerFragment.setTargetFragment(RealEditTaskFragment.this, REQUEST_CODE_DATE);
            datePickerFragment.show(getFragmentManager(), TAG_DATE_PICKER);
        }
        if(view.getId() == R.id.realFragTime_btn_edit){
            TimePickerFragment timePickerFragment = TimePickerFragment.newInstance(mTask.getDate());
            timePickerFragment.setTargetFragment(RealEditTaskFragment.this, REQUEST_CODE_TIME);
            timePickerFragment.show(getFragmentManager(), TAG_TIME_PICKER);
        }
        if(view.getId() == R.id.realFragTime_btn_cancel){
            dismiss();
        }
        if(view.getId() == R.id.realFragDate_submitBtn){
            if(mTask.getId() == mUUID){
                //database update
            }else {
                TaskRepository.getInstance().addToList(mTask);

                dismiss();
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_CODE_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.getPickedDate());
            mTask.setDate(date);
            mRealDate.setText(new SimpleDateFormat("yyyy-MMM-dd").format(date));
        }
        if(requestCode == REQUEST_CODE_TIME){
            Date date = (Date) data.getSerializableExtra(TimePickerFragment.getPickedTime());
            mTask.setDate(date);
            mRealTime.setText(new SimpleDateFormat("hh:mm a").format(date));
        }
    }
}
