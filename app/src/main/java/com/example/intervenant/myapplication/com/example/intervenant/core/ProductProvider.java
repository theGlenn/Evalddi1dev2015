package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.intervenant.myapplication.Product;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by intervenant on 18/04/16.
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
}
