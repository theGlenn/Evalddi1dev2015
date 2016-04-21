package com.example.intervenant.myapplication;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.intervenant.myapplication.com.example.intervenant.core.fragments.widgets.ProductsFragmentAdapter;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new ProductsFragmentAdapter(getSupportFragmentManager()));
    }

    @Override
    public void onClick(View view) {

    }


}
