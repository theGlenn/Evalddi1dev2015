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
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.intervenant.myapplication.com.example.intervenant.core.Frangment;
import com.example.intervenant.myapplication.com.example.intervenant.core.Product;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, Frangment.OnFragmentInteractionListener {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new ListPagerAdapter(getSupportFragmentManager()));

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onFragmentInteraction(Product product) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra("name", product.name);
        detailIntent.putExtra("price", String.valueOf(product.price));
        detailIntent.putExtra("info", product.info);
        detailIntent.putExtra("image", product.image);

        startActivity(detailIntent);

    }

    public class ListPagerAdapter extends FragmentPagerAdapter {

        public ListPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return Frangment.newInstance(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return position== 0 ? "Products" : "Favorites";
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}
