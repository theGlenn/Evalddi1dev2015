package com.example.intervenant.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

/**
 * Created by adumont on 21/04/16.
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
            String image = intent.getStringExtra("image");
            String price =  intent.getStringExtra("price");
            String info = intent.getStringExtra("info");

            product = new Product(name, price, info, image);
            TextView textViewName = (TextView)findViewById(R.id.textViewName);
            textViewName.setText(product.name);
            TextView textViewPrice = (TextView)findViewById(R.id.textViewPrice);
            textViewPrice.setText(product.price);
            TextView textViewInfo = (TextView)findViewById(R.id.textViewInfo);
            textViewInfo.setText(product.info);
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

        SharedPreferences sharedpreferences = getSharedPreferences("data", 0);
        String productsCart = sharedpreferences.getString("products_cart", null);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        if(productsCart != null) {
            editor.putString("products_cart", productsCart);
        } else {
            editor.putString("products_cart", productsCart + ", " + json);
        }

        editor.commit();

        Toast.makeText(this, "Add to cart", Toast.LENGTH_SHORT).show();
    }
}
