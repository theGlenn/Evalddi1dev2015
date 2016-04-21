package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.intervenant.myapplication.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by fmotte on 18/04/16.
 */
public class ProductProvider {

    public static final String URL_API = "https://api.myjson.com/bins/27dd6";

    public static ArrayList<Product> provideFromServer(Context context, Response.Listener<JSONObject> cb) {

        ArrayList<Product> list = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, URL_API, (String) null, cb, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                    }
                });

        queue.add(jsObjRequest);
        queue.start();

        return list;
    }

    public static ArrayList<Product> provideFromCart(Context ctx) {
        SharedPreferences data = ctx.getSharedPreferences("app", 0);
        ArrayList<Product> list = new ArrayList<>();
        String strJson = data.getString("cart","[]");
        if(strJson != null) {
            try {
                JSONArray jsonData = new JSONArray(strJson);
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<Product>>() {}.getType();
                list = gson.fromJson(jsonData.toString(), listType);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public static void putProductInCart(Context ctx, Product f){
        ArrayList<Product> list = ProductProvider.provideFromCart(ctx);

        list.add(f);

        ProductProvider.saveToMemory(ctx,list);
    }

    public static void saveToMemory(Context ctx, ArrayList<Product> list) {

        SharedPreferences data = ctx.getSharedPreferences("app", 0);
        SharedPreferences.Editor prefsEditor = data.edit();

        Gson gson = new Gson();
        String json = gson.toJson(list);

        prefsEditor.putString("cart", json);
        prefsEditor.commit();
    }

    public static void clearMemory(Context ctx) {
        SharedPreferences data = ctx.getSharedPreferences("app", 0);
        SharedPreferences.Editor prefsEditor = data.edit();

        prefsEditor.clear();
    }
}
