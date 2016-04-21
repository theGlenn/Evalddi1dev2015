package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.intervenant.myapplication.Product;
import com.example.intervenant.myapplication.R;

import org.json.JSONObject;

import java.util.ArrayList;

public class ProductProvider {
    public static ArrayList<Product> provideFromCart(){
        ArrayList<Product> cart = new ArrayList<>();
        return cart;
    }

    public static ArrayList<Product> provideFromServer(final Context context, Response.Listener<JSONObject> callback){
        RequestQueue queue = Volley.newRequestQueue(context);

        ArrayList<Product> cart = new ArrayList<>();
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

        return cart;
    }
}
