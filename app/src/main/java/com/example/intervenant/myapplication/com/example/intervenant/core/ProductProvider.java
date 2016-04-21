package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Context;
import android.content.SharedPreferences;
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
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by intervenant on 18/04/16.
 */
public class ProductProvider {

    public static  void provideFromServer(final Context context, final ProviderListener callback){
        final ArrayList<Product> list = new ArrayList<>();
        String url = "https://api.myjson.com/bins/27dd6";
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.start();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jsonData = response.optJSONArray("data");

                        Type listType = new TypeToken<ArrayList<Product>>() {}.getType();
                        List<Product> productList = new Gson().fromJson(jsonData.toString(), listType);

                        callback.provideProductlist(productList);
                        //Toast.makeText(context, productList.get(0).name,
                        //       Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });
        queue.add(jsObjRequest);
    }

    public static  ArrayList<Product> provideFromCart(Context context){
        SharedPreferences settings = context.getApplicationContext().getSharedPreferences("cart", Context.MODE_PRIVATE);
        String jsonString = settings.getString("jsonProduct", "[]");

        Type type = new TypeToken<ArrayList<Product>>() {}.getType();

        ArrayList json = new Gson().fromJson(jsonString, type);

        return json;
    }

    public static void addToCart(Context context, Product obj){
        ArrayList<Product> list = provideFromCart(context);
        list.add(obj);
        saveToCart(context, list);
    }

    public static void saveToCart(Context context, ArrayList<Product> obj){
        SharedPreferences settings = context.getApplicationContext().getSharedPreferences("cart", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        Type type = new TypeToken<ArrayList<Product>>() {}.getType();
        String json = new Gson().toJson(obj, type);
        editor.putString("jsonProduct", json);
        editor.apply();
    }

    public interface ProviderListener{
        void provideProductlist(List<Product> fruitList);
    }

}
