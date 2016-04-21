package com.example.intervenant.myapplication.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * Created by intervenant on 21/04/16.
 */
public class Product {

    public static final String TAG = Product.class.getName();
    public String image;
    public String name;
    public String price;
    public String info;

    public static Product fromJson(String jsonString){
        return new Gson().fromJson(jsonString, Product.class);
    }
    public String toJson() {
        return new Gson().toJson(this, Product.class);
    }
}
