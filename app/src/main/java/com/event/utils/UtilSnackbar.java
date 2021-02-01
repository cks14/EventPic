package com.event.utils;


import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class UtilSnackbar {
    public static void showSnakbarTypeOne(View rootView, String mMessage) {
        Snackbar.make(rootView, mMessage, Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
    }

    public static void showSnakbarTypeTwo(View rootView, String mMessage) {

        Snackbar.make(rootView, mMessage, Snackbar.LENGTH_LONG)
                .make(rootView, mMessage, Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null)
                .show();

    }

    public static void showSnakbarTypeInternet(View rootView, final Activity activity) {

        Snackbar.make(rootView, "Please check your internet connection", Snackbar.LENGTH_SHORT)
//                .setAction("TryAgain", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                })

                .setActionTextColor(Color.YELLOW)
                .setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                    }

                    @Override
                    public void onShown(Snackbar snackbar) {
                        super.onShown(snackbar);
                    }
                })
                .show();

    }
    public static void showSnakbarTypeError(View rootView, final Activity activity) {

        Snackbar
                .make(rootView,"Email not found or wrong password", Snackbar.LENGTH_LONG)
//                .setAction("Something went wrong", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = activity.getIntent();
//                        activity.finish();
//                        activity.startActivity(intent);
//                    }
//                })
                .setActionTextColor(Color.YELLOW)
                .setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                    }

                    @Override
                    public void onShown(Snackbar snackbar) {
                        super.onShown(snackbar);
                    }
                })
                .show();


    }
    public static void showSnackbarWrongOtp(View rootView, final Activity activity) {

        Snackbar
                .make(rootView,"Wrong OTP", Snackbar.LENGTH_SHORT)

                .setActionTextColor(Color.YELLOW)
                .setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                    }

                    @Override
                    public void onShown(Snackbar snackbar) {
                        super.onShown(snackbar);
                    }
                })
                .show();


    }
    public static void showSnackbaremailunique(View rootView, final Activity activity) {
        Snackbar
                .make(rootView,"The Email Id field must contain a unique value.", Snackbar.LENGTH_SHORT)
                .setActionTextColor(Color.YELLOW)
                .setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                    }

                    @Override
                    public void onShown(Snackbar snackbar) {
                        super.onShown(snackbar);
                    }
                })
                .show();
    }
    public static void showPasswordMissmatch(View rootView, final Activity activity) {

        Snackbar
                .make(rootView,"Password and Confirm Password Not Matching", Snackbar.LENGTH_SHORT)
//                .setAction("Something went wrong", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = activity.getIntent();
//                        activity.finish();
//                        activity.startActivity(intent);
//                    }
//                })
                .setActionTextColor(Color.YELLOW)
                .setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                    }

                    @Override
                    public void onShown(Snackbar snackbar) {
                        super.onShown(snackbar);
                    }
                })
                .show();
    }
    public static void showSnackbarBackAgain(View rootView, final Activity activity) {

        Snackbar
                .make(rootView,"Please Click Back Again to Exit", Snackbar.LENGTH_SHORT)
//                .setAction("Something went wrong", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = activity.getIntent();
//                        activity.finish();
//                        activity.startActivity(intent);
//                    }
//                })
                .setActionTextColor(Color.YELLOW)
                .setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                    }

                    @Override
                    public void onShown(Snackbar snackbar) {
                        super.onShown(snackbar);
                    }
                })
                .show();
    }

    public static void showSnackbarEnterdob(View rootView, final Activity activity) {
        Snackbar
                .make(rootView,"Please Enter Date Of Birth", Snackbar.LENGTH_SHORT)
                .setActionTextColor(Color.YELLOW)
                .setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                    }

                    @Override
                    public void onShown(Snackbar snackbar) {
                        super.onShown(snackbar);
                    }
                })
                .show();
    }

    public static void showSnackbarUploadPhoto(View rootView, final Activity activity) {
        Snackbar
                .make(rootView,"Please Upload Photo", Snackbar.LENGTH_SHORT)
                .setActionTextColor(Color.YELLOW)
                .setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                    }

                    @Override
                    public void onShown(Snackbar snackbar) {
                        super.onShown(snackbar);
                    }
                })
                .show();
    }

    public static void showSnackbarUploadSign(View rootView, final Activity activity) {
        Snackbar
                .make(rootView,"Please Upload Signature", Snackbar.LENGTH_SHORT)
                .setActionTextColor(Color.YELLOW)
                .setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                    }

                    @Override
                    public void onShown(Snackbar snackbar) {
                        super.onShown(snackbar);
                    }
                })
                .show();
    }

    public static void showSnackbarCheckDeclaration(View rootView, final Activity activity) {
        Snackbar
                .make(rootView,"Please Accept Declaration", Snackbar.LENGTH_SHORT)
                .setActionTextColor(Color.YELLOW)
                .setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                    }

                    @Override
                    public void onShown(Snackbar snackbar) {
                        super.onShown(snackbar);
                    }
                })
                .show();
    }






}