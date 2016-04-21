package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Context;

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

import org.json.JSONObject;

import java.util.ArrayList;

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

}
