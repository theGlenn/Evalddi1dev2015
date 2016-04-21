package com.example.intervenant.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.intervenant.myapplication.com.example.intervenant.core.DetailActivity;
import com.example.intervenant.myapplication.fragments.ProductGridFragment;
import com.example.intervenant.myapplication.widgets.ViewPagerAdapter;


public class MainActivity extends AppCompatActivity implements ProductGridFragment.OnFragmentListInteractionListener {

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
    public void onFragmentListInteraction(Product product) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra("name", product.name);
        detailIntent.putExtra("price", product.price);
        detailIntent.putExtra("info", product.info);
        detailIntent.putExtra("image", product.image);

        startActivity(detailIntent);
    }
}
