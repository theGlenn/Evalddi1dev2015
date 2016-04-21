package com.example.intervenant.myapplication;

/**
 * Created by adumont on 21/04/16.
 */
public class Product {
    public String name;
    public String price;
    public String info;
    public String image;

    public Product(String name, String price, String info, String image){
        this.name = name;
        this.price = "$ " + price;
        this.info = info;
        this.image = image;
    }

    public static Product instantiate(String name, String price, String info, String image){
        return new Product(name, price, info, image);
    }
}
