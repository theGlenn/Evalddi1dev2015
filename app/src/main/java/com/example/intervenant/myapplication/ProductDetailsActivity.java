package com.example.intervenant.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

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

        Button button = (Button) findViewById(R.id.productDetails_button_add);

        if (image != null && name != null && price != null && info != null && button != null) {

            final Product product = new Product(productName, productPrice, productInfo, productImage);


            Glide.with(this).load(product.getImage()).into(image);
            name.setText(product.getName());

            String priceString = this.getString(R.string.dollar);
            priceString = priceString.concat(String.valueOf(product.getPrice()));

            price.setText(priceString);

            if (productInfo != null && !productInfo.isEmpty()) {
                info.setText(product.getInfo());
            } else {
                info.setText(this.getString(R.string.no_description));
            }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<Product> list = MyApp.getInstance().getCartList();

                    list.add(product);

                    MyApp.getInstance().setCartList(list);

                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                    finish();
                }
            });

        }
    }
}
