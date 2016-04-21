package com.example.intervenant.myapplication;

/**
 * Created by Quentin on 21/04/16.
 */
public class Food {

    public String name;
    public String img;
    public String info;
    public float price;


    public Food(String name, String imgUrl, String info, float price){
        this.name = name;
        this.img = imgUrl;
        this.info = info;
        this.price = price;
    }
}
