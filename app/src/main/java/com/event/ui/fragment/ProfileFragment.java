package com.event.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.event.R;
import com.event.ui.login.LoginActivity;
import com.event.utils.LocalConstant;


public class ProfileFragment extends Fragment {

    LinearLayout ll_logout;
    TextView tv_name;
    TextView tv_email;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_profile, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {

        ll_logout = v.findViewById(R.id.ll_logout);
        tv_name = v.findViewById(R.id.tv_name);
        tv_email = v.findViewById(R.id.tv_email);


        tv_name.setText(LocalConstant.getInstance(getActivity()).getUserName());
        tv_email.setText(LocalConstant.getInstance(getActivity()).getUserEmail());


        ll_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logout();


            }
        });


    }

    private void logout() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Logout");
        builder.setMessage("Do you want to logout?");
        builder.setPositiveButton(Html.fromHtml("<font color='#000000'>Yes</font>"),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        LocalConstant.getInstance(getActivity()).setIsLogin("");
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    }
                });

        builder.setNegativeButton(Html.fromHtml("<font color='#000000'>No</font>"),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //  dialogLoader.hideProgressDialog();
                        dialog.dismiss();
                    }
                });

        builder.show();
    }
}