package com.example.intervenant.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.com.example.intervenant.core.Product;
import com.google.gson.Gson;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Intent intent = getIntent();

        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(intent != null){

            Gson gson = new Gson();
            Product product = gson.fromJson(intent.getStringExtra("product"), Product.class);

            TextView name = (TextView)findViewById(R.id.name);
            name.setText(product.name);

            TextView info = (TextView)findViewById(R.id.info);
            info.setText(product.info);

            TextView price = (TextView)findViewById(R.id.price);
            price.setText(String.valueOf(product.price) + " €");

            ImageView image = (ImageView)findViewById(R.id.image);
            if(product.image != null){
                Glide.with(this).load(product.image).into(image);
            }

        }

    }
}
