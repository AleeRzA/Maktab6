package com.example.android.maktab6.controller;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import com.example.android.maktab6.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment {


    public static final String TIME_PICKER = "time_picker";



    private static final String PICKED_TIME = "com.example.android.maktab6.controller.picked_time";
    private Date mDate;
    private TimePicker mTimePicker;
    public TimePickerFragment() {
        // Required empty public constructor
    }

    public static TimePickerFragment newInstance(Date date) {

        Bundle args = new Bundle();
        args.putSerializable(TIME_PICKER, date);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDate = (Date) getArguments().getSerializable(TIME_PICKER);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_time_picker, null);
        mTimePicker = view.findViewById(R.id.timePicker_fragment);
        Calendar calendar = Calendar.getInstance();
        final int Year = calendar.get(Calendar.YEAR);
        final int Month = calendar.get(Calendar.MONTH);
        final int Day = calendar.get(Calendar.DAY_OF_MONTH);
        int Hour = calendar.get(Calendar.HOUR);
        int Minute = calendar.get(Calendar.MINUTE);

        mTimePicker.setHour(Hour);
        mTimePicker.setMinute(Minute);

        return new AlertDialog.Builder(getActivity())
                                .setView(view)
                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        int hour = mTimePicker.getHour();
                                        int minute = mTimePicker.getMinute();

                                        Date date = new GregorianCalendar(Year, Month, Day, hour, minute).getTime();
                                        timeResult(date);
                                    }
                                })
                                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dismiss();
                                    }
                                })
                                .create();
    }
    private void timeResult(Date date) {
        Intent intent = new Intent();
        intent.putExtra(PICKED_TIME, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(),Activity.RESULT_OK, intent);
    }
    public static String getPickedTime() {
        return PICKED_TIME;
    }
}
