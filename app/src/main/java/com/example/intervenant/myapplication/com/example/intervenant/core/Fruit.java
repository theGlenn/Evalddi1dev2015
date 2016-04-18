package com.example.intervenant.myapplication.com.example.intervenant.core;

/**
 * Created by intervenant on 13/04/16.
 */
public class Fruit {
    String name;
    int image;

    Fruit(String name, int resid){
        this.name = name;
        this.image = resid;
    }

    public static Fruit instantiate(String name, int resid){
        return new Fruit(name, resid);
    }
}
