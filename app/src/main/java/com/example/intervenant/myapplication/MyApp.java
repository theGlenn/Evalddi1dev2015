package com.example.intervenant.myapplication;

import android.app.Application;

import com.example.intervenant.myapplication.model.Product;

import java.util.ArrayList;

/**
 * Created by intervenant on 21/04/16.
 */
public class MyApp extends Application {


    public static MyApp instance;
    public ArrayList<Product> products;

    @Override
    public void onCreate() {
        super.onCreate();
        products = new ArrayList<>();
        instance = this;
    }

    public static MyApp getInstance() {
        return instance;
    }
}
