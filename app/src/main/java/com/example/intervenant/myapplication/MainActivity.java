package com.example.intervenant.myapplication;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.intervenant.myapplication.com.example.intervenant.core.DetailActivity;
import com.example.intervenant.myapplication.com.example.intervenant.core.Product;
import com.example.intervenant.myapplication.com.example.intervenant.core.fragments.MGridViewFragment;


public class MainActivity extends AppCompatActivity implements MGridViewFragment.OnFragmentListInteractionListener {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new GridPagerAdapter(getSupportFragmentManager()));

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onFragmentListInteraction(Product product) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra("name", product.name);
        detailIntent.putExtra("price", product.price);
        detailIntent.putExtra("info", product.info);
        detailIntent.putExtra("image", product.image);

        startActivity(detailIntent);
    }

    public class GridPagerAdapter extends FragmentPagerAdapter {

        public GridPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return MGridViewFragment.newInstance(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return position== 0 ? "Products" : "Cart";
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
