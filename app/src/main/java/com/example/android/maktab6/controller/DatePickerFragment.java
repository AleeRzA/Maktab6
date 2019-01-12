package com.example.android.maktab6.controller;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.example.android.maktab6.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment {


    public static final String TASK_DATE = "Task_date";


    private static final String PICKED_DATE = "com.example.android.maktab6.controller.picked_dat";
    private Date mDate;
    private DatePicker mDatePicker;
    public DatePickerFragment() {
        // Required empty public constructor
    }

    public static DatePickerFragment newInstance(Date date) {

        Bundle args = new Bundle();
        args.putSerializable(TASK_DATE, date);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDate = (Date) getArguments().getSerializable(TASK_DATE);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_date_picker, null);
        mDatePicker = view.findViewById(R.id.datePicker_fragment);
        Calendar calendar = Calendar.getInstance();
        int Year = calendar.get(Calendar.YEAR);
        int Month = calendar.get(Calendar.MONTH);
        int Day = calendar.get(Calendar.DAY_OF_MONTH);

        mDatePicker.init(Year, Month, Day, null);

        return new AlertDialog.Builder(getActivity())
                                .setView(view)
                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        int year = mDatePicker.getYear();
                                        int month = mDatePicker.getMonth();
                                        int day = mDatePicker.getDayOfMonth();

                                        Date date = new GregorianCalendar(year, month, day).getTime();
                                        dateResult(date);
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

    private void dateResult(Date date) {
        Intent intent = new Intent();
        intent.putExtra(PICKED_DATE, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(),Activity.RESULT_OK, intent);
    }
    public static String getPickedDate() {
        return PICKED_DATE;
    }

}
