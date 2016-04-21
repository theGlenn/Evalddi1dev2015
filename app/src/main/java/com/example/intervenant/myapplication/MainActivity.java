package com.example.intervenant.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.intervenant.myapplication.fragments.ProductGridFragment.OnFragmentInteractionListener;
import com.example.intervenant.myapplication.widgets.ViewPagerAdapter;


public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
