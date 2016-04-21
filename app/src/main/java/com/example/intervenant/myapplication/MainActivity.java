package com.example.intervenant.myapplication;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements fragment_list.OnFragmentInteractionListener {

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

    //@Override
    //public void onFragmentListInteraction() {
        //Food food
        //Intent detailIntent = new Intent(this, DetailActivity.class);
        //detailIntent.putExtra("name", food.name);

        //startActivity(detailIntent);
    //}


    public class ListPagerAdapter extends FragmentPagerAdapter{
        public ListPagerAdapter(FragmentManager fm) {super(fm); }

        @Override
        public Fragment getItem(int position) {
            return fragment_list.newInstance(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return position== 0 ? "All food" : "Favorites";
        }

        @Override
        public int getCount() {
            return 2;
        }

    }

}
