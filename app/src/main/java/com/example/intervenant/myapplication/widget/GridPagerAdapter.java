package com.example.intervenant.myapplication.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.intervenant.myapplication.fragments.GridViewFragment;

/**
 * Created by kmoutier on 21/04/16.
 */
public class GridPagerAdapter  extends FragmentPagerAdapter {


    public GridPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
       // return super.getPageTitle(" Mon Titre " + position);
        return (" Mon Titre " + position);
    }

    @Override
    public Fragment getItem(int position) {
        //MListViewFragment.newInstance(position)
        return GridViewFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
