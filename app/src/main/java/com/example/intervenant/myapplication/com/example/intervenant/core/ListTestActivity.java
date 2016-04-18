package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.fragments.MListViewFragment;


public class ListTestActivity extends AppCompatActivity implements MListViewFragment.OnFragmentListInteractionListener {

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_test);

        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new ListPagerAdapter(getSupportFragmentManager()));

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
    }


    @Override
    public void onFragmentListInteraction(Fruit fruit) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra("name", fruit.name);
        detailIntent.putExtra("image", fruit.image);

        startActivity(detailIntent);
    }


    public class ListPagerAdapter extends FragmentPagerAdapter{

        public ListPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return MListViewFragment.newInstance(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return position== 0 ? "All fruits" : "Favorites";
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}
