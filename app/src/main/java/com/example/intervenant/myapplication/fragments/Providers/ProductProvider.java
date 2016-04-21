package com.example.intervenant.myapplication.fragments.Providers;

import android.content.Context;

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
 * Created by kmoutier on 21/04/16.
 */
public class ProductProvider {

    public static void provideFromServer(Context ctx, Response.Listener<JSONObject> callback){

        // API URL
        String url = "https://api.myjson.com/bins/27dd6";

        // Make Json Request
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, callback, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO Auto-generated method stub
            }

        });


        // Add Request to Queue
        RequestQueue queue = Volley.newRequestQueue(ctx);
        queue.add(jsObjRequest);
    }
}
