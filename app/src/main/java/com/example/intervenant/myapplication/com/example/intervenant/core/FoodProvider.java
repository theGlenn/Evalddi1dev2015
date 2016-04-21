package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.intervenant.myapplication.Food;
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

    public static void putFoodInFavorite(Context ctx, Food f){
        ArrayList<Food> list = FoodProvider.provideFromFavorite(ctx);
        Log.i("add","before"+list.size());

        list.add(f);

        Log.i("add","after"+list.size());
        FoodProvider.saveToMemory(ctx,list);
    }

    public static  ArrayList<Food> provideFromFavorite(Context ctx){
        SharedPreferences data = ctx.getSharedPreferences("data", 0);
        ArrayList<Food> list = new ArrayList<>();
        String strJson = data.getString("favoritesFruits","[]");
        if(strJson != null) {
            try {
                JSONArray jsonData = new JSONArray(strJson);
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Food>>() {}.getType();
                list = gson.fromJson(jsonData.toString(), listType);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Log.i("provide","From Provide there are "+list.size());
        return list;
    }

    public static void saveToMemory(Context ctx, ArrayList<Food> list) {
        Log.i("save","saving1");

        SharedPreferences data = ctx.getSharedPreferences("data",0);
        SharedPreferences.Editor prefsEditor = data.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        prefsEditor.putString("favoritesFruits", json);
        prefsEditor.commit();
    }


    // TODO Remove
}
