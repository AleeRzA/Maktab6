package com.example.android.maktab6.controller;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class DateTimeFragment extends DialogFragment {

    private TabLayout mTabDateTime;
    private TabLayout mTabTime;
    private DatePicker mDatePicker;
    private TimePicker mTimePicker;
    private Date mDate;

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
    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_date_time, container, false);
//        mTabDateTime = view.findViewById(R.id.tabLayout_dateTime);
//        mTabDateTime.addTab(mTabDateTime.newTab()
//                .setText(new SimpleDateFormat("yyyy-MMM-dd").format(mDate)));
//        mTabDateTime.addTab(mTabDateTime.newTab()
//                .setText(new SimpleDateFormat("hh:mm a").format(mDate)));
////        mTimePicker = view.findViewById(R.id.time_timePicker);
////        mDatePicker = view.findViewById(R.id.date_datePicker);
//
//
//        mTabDateTime.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                if(mTabDateTime.getSelectedTabPosition() == 0)
//                    mTimePicker.setVisibility(View.GONE);
//
//                if(mTabDateTime.getSelectedTabPosition() == 1)
//                    mDatePicker.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        int hour = calendar.get(Calendar.HOUR);
//        int minute = calendar.get(Calendar.MINUTE);
//
//        mDatePicker.init(year, month, day, null);
//
//
//        return view;
//    }
}
