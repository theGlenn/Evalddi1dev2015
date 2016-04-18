package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.fragments.MListViewFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


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
