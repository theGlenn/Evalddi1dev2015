package com.example.intervenant.myapplication.com.example.intervenant.core;

import org.json.JSONObject;

/**
 * Created by pnguyen on 21/04/16.
 */
public class Product {
    public String name;
    public String image;
    public String price;

    public Product(String name, int resid){
        this.name = name;
        //this.image = resid;
    }

    public Product(JSONObject c) {
        this.name = c.optString("name");
        this.image = c.optString("image");
        this.price = c.optString("price");
    }

    public static Product instantiate(String name, int resid){
        return new Product(name, resid);
    }
}
