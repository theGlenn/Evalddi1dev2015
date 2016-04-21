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
import com.example.intervenant.myapplication.R;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by intervenant on 18/04/16.
 */
public class ProductProvider {



    public static void provideFromServer(final Context ctx, Response.Listener<JSONObject> cb) {


        String url = "https://api.myjson.com/bins/27dd6";
        RequestQueue mRequestQueue = Volley.newRequestQueue(ctx);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest (Request.Method.GET, url, null,cb, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(ctx, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        mRequestQueue.add(jsObjRequest);
    }

    public static  ArrayList<Product> provideFromFavorite(Context context){
        ArrayList<Product> list = new ArrayList<>();

        return list;
    }

}
