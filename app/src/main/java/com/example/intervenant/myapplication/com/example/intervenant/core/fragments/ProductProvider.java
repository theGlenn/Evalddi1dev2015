package com.example.intervenant.myapplication.com.example.intervenant.core.fragments;

import android.app.DownloadManager;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by intervenant on 18/04/16.
 */
public class ProductProvider {
    public static ArrayList<Product> provideFromLocal(){

        ArrayList<Product> list = new ArrayList<>();
       /* list.add(new Product("doggy", R.drawable.chien));
        list.add(new Product("doggy", R.drawable.chien));*/

        return list;
    }

    public static  ArrayList<Product> provideFromFavorite(){
        ArrayList<Product> list = new ArrayList<>();
        //list.add(new Product("apple", R.drawable.apple));

        return list;
    }

    public interface ProductProviderListener{
        void provideProducts(ArrayList<Product> products);
    }

    public static ArrayList<Product> provideFromServer(final Context context, final ProductProviderListener callback) {
        final ArrayList<Product> list = new ArrayList<>();
        String urlJson = "https://api.myjson.com/bins/27dd6";
        RequestQueue queue = Volley.newRequestQueue(context);


        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, urlJson, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context, "Coucou",Toast.LENGTH_SHORT).show();

                        try {

                            JSONObject jsonObj = new JSONObject(response.toString());
                            JSONArray itemsArray = jsonObj.getJSONArray("data");

                            // Parsing json array response
                            // loop through each json object
                            for (int i = 0; i < itemsArray.length(); i++) {
                                JSONObject c = itemsArray.getJSONObject(i);

                                list.add(new Product(c));
                            }

                            callback.provideProducts(list);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }


                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //add request to queue
        queue.add(jsObjRequest);

        return list;
    }


}
