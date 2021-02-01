package com.event.ui.register;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.event.R;
import com.event.ui.login.LoginActivity;
import com.event.utils.Configuration;
import com.event.utils.DialogLoader;
import com.event.utils.LocalConstant;
import com.event.utils.UtilSnackbar;
import com.event.webservice.HttpCallback;
import com.event.webservice.RestClient;
import com.event.webservice.model.Register;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private RelativeLayout rrSign;
    private TextView registerTv;
    private LinearLayout llUser;
    private EditText editUserName;
    private TextView tvUserError;
    private LinearLayout llSignEmail;
    private EditText editEmail;
    private TextView tvEmailError;
    private TextView tvEmailError1;
    private LinearLayout llPassword;
    private EditText editPassword;
    private TextView tvPassError;
    private TextView tvPassError1;
    private LinearLayout llSubmit;
    private TextView tvUserName;
    private TextView tvUserName1;
    private TextView tvUserNot;
    private TextView tvUserNot1;
    private LinearLayout llUserName;
    private LinearLayout llUserName1;
    Dialog dialog1;
    private TextView tvt_user;
    private LinearLayout LlApply;
    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
    DialogLoader dialogLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        dialogLoader = new DialogLoader(RegisterActivity.this);
        // rrSign = (RelativeLayout) findViewById(R.id.rr_sign);
        registerTv = (TextView) findViewById(R.id.register_tv);
        llUser = (LinearLayout) findViewById(R.id.ll_user);
        editUserName = (EditText) findViewById(R.id.edit_user_name);
//        tvt_user = (TextView) findViewById(R.id.tvt_user);
        tvUserError = (TextView) findViewById(R.id.tv_user_error);
        llSignEmail = (LinearLayout) findViewById(R.id.ll_sign_email);
        editEmail = (EditText) findViewById(R.id.edit_email);
        tvEmailError = (TextView) findViewById(R.id.tv_email_error);
        tvEmailError1 = (TextView) findViewById(R.id.tv_email_error1);
        llPassword = (LinearLayout) findViewById(R.id.ll_password);
        editPassword = (EditText) findViewById(R.id.edit_password);
        tvPassError = (TextView) findViewById(R.id.tv_pass_error);
        tvPassError1 = (TextView) findViewById(R.id.tv_pass_error1);
        llSubmit = (LinearLayout) findViewById(R.id.ll_submit);

        editUserName.addTextChangedListener(new GenericTextWatcher(editUserName));
        editEmail.addTextChangedListener(new GenericTextWatcher(editEmail));
        editPassword.addTextChangedListener(new GenericTextWatcher(editPassword));


        llSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSignValid()) {

                    if (Configuration.isInternetConnection(RegisterActivity.this)) {
                        callSignapi();
                    } else {
                        UtilSnackbar.showSnakbarTypeInternet(findViewById(R.id.rr_sign), RegisterActivity.this);
                    }
                }
            }
        });

    }

    private void callSignapi() {

        dialogLoader.showProgressDialog();

        RestClient.getInst().Register(editUserName.getText().toString(),editEmail.getText().toString(),editPassword.getText().toString(), LocalConstant.getInstance(RegisterActivity.this).getToken()).enqueue(new HttpCallback<Register>() {
            @Override
            public void onSuccess(Call<Register> call, Response<Register> response) {
                dialogLoader.hideProgressDialog();

                if (response.isSuccessful())
                {
                    if (response.body().getStatus().equalsIgnoreCase("200"));
                    {

                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finishAffinity();
                        Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                    }

                }

                else
                {
                    Toast.makeText(RegisterActivity.this, "The Email Id field must contain a unique value./", Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onError(Call<Register> call, Throwable t) {
                dialogLoader.hideProgressDialog();
                System.out.println("hshhs"+t.getMessage());
                UtilSnackbar.showSnackbaremailunique(findViewById(R.id.rr_sign),RegisterActivity.this);

            }
        });

    }

    public class GenericTextWatcher implements TextWatcher {
        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {

            tvUserError.setVisibility(View.GONE);
            tvEmailError.setVisibility(View.GONE);
            tvEmailError1.setVisibility(View.GONE);
            tvPassError.setVisibility(View.GONE);
            tvPassError1.setVisibility(View.GONE);


            llUser.setBackgroundResource(R.drawable.round_btn_gray);
            llPassword.setBackgroundResource(R.drawable.round_btn_gray);
            llSignEmail.setBackgroundResource(R.drawable.round_btn_gray);

        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Aut

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            tvUserError.setVisibility(View.VISIBLE);
            tvEmailError.setVisibility(View.VISIBLE);
            tvEmailError1.setVisibility(View.VISIBLE);
            tvPassError.setVisibility(View.VISIBLE);
            tvPassError1.setVisibility(View.VISIBLE);


            llUser.setBackgroundResource(R.drawable.round_red_bg_error);
            llPassword.setBackgroundResource(R.drawable.round_red_bg_error);
            llSignEmail.setBackgroundResource(R.drawable.round_red_bg_error);


        }


    }
    private boolean isSignValid() {

        if (TextUtils.isEmpty(editUserName.getText().toString())) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvUserError.setVisibility(View.VISIBLE);
                    llUser.setBackgroundResource(R.drawable.round_red_bg_error);
                    tvUserError.requestFocus();
                }
            });
            return false;
        }

        if (TextUtils.isEmpty(editEmail.getText().toString())) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvEmailError.setVisibility(View.VISIBLE);
                    llSignEmail.setBackgroundResource(R.drawable.round_red_bg_error);
                    tvEmailError.requestFocus();
                }
            });
            return false;
        } else if (!isValidEmail(editEmail.getText().toString())) {
            runOnUiThread(new Runnable() {
                @SuppressLint("WrongConstant")
                @Override
                public void run() {
                    tvEmailError1.setVisibility(View.VISIBLE);
                    llSignEmail.setBackgroundResource(R.drawable.round_red_bg_error);
                    tvEmailError1.requestFocus();
                }
            });
            return false;
        }

        if (TextUtils.isEmpty(editPassword.getText().toString())) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvPassError.setVisibility(View.VISIBLE);
                    llPassword.setBackgroundResource(R.drawable.round_red_bg_error);
                    tvPassError.requestFocus();
                }
            });
            return false;
        }

//        else if (!isValidPassword(editPassword.getText().toString())) {
//            runOnUiThread(new Runnable() {
//                @SuppressLint("WrongConstant")
//                @Override
//                public void run() {
//                    tvPassError1.setVisibility(View.VISIBLE);
//                    llPassword.setBackgroundResource(R.drawable.round_red_bg_error);
//                    tvPassError1.requestFocus();
//                }
//            });
//            return false;
//        }


        return true;
    }


    public boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public boolean isuserunique(String user) {
        String USER_PATTERN = "^[a-z0-9_-]{3,15}$";
        Pattern pattern = Pattern.compile(USER_PATTERN);
        Matcher matcher = pattern.matcher(user);
        return matcher.matches();
    }

}