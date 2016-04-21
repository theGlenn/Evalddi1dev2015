package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.fragments.ProductProvider;

/**
 * Created by pnguyen on 21/04/16.
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
            String description =  intent.getStringExtra("info");
            String imgId = intent.getStringExtra("image");

            product = new Product(name, description, imgId);
            TextView textView = (TextView)findViewById(R.id.product_detail_text);
            textView.setText(product.name);

            TextView textViewDesc = (TextView)findViewById(R.id.product_detail_description);
            textViewDesc.setText(product.description);

            ImageView imageView = (ImageView)findViewById(R.id.product_detail_img);
            Glide.with(this).load(product.image).into(imageView);
        }

        Button button = (Button) findViewById(R.id.button_buy);

        if (button != null) button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ProductProvider.provideInCart(DetailActivity.this, product);

            }

        });


    }



}
