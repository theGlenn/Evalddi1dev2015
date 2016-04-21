package com.example.intervenant.myapplication;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.intervenant.myapplication.com.example.intervenant.core.fragments.GriedViewFragment;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, GriedViewFragment.OnFragmentInteractionListener {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MyAppPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class MyAppPagerAdapter extends FragmentPagerAdapter {

        public MyAppPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return GriedViewFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 1;
        }
    }

}
