package com.example.android.maktab6.controller;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.maktab6.R;
import com.example.android.maktab6.model.LoginUser;
import com.example.android.maktab6.model.Task;
import com.example.android.maktab6.model.TaskRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class RealEditTaskFragment extends DialogFragment implements View.OnClickListener {

    public interface Callback{
        void onTriggered();
    }
    public static final String TAG = "RealEditTaskFragment_TAG";

    private static final String TASK_ID_EDIT = "com.example.android.maktab6.controller.task_id_edit";
    private static final int REQUEST_CODE_DATE = 10;
    private static final int REQUEST_CODE_TIME = 11;
    private static final String TAG_DATE_PICKER = "date_datePicker";
    private static final String TAG_TIME_PICKER = "time_timePicker";
    private static final String TASK_UUID = "task_uuid";

    private Callback mCallback;

    private EditText mEditTitle;
    private EditText mEditDescription;
    private TextView mRealDate;
    private TextView mRealTime;
    private TextView mEditDateBtn;
    private TextView mEditTimeBtn;
    private Button mSubmit;
    private TextView mCancel;
    private CheckBox mDoneCheckBox;

    private Task mTask;
    private UUID mTaskUUID;
    private TaskRepository mTaskRepository;

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

        mTaskRepository = TaskRepository.getInstance(getActivity());


        if (getArguments().getSerializable(TASK_ID_EDIT) != null) {
            mTaskUUID = (UUID) getArguments().getSerializable(TASK_ID_EDIT);
            mTask = mTaskRepository.getTaskById(mTaskUUID, LoginUser.userLogin);

            Log.i(TAG, "onCreate: if " + mTask.getMTaskUUId());
        } else {
            mTask = new Task(LoginUser.userLogin);

            Log.i(TAG, "onCreate: else " + mTask.getMTaskUUId());
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
        mDoneCheckBox = view.findViewById(R.id.checkBox_doneTask);

        if (mTask != null) {
            mEditTitle.setText(mTask.getMTitle());
            mEditDescription.setText(mTask.getMDescription());
            mRealDate.setText(new SimpleDateFormat("yyyy-MMM-dd").format(mTask.getMDate()));
            mRealTime.setText(new SimpleDateFormat("hh:mm a").format(mTask.getMDate()));
            if(mTask.getMDone())
                mDoneCheckBox.setEnabled(false);
        }


        mEditDateBtn.setOnClickListener(this);
        mEditTimeBtn.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
        mCancel.setOnClickListener(this);

        mDoneCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTask.setMDone(true);
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Callback)
            mCallback = (Callback) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onClick(View view) {
        mTask.setMTitle(mEditTitle.getText().toString());
        mTask.setMDescription(mEditDescription.getText().toString());


        if (view.getId() == R.id.realFragDate_btn_edit) {
            DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mTask.getMDate());
            datePickerFragment.setTargetFragment(RealEditTaskFragment.this, REQUEST_CODE_DATE);
            datePickerFragment.show(getFragmentManager(), TAG_DATE_PICKER);
        }
        if (view.getId() == R.id.realFragTime_btn_edit) {
            TimePickerFragment timePickerFragment = TimePickerFragment.newInstance(mTask.getMDate());
            timePickerFragment.setTargetFragment(RealEditTaskFragment.this, REQUEST_CODE_TIME);
            timePickerFragment.show(getFragmentManager(), TAG_TIME_PICKER);
        }
        if (view.getId() == R.id.realFragTime_btn_cancel) {
            dismiss();
        }
        if (view.getId() == R.id.realFragDate_submitBtn) {

            if (UUID.fromString(mTask.getMTaskUUId()).equals(mTaskUUID)) {
                mTaskRepository.update(mTask);
            } else {
                TaskRepository.getInstance(getActivity()).addTaskToList(mTask);
            }

            mCallback.onTriggered();
//            List<Fragment> fragments = getActivity().getSupportFragmentManager().getFragments();
//            for (Fragment fragment : fragments) {
//                if (fragment instanceof TaskListFragment) {
//                    TaskListFragment taskListFragment = (TaskListFragment) fragment;
//                    taskListFragment.updateUI();
//                }
//            }
            dismiss();
        }
    }
    public static String getTaskUuid() {
        return TASK_UUID;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.getPickedDate());
            mTask.setMDate(date);
            mRealDate.setText(new SimpleDateFormat("yyyy-MMM-dd").format(date));
        }
        if (requestCode == REQUEST_CODE_TIME) {
            Date date = (Date) data.getSerializableExtra(TimePickerFragment.getPickedTime());
            mTask.setMDate(date);
            mRealTime.setText(new SimpleDateFormat("hh:mm a").format(date));
        }
    }
}
