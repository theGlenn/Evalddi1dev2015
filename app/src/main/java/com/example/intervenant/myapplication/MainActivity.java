package com.example.intervenant.myapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.intervenant.myapplication.Adapters.ProductsFragmentAdapter;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    ProductsFragmentAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.pager);

        adapterViewPager = new ProductsFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);

        assert tabLayout != null;
        tabLayout.setupWithViewPager(viewPager);
    }


}
