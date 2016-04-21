package com.example.intervenant.myapplication.com.example.intervenant.core;

import org.json.JSONObject;

/**
 * Created by pnguyen on 21/04/16.
 */
public class Product {
    public String name;
    public String image;
    public String price;
    public String description;

    public Product(String name, String description, String imgId){
        this.name = name;
        this.description = description;
        this.image = imgId;
    }

    public Product(JSONObject c) {
        this.name = c.optString("name");
        this.image = c.optString("image");
        this.price = c.optString("price");
        this.description = c.optString("info");
    }

    public static Product instantiate(String name, String description, String imgId){
        return new Product(name, description, imgId);
    }
}
