package com.example.intervenant.myapplication;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by jfong on 21/04/16.
 */
public class MyApp extends Application {

    public static MyApp instance;
    public ArrayList<Product> cartList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApp getInstance() {
        return instance;
    }

    public ArrayList<Product> getCartList() {
        return cartList;
    }

    public void setCartList(ArrayList<Product> list) {
        this.cartList = list;
    }

    public void removeInCartList(int i) {
        this.cartList.remove(i);
    }

    public void clearCartList() {
        this.cartList.clear();
    }
}
