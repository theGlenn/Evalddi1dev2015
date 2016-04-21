package com.example.intervenant.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.com.example.intervenant.core.ProductProvider;

public class DetailViewActivity extends AppCompatActivity {

    ProductObject product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        Intent intent = getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(intent != null){

            String name =  intent.getStringExtra("name");
            String info = intent.getStringExtra("info");
            String image = intent.getStringExtra("image");
            String price = intent.getStringExtra("price");

            product = new ProductObject(name, info, image, price);

            TextView productName = (TextView)findViewById(R.id.textName);
            productName.setText(product.name);

            TextView productInfo = (TextView)findViewById(R.id.textInfo);
            productInfo.setText(product.info);

            ImageView imageView = (ImageView)findViewById(R.id.imageView);

            TextView productPrice = (TextView)findViewById(R.id.textPrice);
            String thePrice = product.price + " €";
            productPrice.setText(thePrice);

            Glide.with(this).load(product.image).into(imageView);

            final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);

            checkBox.setChecked(ProductProvider.checkCartExist(getApplicationContext(), product));

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkBox.isChecked()){
                        ProductProvider.addToCart(getApplicationContext(), product);
                        Toast.makeText(getApplicationContext(), "Added",
                                Toast.LENGTH_LONG).show();
                    }else{
                        ProductProvider.removeFromCart(getApplicationContext(), product);
                        Toast.makeText(getApplicationContext(), "Removed",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
