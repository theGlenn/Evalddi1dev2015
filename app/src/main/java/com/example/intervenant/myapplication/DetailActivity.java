package com.example.intervenant.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intervenant.myapplication.com.example.intervenant.core.ProductProvider;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    Product product;
    ArrayList<Product> cartList;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        sharedPref = getSharedPreferences("products", Context.MODE_PRIVATE);
        editor     = sharedPref.edit();

        try {
            cartList = ProductProvider.provideFromBasket(sharedPref);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent intent = getIntent();

        if(intent != null){

            String name =  intent.getStringExtra("name");
            String image = intent.getStringExtra("image");
            String price = intent.getStringExtra("price");
            String info = intent.getStringExtra("info");

            product = new Product(name, image, price, info);
            product.inCart = isInCart();

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

            final Button button = (Button)findViewById(R.id.button);

            if(product.inCart) {
                button.setText("Supprimer du panier");
                button.setBackgroundColor(Color.RED);
            }

            assert button != null;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(product.inCart) {
                        removeFromCart();
                        button.setText("Ajouter au panier");
                        button.setBackgroundColor(Color.BLUE);
                    }
                    else {
                        addToCart();
                        button.setText("Supprimer du panier");
                        button.setBackgroundColor(Color.RED);
                    }
                }
            });
        }
    }

    public void addToCart() {
        cartList.add(product);

        Gson gson = new Gson();
        String json = gson.toJson(cartList);
        editor.putString("cart", json);
        editor.commit();
    }

    public void removeFromCart() {
        for(int i = 0; i < cartList.size(); i++) {
            if(cartList.get(i).name.equals(product.name)) {
                cartList.remove(cartList.get(i));
            }
        }

        Gson gson = new Gson();
        String json = gson.toJson(cartList);
        editor.putString("cart", json);
        editor.commit();
    }

    private Boolean isInCart() {
        Boolean isIn = false;

        for(int i = 0; i < cartList.size(); i++) {
            isIn = isIn ? isIn : product.name.equals(cartList.get(i).name);
        }

        return isIn;
    }
}
