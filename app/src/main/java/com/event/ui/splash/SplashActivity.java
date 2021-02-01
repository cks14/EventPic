package com.event.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.event.R;
import com.event.ui.welcome.WelcomeActivity;

import static android.os.Binder.getCallingUid;

public class SplashActivity extends AppCompatActivity {

    TextView tv_splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tv_splash = findViewById(R.id.tv_splash);

        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(tv_splash,
                PropertyValuesHolder.ofFloat("scaleX", 0.0f, 1.0f),
                PropertyValuesHolder.ofFloat("scaleY", 0.0f, 1.0f),
                PropertyValuesHolder.ofFloat("alpha", 0.0f, 1.0f));
        scaleDown.setDuration(2000);
        scaleDown.start();

        boolean hasAndroidPermissions = hasPermissions(getApplicationContext(), new String[]{
                Manifest.permission.INTERNET,

        });

        if (hasAndroidPermissions) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                }
            }, 2500);

        } else {

            ActivityCompat.requestPermissions(SplashActivity.this,
                    new String[]{
                            Manifest.permission.INTERNET,


                    },
                    1);
        }


    }

    public static boolean hasPermissions(Context context, String... permissions) {

        boolean hasAllPermissions = true;
        for (String permission : permissions) {
            //return false instead of assigning, but with this you can log all permission values
            if (!hasPermission(context, permission)) {
                hasAllPermissions = false;
            }
        }
        return hasAllPermissions;
    }

    public static boolean hasPermission(Context context, String permission) {

        PackageManager pm = context.getPackageManager();
        boolean permissionaaa = (PackageManager.PERMISSION_GRANTED == pm.checkPermission(permission, pm.getNameForUid(getCallingUid())));
        return permissionaaa;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                    }, 2500);
                } else {
                    Toast.makeText(SplashActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}