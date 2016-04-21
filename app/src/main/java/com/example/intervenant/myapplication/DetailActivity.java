package com.example.intervenant.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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


    }
}
