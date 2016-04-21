package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intervenant.myapplication.R;

/**
 * Created by pnguyen on 21/04/16.
 */
public class DetailActivity extends AppCompatActivity {

    private static final int CONTENT_VIEW_ID = 0x10101010;

    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_item);

        Intent intent = getIntent();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(intent != null){

            String name =  intent.getStringExtra("name");
            int resId = intent.getIntExtra("image", -1);

            product = new Product(name, resId);
            TextView textView = (TextView)findViewById(R.id.product_text);
            textView.setText(product.name);
            ImageView imageView = (ImageView)findViewById(R.id.product_img);
        }


    }
}
