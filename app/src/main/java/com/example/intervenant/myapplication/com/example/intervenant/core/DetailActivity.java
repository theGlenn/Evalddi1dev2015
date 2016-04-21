package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.R;

public class DetailActivity extends AppCompatActivity {

    Food food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(intent != null){

            String name =  intent.getStringExtra("name");
            String price = intent.getStringExtra("price");
            String info = intent.getStringExtra("info");
            String image = intent.getStringExtra("image");


            food = new Food(name,price, info, image);
            TextView NameTextView = (TextView)findViewById(R.id.name);
            NameTextView.setText(food.name);
            TextView InfoTextView = (TextView)findViewById(R.id.info);
            InfoTextView.setText(food.info);
            TextView PriceTextView = (TextView)findViewById(R.id.price);
            PriceTextView.setText(food.price);
            ImageView imageView = (ImageView)findViewById(R.id.imageView);
            Log.d("IMAGE/","HELLO");
            food.isInCart = FoodProvider.isInCart(this,food);
            if(food.image != null){
                Glide.with(this).load(food.image).into(imageView);
            }
            /*else {
                imageView.setImageResource(fruit.resImage);
            }*/
        }

        Switch cartSwitch = (Switch) findViewById(R.id.switchCart);
        if(cartSwitch != null) {
            cartSwitch.setChecked(food.isInCart);
            cartSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        Log.i("check","Wasn't checked");
                        food.isInCart = false;
                        FoodProvider.putFoodInCart(DetailActivity.this,food);
                    } else {
                        Log.i("check","Was checked");
                        food.isInCart = true;
                        FoodProvider.removeFoodFromCart(DetailActivity.this,food);
                    }
                }
            });
        }
    }
}
