package com.example.intervenant.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.com.example.intervenant.core.Product;
import com.example.intervenant.myapplication.com.example.intervenant.core.ProductProvider;

/**
 * Created by jgigonnet on 21/04/16.
 */
public class DetailActivity extends AppCompatActivity {


    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (intent != null) {

            String name = intent.getStringExtra("name");
            String price = intent.getStringExtra("price");
            String info = intent.getStringExtra("info");
            String image = intent.getStringExtra("image");

            product = new Product(name, Float.parseFloat(price), info, image);
            TextView textView = (TextView) findViewById(R.id.textName);
            textView.setText(product.name);
            TextView textView2 = (TextView) findViewById(R.id.textPrice);
            textView2.setText(String.valueOf(price) + " $");
            TextView textView3 = (TextView) findViewById(R.id.textInfo);
            textView3.setText(product.info);
            ImageView imageView = (ImageView) findViewById(R.id.imageProduct);
            Glide.with(this).load(image).into(imageView);

        }

        Button button = (Button) findViewById(R.id.cartButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ProductProvider.putProductInCart(DetailActivity.this, product);
            }
        });

    }
}
