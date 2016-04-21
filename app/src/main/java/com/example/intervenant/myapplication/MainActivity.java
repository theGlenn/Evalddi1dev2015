package com.example.intervenant.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.intervenant.myapplication.com.example.intervenant.core.DetailActivity;
import com.example.intervenant.myapplication.com.example.intervenant.core.Product;
import com.example.intervenant.myapplication.com.example.intervenant.core.fragments.MListViewFragment;


public class MainActivity extends AppCompatActivity implements MListViewFragment.OnFragmentGridInteractionListener {

    ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new ListPagerAdapter(getSupportFragmentManager()));

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);

    }


    @Override
    public void onFragmentInteraction(Product obj) {

        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra("name", obj.name);
        detailIntent.putExtra("image", obj.image);
        detailIntent.putExtra("info", obj.info);
        detailIntent.putExtra("price", obj.price);

        startActivity(detailIntent);

    }

    public class ListPagerAdapter extends FragmentPagerAdapter {

        public ListPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return MListViewFragment.newInstance(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return position== 0 ? "All products" : "Cart";
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}
