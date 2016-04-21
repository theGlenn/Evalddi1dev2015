package com.example.intervenant.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        String productName = getIntent().getStringExtra("name");
        float productPrice = getIntent().getFloatExtra("price", 0f);
        String productInfo = getIntent().getStringExtra("info");
        String productImage = getIntent().getStringExtra("image");

        ImageView image = (ImageView) findViewById(R.id.productDetails_image);
        TextView name = (TextView) findViewById(R.id.productDetails_name);
        TextView price = (TextView) findViewById(R.id.productDetails_price);
        TextView info = (TextView) findViewById(R.id.productDetails_info);

        if (image != null && name != null && price != null && info != null) {

            Product product = new Product(productName, productPrice, productInfo, productImage);


            Glide.with(this).load(product.getImage()).into(image);
            name.setText(product.getName());

            String priceString = this.getString(R.string.dollar);
            priceString = priceString.concat(String.valueOf(product.getPrice()));

            price.setText(priceString);

            if(productInfo != null && !productInfo.isEmpty()) {
                info.setText(product.getInfo());
            } else {
                info.setText(this.getString(R.string.no_description));
            }

        }
    }
}
