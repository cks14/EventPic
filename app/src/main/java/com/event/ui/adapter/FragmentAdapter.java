package com.event.ui.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.event.ui.fragment.PicUploadingFragment;
import com.event.ui.fragment.UploadedPictureFragment;
import com.event.ui.fragment.ProfileFragment;

import java.util.ArrayList;


public class FragmentAdapter extends FragmentPagerAdapter {

    Context context;
    ArrayList<Fragment> fragments;

    public FragmentAdapter(FragmentManager fm, Context context, ArrayList<Fragment> fragments) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PicUploadingFragment picUploadingFragment = new PicUploadingFragment();
                return picUploadingFragment;
            case 1:
                UploadedPictureFragment uploadedPictureFragment = new UploadedPictureFragment();
                return uploadedPictureFragment;
            case 2:
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;
            default:
                return null;
        }
    }

}