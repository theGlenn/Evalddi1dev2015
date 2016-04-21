package com.example.intervenant.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.com.example.intervenant.core.Product;
import com.example.intervenant.myapplication.com.example.intervenant.core.ProductProvider;

public class ProductDetail extends AppCompatActivity {

    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Intent intent = getIntent();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(intent != null){

            String name  =  intent.getStringExtra("name");
            String image = intent.getStringExtra("image");
            String price = intent.getStringExtra("price");
            String info  = intent.getStringExtra("info");

            product = new Product(name, Float.parseFloat(price), info, image);
            TextView nameView = (TextView)findViewById(R.id.name);
            nameView.setText(product.name);
            TextView priceView = (TextView)findViewById(R.id.price);
            priceView.setText(String.valueOf(product.price) + '€');
            TextView infoView = (TextView)findViewById(R.id.info);
            infoView.setText(product.info);
            ImageView imageView = (ImageView)findViewById(R.id.image);
            Glide.with(this).load(product.getImageUrl()).into(imageView);

            Button button = (Button) findViewById(R.id.cartButton);
            if (button != null) {
                //button.setChecked(product.favorite);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ProductProvider.putProductInCart(ProductDetail.this,product);
                    }
                });
            }

        }
    }
}
