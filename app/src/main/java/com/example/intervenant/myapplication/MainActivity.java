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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.intervenant.myapplication.com.example.intervenant.core.DetailActivity;
import com.example.intervenant.myapplication.com.example.intervenant.core.Food;
import com.example.intervenant.myapplication.com.example.intervenant.core.fragments.GriedViewFragment;
import com.example.intervenant.myapplication.com.example.intervenant.core.fragments.ListViewFragment;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, GriedViewFragment.OnFragmentInteractionListener, ListViewFragment.OnFragmentInteractionListener {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MyAppPagerAdapter(getSupportFragmentManager()));

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentGridInteraction(Food food) {
        Log.d("HELLOOOOOOOO/","ICIIII");
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra("name", food.name);
        detailIntent.putExtra("image", food.image);
        detailIntent.putExtra("info", food.info);
        detailIntent.putExtra("price", food.price);

        startActivity(detailIntent);
    }

    public class MyAppPagerAdapter extends FragmentPagerAdapter {

        public MyAppPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return position== 0 ? "Grid" : "List";
        }

        @Override
        public Fragment getItem(int position) {
            return position== 0 ? GriedViewFragment.newInstance(position) : ListViewFragment.newInstance(position);

        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
