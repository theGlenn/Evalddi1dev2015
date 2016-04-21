package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.R;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by avanderpotte on 21/04/16.
 */
public class DetailActivity extends AppCompatActivity {

    private static final int CONTENT_VIEW_ID = 0x10101010;

    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if(intent != null){

            String name =  intent.getStringExtra("name");
            String price =  intent.getStringExtra("price");
            String info = intent.getStringExtra("info");
            String image = intent.getStringExtra("image");

            product = new Product(name, price, info, image);
            Log.v("product", product.toString());
            TextView textName = (TextView)findViewById(R.id.textName);
            textName.setText(product.name);
            TextView textPrice = (TextView)findViewById(R.id.textPrice);
            textPrice.setText(product.price);
            TextView textInfo = (TextView)findViewById(R.id.textInfo);
            textInfo.setText(product.info);
            ImageView imageView = (ImageView)findViewById(R.id.imageView);
            Glide.with(getBaseContext()).load(product.image).into(imageView);
        }

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addToCart(product);
            }
        });
    }

    private void addToCart(Product product) {

        Gson gson = new Gson();
        String json = gson.toJson(product);

        SharedPreferences mPrefs = getSharedPreferences("data", 0);
        String lastProducts = mPrefs.getString("cartProducts", "");
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        if(lastProducts != "") {
            prefsEditor.putString("cartProducts", lastProducts + "," + json);
        } else {
            prefsEditor.putString("cartProducts", json);
        }
        prefsEditor.commit();

        Toast.makeText(this, "Product added to your cart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d("HFGHDKJFXDGHFJHGXDH", "HFKGHJGKLKUJFJHFJH");
                this.finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
