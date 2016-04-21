package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class ProductProvider {



    public static String provideFromCart(final Context context) {
        String productsCart;

        SharedPreferences sharedpreferences = context.getSharedPreferences("data", 0);
        productsCart = sharedpreferences.getString("products_cart", null);

        return productsCart;
    }

    public static void provideFromServer(final Context context, Response.Listener<JSONObject> callback){
        RequestQueue queue = Volley.newRequestQueue(context);

        context.getSharedPreferences("data", 0).edit().clear().commit();

        String URL = "https://api.myjson.com/bins/27dd6";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null,callback,  new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
                        Log.d("ERROR", error.toString());
                    }
                });

        queue.start();

        queue.add(jsObjRequest);
    }
}
