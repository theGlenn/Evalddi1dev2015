package com.example.intervenant.myapplication.com.example.intervenant.core;

/**
 * Created by benjamingammaire on 21/04/16.
 */
public class Product {

    public String name;
    public String image;
    public int resImage;
    public String info;
    public String price;

    public Product(String name, int resImage, String image, String info, String price){
        this.name = name;
        this.resImage = resImage;
        this.image = image;
        this.info = info;
        this.price = price;
    }

}
