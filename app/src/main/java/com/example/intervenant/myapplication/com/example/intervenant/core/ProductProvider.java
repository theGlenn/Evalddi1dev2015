package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.intervenant.myapplication.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by intervenant on 18/04/16.
 */
public class ProductProvider {

    public static void provideFromServer(Context context, Response.Listener<JSONObject> callback) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://api.myjson.com/bins/27dd6";
        final ArrayList<Product> list = new ArrayList<Product>();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, callback, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        // Add the request to the RequestQueue.
        queue.add(jsObjRequest);
    }

    public static ArrayList<Product> provideFromFavorite (Context ctx) {

        ArrayList<Product> list = new ArrayList<Product>();
        String FILENAME = "product_favorites";

        FileInputStream fos;
        try {
            fos = ctx.openFileInput(FILENAME);
            fos.read();
            fos.close();
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        ArrayList<Product> list = new ArrayList<Product>();
        list.add(new Product("Almond milk", 1.18, "Yolo", "http://www.tendances-nutrition.com/wp-content/uploads/lait-d-amande-et-riz-100-v-C3-A9g-C3-A9tal-soluble-300x300.png"));
        list.add(new Product("Yolo", 2.88, "Hey", "http://www.tendances-nutrition.com/wp-content/uploads/lait-d-amande-et-riz-100-v-C3-A9g-C3-A9tal-soluble-300x300.png"));
        */
        return list;

    }

    public static void addToLocalSave (Product product) {
        Gson gson = new Gson();
        String json = gson.toJson(product);
    }

}
