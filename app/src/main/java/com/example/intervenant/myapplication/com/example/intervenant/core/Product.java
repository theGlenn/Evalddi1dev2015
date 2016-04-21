package com.example.intervenant.myapplication.com.example.intervenant.core;

/**
 * Created by tboulongne on 21/04/2016.
 */
public class Product {

    String name;
    float price;
    String info;
    String image;

    public Product(String name, float price, String info, String img){
        this.name = name;
        this.price = price;
        this.info = info;
        this.image = img;
    }

    public String getImageUrl() { return image; }

    public static Product instantiate(String name, float price, String info, String img){
        return new Product(name, price, info, img);
    }
}
