package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.intervenant.myapplication.R;

public class DetailActivity extends AppCompatActivity {

    private static final int CONTENT_VIEW_ID = 0x10101010;

    Fruit fruit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        if(intent != null){

            String name =  intent.getStringExtra("name");
            int resId = intent.getIntExtra("image", -1);

            fruit = new Fruit(name, resId);
            TextView textView = (TextView)findViewById(R.id.textView);
            textView.setText(fruit.name);
            ImageView imageView = (ImageView)findViewById(R.id.imageView);
            imageView.setImageResource(fruit.image);
        }


    }
}
