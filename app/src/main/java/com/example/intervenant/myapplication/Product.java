package com.example.intervenant.myapplication;

import org.json.JSONArray;

/**
 * Created by jfong on 21/04/16.
 */
public class Product {

    public String name;
    public float price;
    public String info;
    public String image;

    public Product(String name, float price, String info, String image) {
        this.name = name;
        this.price = price;
        this.info = info;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getInfo() {
        return info;
    }

    public String getImage() {
        return image;
    }

}


