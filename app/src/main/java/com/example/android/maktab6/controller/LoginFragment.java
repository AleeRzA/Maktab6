package com.example.android.maktab6.controller;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.maktab6.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private Button mLoginBtn;
    private Button mRegisterBtn;
    private Button mGuestBtn;
    private EditText mUserName;
    private EditText mPassword;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        
        Bundle args = new Bundle();
        
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mLoginBtn = view.findViewById(R.id.loginBtn_login);
        mRegisterBtn = view.findViewById(R.id.loginBtn_register);
        mGuestBtn = view.findViewById(R.id.loginBtn_guest);
        mUserName = view.findViewById(R.id.edtTxtUserEmail);
        mPassword = view.findViewById(R.id.edtTxtUserPassword);
        //-----------
        mLoginBtn.setOnClickListener(this);
        mRegisterBtn.setOnClickListener(this);
        mGuestBtn.setOnClickListener(this);

        mUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(), view.getTooltipText(), Toast.LENGTH_SHORT).show();
        switch (view.getId()){
            case R.id.loginBtn_login:
//                if()

                new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.login_alertDlg)
                        .setIcon(R.drawable.ic_error_alert)
                        .setPositiveButton(android.R.string.ok, null)
                        .create().show();
                break;

            case R.id.loginBtn_register:
                RegisterFragment registerFragment = RegisterFragment.newInstance();

                getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.editTask_login_container, RegisterFragment.newInstance())
                                .commit();
                break;

                default:
                    Intent intent = ViewPagerActivity.newIntent(getActivity());
                    startActivity(intent);
                    getActivity().finish();

        }
    }
}
