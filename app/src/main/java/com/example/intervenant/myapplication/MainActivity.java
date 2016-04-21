package com.example.intervenant.myapplication;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.intervenant.myapplication.fragments.ProductFragment;
import com.example.intervenant.myapplication.model.Product;
import com.example.intervenant.myapplication.widgets.ProductsAdapter;
import com.example.intervenant.myapplication.widgets.ProductsFragmentAdapter;


public class MainActivity extends AppCompatActivity implements ProductFragment.OnProductItemClickListener {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new ProductsFragmentAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onProductItemSelected(Product product, ProductsAdapter.ViewHolder holder) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Product.TAG, product.toJson());


        Pair<View, String> p1 = Pair.create((View) holder.imageView, "product_img");
        Pair<View, String> p2 = Pair.create((View) holder.nameView, "product_text");
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p2);
        startActivity(intent, options.toBundle());

    }
}
