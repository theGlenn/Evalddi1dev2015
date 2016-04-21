package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.intervenant.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by intervenant on 18/04/16.
 */
public class ProductProvider {

    static final String FILENAME_CART = "product_cart";

    public static void provideFromServer(Context context, final ProductProvider.Listener callback) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://api.myjson.com/bins/27dd6";
        final ArrayList<Product> list = new ArrayList<Product>();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
            Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArr = response.getJSONArray("data");
                        for (int i = 0; i < jsonArr.length(); i++) {
                            JSONObject item = jsonArr.getJSONObject(i);
                            list.add(new Product(item));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    callback.onAllProductFromServer(list);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {}
            }
        );
        // Add the request to the RequestQueue.
        queue.add(jsObjRequest);
    }

    public static ArrayList<Product> restoreCart (Context ctx) {

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences prefs = ctx.getSharedPreferences(FILENAME_CART, 0);
        String data = prefs.getString("data", "[]");

        Type dataType = new TypeToken<ArrayList<Product>>(){}.getType();
        Gson gson = new Gson();
        ArrayList<Product> result = gson.fromJson(data, dataType);

        return result;

    }

    public static void saveCart (Context ctx, ArrayList<Product> list) {

        Gson gson = new Gson();
        String data = gson.toJson(list);

        SharedPreferences prefs = ctx.getSharedPreferences(FILENAME_CART, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("data", data);

        editor.apply();

    }

    public interface Listener {
        void onAllProductFromServer(ArrayList<Product> allProducts);
    }


}
