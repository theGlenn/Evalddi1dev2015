package com.example.intervenant.myapplication;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by jfong on 21/04/16.
 */
public class MyApp extends Application {

    public static ArrayList<Product> cartList = new ArrayList<>();
    public static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Application getInstance() {
        return instance;
    }

    public static ArrayList<Product> getCartList() {
        return cartList;
    }

    public static void setCartList(ArrayList<Product> cartList) {
        MyApp.cartList = cartList;
    }
}
