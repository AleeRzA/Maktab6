package com.example.android.maktab6.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.maktab6.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmptyTaskFragment extends Fragment {


    public EmptyTaskFragment() {
        // Required empty public constructor
    }

    public static EmptyTaskFragment newInstance() {
        
        Bundle args = new Bundle();
        
        EmptyTaskFragment fragment = new EmptyTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.sample_fragment_task_empty, container, false);
    }

}
