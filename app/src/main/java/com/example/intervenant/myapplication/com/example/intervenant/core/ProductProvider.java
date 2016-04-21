package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Context;
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

import org.json.JSONObject;

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

}
