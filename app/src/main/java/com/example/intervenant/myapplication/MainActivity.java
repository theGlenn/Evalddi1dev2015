package com.example.intervenant.myapplication;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.intervenant.myapplication.Fragments.MCartViewFragment;
import com.example.intervenant.myapplication.Fragments.MGridViewFragment;


public class MainActivity extends AppCompatActivity implements MGridViewFragment.OnFragmentListInteractionListener {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new GridPagerAdapter(getSupportFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onFragmentListInteraction(ProductObject product) {
        Intent detailIntent = new Intent(this, DetailViewActivity.class);
        detailIntent.putExtra("name", product.name);
        detailIntent.putExtra("info", product.info);
        detailIntent.putExtra("image", product.image);
        detailIntent.putExtra("price", product.price);

        startActivity(detailIntent);
    }

    public class GridPagerAdapter extends FragmentPagerAdapter {

        public GridPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return position == 0 ? MGridViewFragment.newInstance(position) : MCartViewFragment.newInstance(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return position== 0 ? "Products List" : "My Cart";
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
