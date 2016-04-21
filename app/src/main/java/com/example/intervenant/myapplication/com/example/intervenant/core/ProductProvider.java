package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.intervenant.myapplication.R;
import com.google.gson.Gson;

import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by avanderpotte on 21/04/16.
 */
public class ProductProvider {
    public static String provideFromCart(final Context context){
        SharedPreferences cart_products = context.getSharedPreferences("data", 0);
        String cartJson = cart_products.getString("cartProducts", null);

        return cartJson;
    }

    public static void provideFromServer(final Context context, Response.Listener<JSONObject> callback){
        RequestQueue queue = Volley.newRequestQueue(context);

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
