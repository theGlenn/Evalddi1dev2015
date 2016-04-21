package com.example.intervenant.myapplication.com.example.intervenant.core.widgets;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.intervenant.myapplication.com.example.intervenant.core.fragments.GridViewFragment;


public class MyAppPagerAdapter extends FragmentPagerAdapter {

    public MyAppPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return GridViewFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 1;
    }
}