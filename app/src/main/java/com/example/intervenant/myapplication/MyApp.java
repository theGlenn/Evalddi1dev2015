package com.example.intervenant.myapplication;

import android.app.Application;

import com.example.intervenant.myapplication.com.example.intervenant.core.ProductProvider;

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
        cartList = ProductProvider.provideFromMemory(getBaseContext());
    }

    public static MyApp getInstance() {
        return instance;
    }

    public ArrayList<Product> getCartList() {
        return cartList;
    }

    public void setCartList(ArrayList<Product> list) {
        this.cartList = list;
        ProductProvider.saveToMemory(this, this.cartList);
    }

    public void removeInCartList(int i) {
        this.cartList.remove(i);
        ProductProvider.saveToMemory(this, this.cartList);
    }

    public void clearCartList() {
        this.cartList.clear();
        ProductProvider.saveToMemory(this, this.cartList);
    }
}
