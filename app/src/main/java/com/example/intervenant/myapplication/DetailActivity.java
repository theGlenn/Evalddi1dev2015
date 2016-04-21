package com.example.intervenant.myapplication;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {


    Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        //TODO add ActionBAr
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if(intent != null){

            String name =  intent.getStringExtra("name");
            String img = intent.getStringExtra("image");
            String info = intent.getStringExtra("info");
            float price = intent.getFloatExtra("price",0);

            food = new Food(name, img, info, price);

            TextView nameView = (TextView)findViewById(R.id.food_name);
            nameView.setText(food.name);

            TextView infoView = (TextView)findViewById(R.id.food_info);
            infoView.setText(food.info);

            TextView priceView = (TextView)findViewById(R.id.food_price);
            String priceValue =Float.toString(food.price);
            priceView.setText(priceValue);

            //priceView.setText("1.6");


            ImageView imageView = (ImageView)findViewById(R.id.food_img);
            if(food.img != null) {
                Glide.with(this)
                        .load(food.img)
                        .into(imageView);
            }

        }



        /*
        final Button button = (Button) findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            }
        });
        */

        final FloatingActionButton buttonAdd = (FloatingActionButton) this.findViewById(R.id.buttonAddCart);

        assert buttonAdd != null;
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println(food.name);



            }
        });

    }







    // Button onclick



}
