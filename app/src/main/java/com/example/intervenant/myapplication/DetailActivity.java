package com.example.intervenant.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.com.example.intervenant.core.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    Product product;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final String KEY = "PRODUCT";
        Intent intent = getIntent();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        SHAREDPREFERENCES
        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();

        if(intent != null){
            Log.v("Product", intent.toString());
            String name =  intent.getStringExtra("name");
            String info = intent.getStringExtra("info");
            float price = intent.getFloatExtra("price",0);
            String image = intent.getStringExtra("image");

            product = new Product(name,info, price, image);
            TextView nameView = (TextView)findViewById(R.id.nameView);
            nameView.setText(product.name);
            TextView infoView = (TextView)findViewById(R.id.infoView);
            infoView.setText(product.info);
            TextView priceView = (TextView)findViewById(R.id.priceView);
            priceView.setText(String.valueOf(product.price));
            ImageView imageView = (ImageView)findViewById(R.id.imgView);
            Glide.with(getBaseContext()).load(product.image).into(imageView);


            final Button button = (Button) findViewById(R.id.add);
            assert button != null;
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(getBaseContext(), "add", Toast.LENGTH_SHORT).show();


                    Gson gson = new Gson(); // Or use new GsonBuilder().create();

                    if(sharedpreferences.contains(KEY)){
                        ArrayList<Product> list = new ArrayList<>();
                        String strJson = sharedpreferences.getString(KEY,"[]");//second parameter is necessary ie.,Value to return if this preference does not exist.
                        if(strJson != null) {
                            try {

                                JSONArray jsonData = new JSONArray(strJson);
                                Log.v("PRODUCT",jsonData.toString());
                                Type listType = new TypeToken<List<Product>>() {}.getType();
                                list = gson.fromJson(jsonData.toString(), listType);
                                list.add(product);
                                String newData =  gson.toJson(list);
                                editor.putString(KEY, newData);
                                editor.apply();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    } else {
                        String data = null;
                        data = gson.toJson(product);
                        Log.v("PRODUCT",data);
                        editor.putString(KEY, data);
                        editor.commit();
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

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
