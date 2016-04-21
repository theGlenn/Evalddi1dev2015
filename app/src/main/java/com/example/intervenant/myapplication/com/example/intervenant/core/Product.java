package com.example.intervenant.myapplication.com.example.intervenant.core;

/**
 * Created by lxing on 21/04/2016.
 */
public class Product {

    public String name;
    public String info;
    public float price;
    public String image;

    public Product(String name, String info, float price, String image){
        this.name = name;
        this.info = info;
        this.price = price;
        this.image = image;
    }

    public static Product instantiate(String name,String info, float price, String image){
        return new Product(name, info, price, image);
    }
}
