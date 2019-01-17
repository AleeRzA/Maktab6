package com.example.android.maktab6.controller;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.maktab6.R;
import com.example.android.maktab6.model.LoginUser;
import com.example.android.maktab6.model.TaskRepository;
import com.example.android.maktab6.model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private EditText mName;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mConfirm;
    private Button mSubmitBtn;

    private User mUser;
    private String _name;
    private String _email;
    private String _password;
    private String _confirm;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {

        Bundle args = new Bundle();

        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUser = new User();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        mName = view.findViewById(R.id.txtLogin_name_edit);
        mEmail = view.findViewById(R.id.txtLogin_email_edit);
        mPassword = view.findViewById(R.id.txtLogin_password_edit);
        mConfirm = view.findViewById(R.id.txtLogin_passwordAgn_edit);
        mSubmitBtn = view.findViewById(R.id.txtLogin_submitBtn);

        mName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                _name = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                _email = charSequence.toString();
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
                _password = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                _confirm = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!_password.equals(_confirm)) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle(R.string.confirmPass_alertDlg)
                            .setIcon(R.drawable.ic_error_alert)
                            .setPositiveButton(android.R.string.ok, null)
                            .create().show();
                } else {
                    mUser.setName(_name);
                    mUser.setUserName(_email);
                    mUser.setPassword(_password);
                    long userid = TaskRepository.getInstance(getActivity()).addNewUser(mUser);
                    LoginUser.userLogin = (int) userid;
                    Intent intent = ViewPagerActivity.newIntent(getActivity(), mUser.getId());
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
        return view;
    }

//    public void addTextChangeListener(EditText editText, final int id) {
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                switch (id) {
//                        case
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//    }
}
