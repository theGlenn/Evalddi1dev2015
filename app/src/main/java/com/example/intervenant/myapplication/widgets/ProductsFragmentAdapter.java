package com.example.intervenant.myapplication.widgets;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.intervenant.myapplication.fragments.normal.ProductCartFragment;
import com.example.intervenant.myapplication.fragments.normal.ProductGridFragment;

/**
 * Created by intervenant on 21/04/16.
 */
public class ProductsFragmentAdapter extends FragmentPagerAdapter {

    public String titles[] = new String[]{"Products", "My cart"};
    public ProductsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return position == 0 ? ProductGridFragment.newInstance() : ProductCartFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
