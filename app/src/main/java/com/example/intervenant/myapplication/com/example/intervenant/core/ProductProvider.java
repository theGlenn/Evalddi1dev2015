package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.intervenant.myapplication.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by intervenant on 18/04/16.
 */
public class ProductProvider {

    public static void provideFromServer(final Context ctx, Response.Listener<JSONObject> cb) {

        // Instantiate the cache
        Cache cache = new DiskBasedCache(ctx.getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        String url = "https://api.myjson.com/bins/27dd6";
        RequestQueue mRequestQueue;

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, cb, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(ctx, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
        mRequestQueue.add(jsObjRequest);

    }


    public static ArrayList<Product> provideFromMemory(Context ctx) {
        SharedPreferences data = ctx.getSharedPreferences("data", 0);
        ArrayList<Product> list = new ArrayList<>();
        String strJson = data.getString("jackyrgl", "[]");
        try {
            JSONArray jsonData = new JSONArray(strJson);
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Product>>(){}.getType();
            list = gson.fromJson(jsonData.toString(), listType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void saveToMemory(Context ctx, ArrayList<Product> list) {

        SharedPreferences data = ctx.getSharedPreferences("data", 0);
        SharedPreferences.Editor prefsEditor = data.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        prefsEditor.putString("jackyrgl", json);
        prefsEditor.apply();
    }

}
