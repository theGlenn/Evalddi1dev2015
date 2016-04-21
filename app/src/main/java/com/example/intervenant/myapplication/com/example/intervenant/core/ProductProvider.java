package com.example.intervenant.myapplication.com.example.intervenant.core;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.intervenant.myapplication.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by intervenant on 18/04/16.
 */
public class ProductProvider {


    public static void provideFromServer(ProductProviderListener listener){
        JsonObjectRequest productRequest = new JsonObjectRequest("", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    public interface ProductProviderListener{
        public void onProducts(List<Product> product);

    }

}
