package com.example.intervenant.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.model.Product;
import com.example.intervenant.myapplication.model.ProductProvider;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    Product mProduct;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String jsonProduct = intent.getStringExtra(Product.TAG);
        mProduct = Product.fromJson(jsonProduct);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(mProduct.name);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        ImageView imageView = (ImageView) findViewById(R.id.toolbar_img);
        Glide.with(this).load(mProduct.image).into(imageView);

        TextView textView = (TextView) findViewById(R.id.product_info);
        textView.setText(mProduct.info);
    }

    @Override
    public void onClick(View view) {

        ProductProvider.addToCart(mProduct);
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

}
