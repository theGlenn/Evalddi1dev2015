package com.example.intervenant.myapplication;

/**
 * Created by Quentin on 21/04/16.
 */
public class Food {

    public String name;
    public String img;


    //for details
    public Food(String name, int resid){
        this.name = name;
    }

    public Food(String name, String imgUrl){
        this.name = name;
        this.img = imgUrl;
    }
}
