package com.example.android.maktab6.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.maktab6.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskCreationFragment extends Fragment {

    private EditText mTitle;
    private EditText mDescription;
    private Button mCreateBtn;
    public TaskCreationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_creation, container, false);
        mTitle = view.findViewById(R.id.creation_title);
        mDescription = view.findViewById(R.id.creation_description);
        mCreateBtn = view.findViewById(R.id.creation_btn_create);

        return view;
    }

}
