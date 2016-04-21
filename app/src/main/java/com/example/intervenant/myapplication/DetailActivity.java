package com.example.intervenant.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        if(intent != null){

            String name =  intent.getStringExtra("name");
            String image = intent.getStringExtra("image");
            String price = intent.getStringExtra("price");
            String info = intent.getStringExtra("info");

            product = new Product(name, image, price, info);

            ImageView imageView = (ImageView)findViewById(R.id.imageView);

            Picasso.with(this)
                    .load(product.image)
                    .into(imageView);

            TextView productName = (TextView)findViewById(R.id.productName);
            productName.setText(product.name);

            TextView productPriceBefore = (TextView)findViewById(R.id.productPriceBefore);
            TextView productPrice = (TextView)findViewById(R.id.productPrice);

            productPrice.setText(product.price);

            TextView productInfo = (TextView)findViewById(R.id.productInfo);
            productInfo.setText(product.info);
        }
    }
}
