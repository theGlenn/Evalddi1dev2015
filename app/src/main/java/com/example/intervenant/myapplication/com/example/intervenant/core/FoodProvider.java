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
import com.example.intervenant.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by intervenant on 18/04/16.
 */
public class FoodProvider {

    public static void saveToMemory(Context context, ArrayList<Food> list) {

        SharedPreferences data = context.getSharedPreferences("data",0);
        SharedPreferences.Editor prefsEditor = data.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        prefsEditor.putString("cart", json);
        prefsEditor.commit();
    }

    public static  ArrayList<Food> provideFromCart(Context ctx){
        SharedPreferences data = ctx.getSharedPreferences("data", 0);
        ArrayList<Food> list = new ArrayList<>();
        String strJson = data.getString("cart","[]");//second parameter is necessary ie.,Value to return if this preference does not exist.
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

//        Log.i("provide","From Provide there are "+list.get(0).name+list.get(0).favorite+" "+list.get(1).name+list.get(1).favorite+" "+list.get(3).name+list.get(3).favorite);
        Log.i("provide","From Provide there are "+ strJson);
        return list;
    }

    public static void putFoodInCart(Context ctx, Food f){
        ArrayList<Food> list = FoodProvider.provideFromCart(ctx);
        Log.i("add","before"+list.size());
//        if(!FruitProvider.isInFavorite(ctx, f)){
        list.add(f);
/*        } else {
            Log.i("add","was already !");
        }
*/
        Log.i("add","after"+list.size());
        FoodProvider.saveToMemory(ctx,list);
    }

    public static void removeFoodFromCart(Context ctx, Food f){
        ArrayList<Food> list = FoodProvider.provideFromCart(ctx);
        Log.i("remove","before"+list.size());
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).name.equals(f.name)) {
                list.remove(i);
                break;
            }
        }
        Log.i("remove","after"+list.size());
        FoodProvider.saveToMemory(ctx,list);
    }

    public static boolean isInCart(Context ctx, Food f){
        ArrayList<Food> list;
        list = FoodProvider.provideFromCart(ctx);
        int i;
        for (i = 0; i < list.size(); i++) {
            if(list.get(i).name.equals(f.name)) {
                Log.i("isInFav","equals");
                break;
            }
        }

        return i!=list.size();
    }


    public static void provideFromServer(Context context, Response.Listener cb) {

        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "https://api.myjson.com/bins/27dd6";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                cb, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }
        });

        queue.add(jsObjRequest);
    }

}
