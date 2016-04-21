package com.example.intervenant.myapplication;

import android.content.Context;

import com.android.volley.Response;
import com.example.intervenant.myapplication.com.example.intervenant.core.Product;
import com.example.intervenant.myapplication.com.example.intervenant.core.ProductProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by etienne-dldc on 21/04/2016.
 */
public class MyApp {
    private static MyApp ourInstance = new MyApp();

    public static MyApp getInstance() {
        return ourInstance;
    }

    public ArrayList<Product> productsInCart = new ArrayList<Product>();
    public ArrayList<Product> allProducts;

    private MyApp() {

    }

    public ArrayList<Product> getProductsInCart () {
        return productsInCart;
    }

    public void addProductToCart (Product product) {
        productsInCart.add(product);
    }

    public void getAllProducts (Context context, final MyApp.Listener callback) {
        if (allProducts == null) {
            allProducts = new ArrayList<Product>();
            ProductProvider.provideFromServer(context, new ProductProvider.Listener() {
                @Override
                public void onAllProductFromServer(ArrayList<Product> allProductsFromServer) {
                    allProducts = allProductsFromServer;
                    callback.onAllProductsLoaded(allProducts);
                }
            });
        } else {
            callback.onAllProductsLoaded(allProducts);
        }
    }

    public interface Listener {
        void onAllProductsLoaded(ArrayList<Product> products);
    }


}
