package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.intervenant.myapplication.R;

import java.util.ArrayList;

/**
 * Created by intervenant on 18/04/16.
 */
public class ProductProvider {

    /*public static  ArrayList<Food> provideFromServer(){
        ArrayList<Food> list = new ArrayList<>();
        list.add(new Food("banana", R.drawable.banana));
        list.add(new Food("pear", R.drawable.pear));
        list.add(new Food("apple", R.drawable.apple));
        list.add(new Food("grapefruit", R.drawable.grapefruit));
        list.add(new Food("orange", R.drawable.orange));

        list.add(new Food("banana", R.drawable.banana));
        list.add(new Food("pear", R.drawable.pear));
        list.add(new Food("apple", R.drawable.apple));
        list.add(new Food("grapefruit", R.drawable.grapefruit));
        list.add(new Food("orange", R.drawable.orange));
        return list;
    }*/

    public static void provideFromServer(Context context, Response.Listener cb) {

        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "https://api.myjson.com/bins/27dd6";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                cb, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }
        });

        queue.add(jsObjRequest);
    }

}
