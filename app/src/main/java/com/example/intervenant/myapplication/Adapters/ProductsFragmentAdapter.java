package com.example.intervenant.myapplication.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.intervenant.myapplication.Fragments.ProductsFragment;

/**
 * Created by jfong on 21/04/16.
 */
public class ProductsFragmentAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 2;


    public ProductsFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {

        return ProductsFragment.newInstance(position);
    }

    // Returns the page name for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Products";
        } else {
            return "Cart";
        }
    }

}
