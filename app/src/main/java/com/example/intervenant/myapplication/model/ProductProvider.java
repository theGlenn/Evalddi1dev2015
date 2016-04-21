package com.example.intervenant.myapplication.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.intervenant.myapplication.MyApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by intervenant on 18/04/16.
 */
public class ProductProvider {

    private static final String URL = "https://api.myjson.com/bins/27dd6";

    public static void provideFromServer(Context context) {
        if (context instanceof ProductProviderListener) {
            final ProductProviderListener listener = (ProductProviderListener) context;
            provideFromServer(context, listener);
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ProductProviderListener");
        }
    }

    public static void provideFromServer(Context context, final ProductProviderListener listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest productRequest = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("URL", response.toString());
                JSONArray data = response.optJSONArray("data");


                Type type = new TypeToken<List<Product>>() {
                }.getType();
                List<Product> productList = new Gson().fromJson(data.toString(), type);

                listener.onProducts(productList);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(productRequest);
    }

    public static ArrayList<Product> provideFromLocal(Context context) {

        MyApp app = (MyApp) context.getApplicationContext();

        return app.products;

    }

    public static void addToCart(Product product){
        MyApp.getInstance().products.add(product);
    }

    public interface ProductProviderListener {
        void onProducts(List<Product> product);

    }

}
