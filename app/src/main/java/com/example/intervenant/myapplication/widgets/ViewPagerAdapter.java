package com.example.intervenant.myapplication.widgets;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.intervenant.myapplication.fragments.CartListFragment;
import com.example.intervenant.myapplication.fragments.ProductGridFragment;

/**
 * Created by fmotte on 21/04/16.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ProductGridFragment.newInstance(position);
            case 1:
                return CartListFragment.newInstance(position);
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position == 0 ? "Products" : "Cart";
    }

    @Override
    public int getCount() {
        return 2;
    }
}