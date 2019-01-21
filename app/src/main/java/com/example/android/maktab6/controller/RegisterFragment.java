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
import com.example.android.maktab6.model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    public static final String TAG_LOG_USER = "LoginFragment_TAG";
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

        mSubmitBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        _name = mName.getText().toString();
        Log.i(TAG_LOG_USER, "SignUp the name is: " + _name);
        _email = mEmail.getText().toString();
        Log.i(TAG_LOG_USER, "SignUp the email is: " + _email);
        _password = mPassword.getText().toString();
        Log.i(TAG_LOG_USER, "SignUp the password is: " + _password);
        _confirm = mConfirm.getText().toString();
        Log.i(TAG_LOG_USER, "SignUp the confirm is: " + _confirm);

        if (!_password.equals(_confirm)) {
            new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.confirmPass_alertDlg)
                    .setIcon(R.drawable.ic_error_alert)
                    .setPositiveButton(android.R.string.ok, null)
                    .create().show();
        } else if(_name.length() == 0 || _email.length() == 0){
            new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.field_isEmpty)
                    .setIcon(R.drawable.ic_error_alert)
                    .setPositiveButton(android.R.string.ok, null)
                    .create().show();
        } else  {
            mUser.setMName(_name);
            mUser.setMUserName(_email);
            mUser.setMPassword(_password);
            Log.i(TAG_LOG_USER, "userName: " + mUser.getMName() +
                    " userEmail: " + mUser.getMUserName() + " password: " + mUser.getMPassword());
            LoginUser.userLogin = TaskRepository.getInstance(getActivity()).addNewUser(mUser);
            mUser.set_idTableUser(LoginUser.userLogin);
            Log.i(TAG_LOG_USER, "LoginUser: " + LoginUser.userLogin);
            Intent intent = ViewPagerActivity.newIntent(getActivity());
            startActivity(intent);
            getActivity().finish();
        }
    }
}
