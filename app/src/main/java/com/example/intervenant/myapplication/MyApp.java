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

    public ArrayList<Product> productsInCart;
    public ArrayList<Product> allProducts;

    private MyApp() {

    }

    public void restoreCart (Context ctx) {
        productsInCart = ProductProvider.restoreCart(ctx);
    }

    public ArrayList<Product> getProductsInCart () {
        return productsInCart;
    }

    public void addProductToCart (Context ctx, Product product) {
        productsInCart.add(product);
        ProductProvider.saveCart(ctx, productsInCart);
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

    public void removeProductAtIndex (int position) {
        productsInCart.remove(position);
    }

    public interface Listener {
        void onAllProductsLoaded(ArrayList<Product> products);
    }

}
