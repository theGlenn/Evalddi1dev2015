package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.intervenant.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by intervenant on 18/04/16.
 */
public class ProductProvider {

    public static ArrayList<Product> provideFromServer(final Context context,Response.Listener<JSONObject> callback){
        RequestQueue queue = Volley.newRequestQueue(context);

        ArrayList<Product> list = new ArrayList<>();
        String URL = "https://api.myjson.com/bins/27dd6";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null,callback,  new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
                        Log.d("ERROR", error.toString());
                    }
                });

        queue.start();

        queue.add(jsObjRequest);

        return list;
    }

    public static  ArrayList<Product> provideFromFavorite(Context ctx){
        ArrayList<Product> list = new ArrayList<>();
        final String KEY = "PRODUCT";
        SharedPreferences sharedpreferences;
        sharedpreferences = ctx.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String strJson = sharedpreferences.getString(KEY,"[]");//second parameter is necessary ie.,Value to return if this preference does not exist.
        if(strJson != null) {
            try {
                JSONArray jsonData = new JSONArray(strJson);
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Product>>() {}.getType();
                list = gson.fromJson(jsonData.toString(), listType);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

}
