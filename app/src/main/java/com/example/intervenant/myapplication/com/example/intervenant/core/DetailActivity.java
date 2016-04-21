package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.R;

public class DetailActivity extends AppCompatActivity {

    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();

        if(intent != null){

            String name =  intent.getStringExtra("name");
            int resImage = 0;
            String image = intent.getStringExtra("image");
            String info = intent.getStringExtra("info");
            String price = intent.getStringExtra("price");


            product = new Product(name, resImage, image, info, price);
            TextView productName = (TextView)findViewById(R.id.textView);
            productName.setText(product.name);
            TextView productPrice = (TextView)findViewById(R.id.textView_price);
            productPrice.setText(product.price);
            TextView productInfo = (TextView)findViewById(R.id.detail_descr);
            productInfo.setText(product.info);
            ImageView imageView = (ImageView)findViewById(R.id.imageView);
            Glide.with(this).load(product.image).into(imageView);

            final ProductProvider provider = new ProductProvider();



        }

    }
}
