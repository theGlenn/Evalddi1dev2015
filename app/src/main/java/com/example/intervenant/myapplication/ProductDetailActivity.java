package com.example.intervenant.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.com.example.intervenant.core.Product;
import com.google.gson.Gson;

public class ProductDetailActivity extends AppCompatActivity {

    public Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Intent intent = getIntent();

        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(intent != null){

            Gson gson = new Gson();
            product = gson.fromJson(intent.getStringExtra("product"), Product.class);

            TextView name = (TextView)findViewById(R.id.name);
            if (name != null) {
                name.setText(product.name);
            }

            TextView info = (TextView)findViewById(R.id.info);
            if (info != null) {
                info.setText(product.info);
            }

            TextView price = (TextView)findViewById(R.id.price);
            if (price != null) {
                String textContent = String.valueOf(product.price) + " €";
                price.setText(textContent);
            }

            ImageView image = (ImageView)findViewById(R.id.image);
            if(product.image != null){
                Glide.with(this).load(product.image).into(image);
            }

            final Button button = (Button) findViewById(R.id.button);
            if (button != null) {
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        MyApp.getInstance().addProductToCart(getBaseContext(), product);
                    }
                });
            }

        }

    }
}
