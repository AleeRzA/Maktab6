package com.example.android.maktab6.controller;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.maktab6.R;
import com.example.android.maktab6.model.LoginUser;
import com.example.android.maktab6.model.TaskRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "LoginFragment_TAG";

    private Button mLoginBtn;
    private Button mRegisterBtn;
    private Button mGuestBtn;
    private EditText mUserName;
    private EditText mPassword;
    private String _userName;
    private String _passWord;
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

        return view;
    }

    @Override
    public void onClick(View view) {
        _userName = mUserName.getText().toString();
        _passWord = mPassword.getText().toString();

        switch (view.getId()) {
            case R.id.loginBtn_login:
                if (_userName == null || _passWord == null) {
                    return;
                }
                if (mTaskRepository.validateUser(_userName, _passWord) != null) {
                    Log.i(TAG, "onClick: LoginFragment not Register " + LoginUser.userLogin);
                    Intent intent = ViewPagerActivity.newIntent(getActivity());
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    new AlertDialog.Builder(getActivity())
                            .setTitle(R.string.login_alertDlg)
                            .setIcon(R.drawable.ic_error_alert)
                            .setPositiveButton(android.R.string.ok, null)
                            .create().show();
                    mUserName.setText("");
                    mPassword.setText("");
                }
                break;

            case R.id.loginBtn_guest:
                Intent intent = ViewPagerActivity.newIntent(getActivity());
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
