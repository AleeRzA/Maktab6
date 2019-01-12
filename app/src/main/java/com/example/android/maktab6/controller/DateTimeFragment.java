package com.example.android.maktab6.controller;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TimePicker;

import com.example.android.maktab6.R;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class DateTimeFragment extends DialogFragment {

    private TabLayout mTabDateTime;
    private TabLayout mTabTime;
    private DatePickerFragment mDatePickerFragment;
    private TimePicker mTimePicker;
    private Date mDate;
    private Button mOkButton;
    private Button mCancelButton;
    private FrameLayout mFrameLayout;

    private static final String TASK_DATE = "task_date";

    public DateTimeFragment() {
        // Required empty public constructor
    }

    public static DateTimeFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(TASK_DATE, date);
        DateTimeFragment fragment = new DateTimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDate = (Date) getArguments().getSerializable(TASK_DATE);
        mDatePickerFragment = DatePickerFragment.newInstance(mDate);
        FragmentManager fragmentManager = getFragmentManager();
        if(fragmentManager.findFragmentById(R.id.dateTimePicker_container) == null){
            fragmentManager.beginTransaction()
                    .add(R.id.dateTimePicker_container, mDatePickerFragment)
                    .commit();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_date_time, null, false);
//        mTabDateTime.addTab(mTabTime.newTab().setText(mDate.toString()), 0, true);
//        mTabDateTime = view.findViewById(R.id.tabLayout_dateTime);
        mFrameLayout = view.findViewById(R.id.dateTimePicker_container);
        mOkButton = view.findViewById(R.id.btn_dateTime_ok);
        mCancelButton = view.findViewById(R.id.btn_dateTime_cancel);

        mFrameLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {

            }
        });
        return view;
    }
}
