package com.event.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.graphics.PorterDuff;
import android.os.Bundle;

import com.event.R;
import com.event.ui.adapter.FragmentAdapter;
import com.event.ui.fragment.PicUploadingFragment;
import com.event.ui.fragment.UploadedPictureFragment;
import com.event.ui.fragment.ProfileFragment;
import com.event.utils.LocalConstant;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        LocalConstant.getInstance(HomeActivity.this).setIsLogin("1");


        viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        fragments = new ArrayList<>();

        fragments.add(new PicUploadingFragment());
        fragments.add(new UploadedPictureFragment());
        fragments.add(new ProfileFragment());

        FragmentAdapter pagerAdapter = new FragmentAdapter(getSupportFragmentManager(), getApplicationContext(), fragments);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_add_a_photo_24);
        tabLayout.getTabAt(0).setText("Pic uploading");
        tabLayout.getTabAt(1).setIcon(R.drawable.camera);
        tabLayout.getTabAt(1).setText("Uploaded picture");
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_account_box_24);
        tabLayout.getTabAt(2).setText("Profile fragment");


        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int tabIconColor = ContextCompat.getColor(HomeActivity.this, R.color.white);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                        if (tabLayout.getTabAt(0).isSelected()) {

                        } else {

                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        int tabIconColor = ContextCompat.getColor(HomeActivity.this, R.color.grey);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );

    }

    @Override
    protected void onResume() {
        super.onResume();
        tabLayout.getTabAt(0).select();
    }

}