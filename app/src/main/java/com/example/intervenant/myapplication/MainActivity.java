package com.example.intervenant.myapplication;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intervenant.myapplication.com.example.intervenant.core.Adapters.AppPagerAdapter;
import com.example.intervenant.myapplication.com.example.intervenant.core.Fragments.ProductCartFragment;
import com.example.intervenant.myapplication.com.example.intervenant.core.Fragments.ProductGridFragment;
import com.example.intervenant.myapplication.com.example.intervenant.core.Product;
import com.google.gson.Gson;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ProductGridFragment.OnFragmentInteractionListener, ProductCartFragment.OnFragmentInteractionListener {

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp.getInstance().restoreCart(this);

        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new AppPagerAdapter(getSupportFragmentManager()));

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public void onFragmentGridInteraction(Product product) {
        Intent detailIntent = new Intent(this, ProductDetailActivity.class);
        Gson gson = new Gson();
        detailIntent.putExtra("product", gson.toJson(product));
        startActivity(detailIntent);
    }

}
