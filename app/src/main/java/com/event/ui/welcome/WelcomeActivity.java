package com.event.ui.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.event.R;
import com.event.ui.home.HomeActivity;
import com.event.ui.login.LoginActivity;
import com.event.utils.LocalConstant;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (LocalConstant.getInstance(WelcomeActivity.this).getIsLogin().equalsIgnoreCase("1")) {
            startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
            LocalConstant.getInstance(WelcomeActivity.this).setKey("1");
            finishAffinity();
        }
        else {
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            finishAffinity();
        }
    }
}