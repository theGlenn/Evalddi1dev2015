package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.Product;
import com.example.intervenant.myapplication.R;

public class DetailActivity extends AppCompatActivity {

    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(intent != null){

            String name =  intent.getStringExtra("name");
            double price = intent.getDoubleExtra("price", 1.0);
            String info =  intent.getStringExtra("info");
            String image = intent.getStringExtra("image");
            product = new Product(name, price, info, image);

            TextView nameView = (TextView)findViewById(R.id.name);
            nameView.setText(product.name);

            TextView priceView = (TextView)findViewById(R.id.price);
            priceView.setText("$ " + Double.toString(product.price));

            TextView infoView = (TextView)findViewById(R.id.info);
            infoView.setText(product.info);

            ImageView imageView = (ImageView)findViewById(R.id.image);
            Glide.with(this).load(product.image).into(imageView);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return true;
    }
}
