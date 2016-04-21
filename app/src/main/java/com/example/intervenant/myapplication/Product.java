package com.example.intervenant.myapplication;

/**
 * Created by baest on 21/04/2016.
 */
public class Product {
    public String name;
    public String image;
    public String info;
    public String price;

    public Product(String name, String image, String price, String info){
        this.name = name;
        this.image = image;
        this.info = info;
    }

    public static Product instantiate(String name, String image, String price, String info){
        return new Product(name, image, price, info);
    }
}
