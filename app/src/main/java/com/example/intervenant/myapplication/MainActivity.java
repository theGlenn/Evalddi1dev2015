package com.example.intervenant.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.intervenant.myapplication.fragments.GridViewFragment;
import com.example.intervenant.myapplication.fragments.GridViewFragment.OnFragmentInteractionListener;
import com.example.intervenant.myapplication.widget.GridPagerAdapter;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, GridViewFragment.OnFragmentInteractionListener{

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new GridPagerAdapter(getSupportFragmentManager()));

        final TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void onFragmentInteraction(Product product) {
        Intent detailIntent = new Intent(this, DetailsActivity.class);
        detailIntent.putExtra("name", product.getName());
        detailIntent.putExtra("image", product.getImage());
        detailIntent.putExtra("price", product.getPrice());
        detailIntent.putExtra("info", product.getInfo());
        startActivity(detailIntent);
    }
}
