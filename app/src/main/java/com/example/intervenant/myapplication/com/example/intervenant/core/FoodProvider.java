package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by Quentin on 21/04/16.
 */
public class FoodProvider {



    public static void provideFromServer(Context ctx, Response.Listener<JSONObject> callback){

        String url = "https://api.myjson.com/bins/27dd6";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest (Request.Method.GET, url, null, callback, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO Auto-generated method stub
            }
        });

        RequestQueue queue = Volley.newRequestQueue(ctx);
        queue.add(jsObjRequest);

    }
}
