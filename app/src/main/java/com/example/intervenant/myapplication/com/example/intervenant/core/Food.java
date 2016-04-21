package com.example.intervenant.myapplication.com.example.intervenant.core;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by totomac on 21/04/2016.
 */
public class Food {

    public String name;
    public double price;
    public String info;
    public String image;

    public Food (String name, double price, String info, String image){
        this.name = name;
        this.price = price;
        this.info = info;
        this.image = image;
    }

    public Food (JSONObject obj) throws JSONException {
        this.name = obj.getString("name");
        this.price = obj.getDouble("price");
        this.info = obj.getString("info");
        this.image = obj.getString("image");
    }

}
