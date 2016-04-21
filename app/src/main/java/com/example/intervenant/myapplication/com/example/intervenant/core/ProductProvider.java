package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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
import com.example.intervenant.myapplication.R;
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

    public static void provideFromServer (Context context, Response.Listener<JSONObject> callback) {

        String url = "https://api.myjson.com/bins/27dd6";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, callback, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        Cache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        RequestQueue mRequestQueue;

// Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);
        // Start the queue
        mRequestQueue.start();
        // Add the request to the RequestQueue.
        mRequestQueue.add(jsObjRequest);

    }

    public static  ArrayList<Product> provideFromCart(Context ctx){
        SharedPreferences data = ctx.getSharedPreferences("data", 0);
        ArrayList<Product> list = new ArrayList<>();
        String strJson = data.getString("cart","[]");//second parameter is necessary ie.,Value to return if this preference does not exist.
        if(strJson != null) {
            try {
                JSONArray jsonData = new JSONArray(strJson);
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Product>>() {}.getType();
                list = gson.fromJson(jsonData.toString(), listType);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

//        Log.i("provide","From Provide there are "+list.get(0).name+list.get(0).favorite+" "+list.get(1).name+list.get(1).favorite+" "+list.get(3).name+list.get(3).favorite);
        Log.i("provide","From Provide there are "+list.size());
        return list;
    }

    public static void putProductInCart(Context ctx, Product f){
        ArrayList<Product> list = ProductProvider.provideFromCart(ctx);
        Log.i("add","before"+list.size());
//        if(!FruitProvider.isInFavorite(ctx, f)){
        list.add(f);
/*        } else {
            Log.i("add","was already !");
        }
*/
        Log.i("add","after"+list.size());
        ProductProvider.saveToMemory(ctx,list);
    }

    public static void saveToMemory(Context ctx, ArrayList<Product> list) {
        Log.i("save","saving1");

        SharedPreferences data = ctx.getSharedPreferences("data",0);
        SharedPreferences.Editor prefsEditor = data.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        prefsEditor.putString("cart", json);
        prefsEditor.commit();
    }

}
