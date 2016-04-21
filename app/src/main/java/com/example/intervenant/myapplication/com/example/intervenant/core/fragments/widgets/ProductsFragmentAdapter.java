package com.example.intervenant.myapplication.com.example.intervenant.core.fragments.widgets;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.intervenant.myapplication.com.example.intervenant.core.fragments.normal.ProductGridFragment;

/**
 * Created by intervenant on 21/04/16.
 */
public class ProductsFragmentAdapter extends FragmentPagerAdapter {
    public ProductsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ProductGridFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
