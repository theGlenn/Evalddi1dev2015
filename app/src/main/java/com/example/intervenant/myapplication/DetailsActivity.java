package com.example.intervenant.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.fragments.Providers.ProductProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(intent != null){

            // Retreive all Extras
            String name =  intent.getStringExtra("name");
            String img = intent.getStringExtra("image");
            String info =  intent.getStringExtra("info");
            Float price = intent.getFloatExtra("price", -1);

            product = new Product(name, info, img,price);

            // Set Details Texts
            TextView productName = (TextView)findViewById(R.id.detailsName);
            TextView productInfo = (TextView)findViewById(R.id.detailsInfo);
            TextView productPrice = (TextView)findViewById(R.id.detailsPrice);

            productName.setText(product.getName());
            productInfo.setText(product.getInfo());
            productPrice.setText(product.getPrice().toString() + " $");

            // Set Details Image
            ImageView imageView = (ImageView)findViewById(R.id.detailsImageView);

            if(product.getImage() != null) {
                Glide.with(this)
                        .load(product.getImage())
                        .into(imageView);
            }

            Button addToCartBtn =  (Button) findViewById(R.id.addCartBtn);
            if (addToCartBtn != null) {
                addToCartBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ProductProvider.Save(getApplicationContext(), product);
                    }
                });
            }
        }
    }
}
