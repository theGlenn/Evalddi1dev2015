package com.example.intervenant.myapplication.com.example.intervenant.core;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ykubacki on 21/04/16.
 */
public class Food {
    public String name;
    public String price;
    public String info;
    public String image;
    public Boolean isInCart;

    public Food (String name, String price, String info, String image){
        this.name = name;
        this.price = price;
        this.info = info;
        this.image = image;
        isInCart = false;
    }

    public Food (JSONObject obj) throws JSONException {
        this.name = obj.getString("name");
        this.price = obj.getString("price");
        this.info = obj.getString("info");
        this.image = obj.getString("image");
    }
}
