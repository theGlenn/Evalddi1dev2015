package com.example.intervenant.myapplication.com.example.intervenant.core.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.intervenant.myapplication.com.example.intervenant.core.Fragments.ProductGridFragment;

/**
 * Created by etienne-dldc on 21/04/2016.
 */
public class AppPagerAdapter  extends FragmentPagerAdapter {

    public AppPagerAdapter (FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ProductGridFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position== 0 ? "Products" : "Fav <3";
    }

    @Override
    public int getCount() {
        return 2;
    }
}