package com.example.intervenant.myapplication;

import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.intervenant.myapplication.com.example.intervenant.core.fragments.GridViewFragment;
import com.example.intervenant.myapplication.com.example.intervenant.core.fragments.GridViewFragment.OnFragmentInteractionListener;
import com.example.intervenant.myapplication.com.example.intervenant.core.widgets.MyAppPagerAdapter;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnFragmentInteractionListener {

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
}
