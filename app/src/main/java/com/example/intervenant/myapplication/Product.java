package com.example.intervenant.myapplication;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by fmotte on 21/04/16.
 */
public class Product {
    public String name;
    public double price;
    public String info;
    public String image;

    public Product(String name, double price, String info, String image) {
        this.name = name;
        this.price = price;
        this.info = info;

        this.image = image;
    }

    public Product(JSONObject obj) throws JSONException {
        this.name = obj.getString("name");
        this.price = obj.getDouble("price");
        this.info = obj.getString("info");
        this.image = obj.getString("image");
    }
}
