package com.example.intervenant.myapplication.com.example.intervenant.core.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.intervenant.myapplication.com.example.intervenant.core.Fragments.ProductCartFragment;
import com.example.intervenant.myapplication.com.example.intervenant.core.Fragments.ProductGridFragment;

/**
 * Created by etienne-dldc on 21/04/2016.
 */
public class AppPagerAdapter  extends FragmentPagerAdapter {

    public final int FRAGMENT_TYPE_GRID = 0;
    public final int FRAGMENT_TYPE_CART = 1;

    public AppPagerAdapter (FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int type) {
        if (type == FRAGMENT_TYPE_GRID) {
            return ProductGridFragment.newInstance();
        }
        return ProductCartFragment.newInstance();
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