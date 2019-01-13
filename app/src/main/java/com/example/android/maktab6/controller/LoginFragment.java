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
import com.example.android.maktab6.model.TaskRepository;
import com.example.android.maktab6.model.User;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private Button mLoginBtn;
    private Button mRegisterBtn;
    private Button mGuestBtn;
    private EditText mUserName;
    private EditText mPassword;
    private String _userName;
    private String _passWord;
    private User mUser;
    private TaskRepository mTaskRepository;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskRepository = TaskRepository.getInstance(getActivity());
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
                _userName = charSequence.toString();
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
                _passWord = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.loginBtn_login:
                mUser = mTaskRepository.validateUser(_userName, _passWord);
                if(mUser != null){
                    UUID userId = mUser.getId();
                    Intent intent = ViewPagerActivity.newIntent(getActivity(), userId);
                    startActivity(intent);
                    getActivity().finish();
                } else {

                    new AlertDialog.Builder(getActivity())
                            .setTitle(R.string.login_alertDlg)
                            .setIcon(R.drawable.ic_error_alert)
                            .setPositiveButton(android.R.string.ok, null)
                            .create().show();
                }
                break;

            case R.id.loginBtn_guest:

                Intent intent = ViewPagerActivity.newIntent(getActivity(), null);
                startActivity(intent);
                getActivity().finish();
                break;

            default:

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.editTask_login_container, RegisterFragment.newInstance())
                        .addToBackStack(LoginActivity.LOGIN_FRAGMENT)
                        .commit();

        }
    }
}
