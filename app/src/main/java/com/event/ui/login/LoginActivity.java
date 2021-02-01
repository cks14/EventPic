package com.event.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.event.R;
import com.event.ui.home.HomeActivity;
import com.event.ui.register.RegisterActivity;
import com.event.utils.Configuration;
import com.event.utils.DialogLoader;
import com.event.utils.LocalConstant;
import com.event.utils.UtilSnackbar;
import com.event.webservice.HttpCallback;
import com.event.webservice.RestClient;
import com.event.webservice.model.Login;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;
    private RelativeLayout rrLogin;
    private TextView loginTv;
    private LinearLayout llLogin;
    private LinearLayout llPassword;
    private EditText editUsername;
    private EditText editPassword;
    private TextView forgotTv;
    private TextView dontAccount;
    private LinearLayout llLoginSub;
    private ImageView btnSub;
    private TextInputLayout text_input_email;
    private TextInputLayout text_input_pass;
    private TextView tv_pass_error;
    private TextView tv_user_error;
    private TextView tv_pass_error1;
    private TextView tv_user_error1;
    DialogLoader dialogLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        dialogLoader = new DialogLoader(LoginActivity.this);

        tv_pass_error = (TextView) findViewById(R.id.tv_pass_error);
        tv_user_error = (TextView) findViewById(R.id.tv_user_error);
        tv_pass_error1 = (TextView) findViewById(R.id.tv_pass_error1);
        tv_user_error1 = (TextView) findViewById(R.id.tv_user_error1);

        rrLogin = (RelativeLayout) findViewById(R.id.rr_login);
        loginTv = (TextView) findViewById(R.id.login_tv);
        llLogin = (LinearLayout) findViewById(R.id.ll_login);
        editUsername = (EditText) findViewById(R.id.edit_username);
        llPassword = (LinearLayout) findViewById(R.id.ll_password);
        editPassword = (EditText) findViewById(R.id.edit_password);
//        forgotTv = (TextView) findViewById(R.id.forgot_tv);
        dontAccount = (TextView) findViewById(R.id.dont_account);
        llLoginSub = (LinearLayout) findViewById(R.id.ll_login_sub);

        editUsername.addTextChangedListener(new GenericTextWatcher(editUsername));
        editPassword.addTextChangedListener(new GenericTextWatcher(editPassword));


        llLoginSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoginValid())
                {
                    if (Configuration.isInternetConnection(LoginActivity.this)) {
                        callloginApi();
                    } else {
                        UtilSnackbar.showSnakbarTypeInternet(findViewById(R.id.rr_log), LoginActivity.this);
                    }
                }


                }
        });



        dontAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

    }

    private void callloginApi() {

        dialogLoader.showProgressDialog();

        RestClient.getInst().Login(editUsername.getText().toString(),editPassword.getText().toString()).enqueue(new HttpCallback<Login>() {
            @Override
            public void onSuccess(Call<Login> call, Response<Login> response) {
                dialogLoader.hideProgressDialog();
                if (response.isSuccessful())
                {
                    if (response.body().getStatus().equalsIgnoreCase("200"));
                    {
                        LocalConstant.getInstance(LoginActivity.this).setIsLogin("1");
                        LocalConstant.getInstance(LoginActivity.this).setToken(response.body().getData().getAuth_token());
                        LocalConstant.getInstance(LoginActivity.this).setUserEmail(response.body().getData().getEmail());
                        LocalConstant.getInstance(LoginActivity.this).setUserName(response.body().getData().getName());
                        LocalConstant.getInstance(LoginActivity.this).setUserId(response.body().getData().getId());
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(i);
//                      System.out.println("nnnn"+response.getMessage());
                        finish();

                    }


                }
                else
//                    if (response.body().getStatus().equalsIgnoreCase("400"))
//
//                    {
                    Toast.makeText(LoginActivity.this, "Email Not Found or wrong password", Toast.LENGTH_SHORT).show();

//                }
            }

            @Override
            public void onError(Call<Login> call, Throwable t) {
                dialogLoader.hideProgressDialog();
                System.out.println("nnnn"+t.getMessage());
                UtilSnackbar.showSnakbarTypeError(findViewById(R.id.rr_log),LoginActivity.this);

            }
        });
    }

    private boolean isLoginValid() {

        if (TextUtils.isEmpty(editUsername.getText().toString())) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_user_error.setVisibility(View.VISIBLE);
                    llLogin.setBackgroundResource(R.drawable.round_red_bg_error);
                    tv_user_error.requestFocus();
                }
            });
            return false;
        } else if (!isValidEmail(editUsername.getText().toString())) {
            runOnUiThread(new Runnable() {
                @SuppressLint("WrongConstant")
                @Override
                public void run() {
                    tv_user_error1.setVisibility(View.VISIBLE);
                    llLogin.setBackgroundResource(R.drawable.round_red_bg_error);
                    tv_user_error1.requestFocus();
                }
            });
            return false;
        }

        if (TextUtils.isEmpty(editPassword.getText().toString())) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_pass_error.setVisibility(View.VISIBLE);
                    llPassword.setBackgroundResource(R.drawable.round_red_bg_error);
                    tv_pass_error.requestFocus();
                }
            });
            return false;
        }
//
//        else if (!isValidPassword(editPassword.getText().toString())) {
//            runOnUiThread(new Runnable() {
//                @SuppressLint("WrongConstant")
//                @Override
//                public void run() {
//                    tv_pass_error1.setVisibility(View.VISIBLE);
//                    llPassword.setBackgroundResource(R.drawable.round_red_bg_error);
//                    tv_pass_error1.requestFocus();
//                }
//            });
//            return false;
//        }

        return true;

    }

    private boolean regex_matcher(Pattern pattern, String string) {
        Matcher m = pattern.matcher(string);
        return m.find() && (m.group(0) != null);
    }

    public class GenericTextWatcher implements TextWatcher {
        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {

            tv_user_error.setVisibility(View.GONE);
            tv_pass_error.setVisibility(View.GONE);
            tv_pass_error1.setVisibility(View.GONE);
            tv_user_error1.setVisibility(View.GONE);


            llLogin.setBackgroundResource(R.drawable.round_btn_gray);
            llPassword.setBackgroundResource(R.drawable.round_btn_gray);


        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Aut

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            tv_user_error.setVisibility(View.VISIBLE);
            tv_pass_error.setVisibility(View.VISIBLE);
            tv_pass_error1.setVisibility(View.VISIBLE);
            tv_user_error1.setVisibility(View.VISIBLE);

            llLogin.setBackgroundResource(R.drawable.round_red_bg_error);
            llPassword.setBackgroundResource(R.drawable.round_red_bg_error);


        }
    }

    public boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

//    public boolean isValidPassword(final String password) {
//
//        Pattern pattern;
//        Matcher matcher;
//
//        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
//
//        pattern = Pattern.compile(PASSWORD_PATTERN);
//        matcher = pattern.matcher(password);
//
//        return matcher.matches();
//
//    }

}