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
import com.example.intervenant.myapplication.ProductObject;
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

    public static ArrayList<ProductObject> ProvideFromServer() {
        ArrayList<ProductObject> list = new ArrayList<>();
        list.add(new ProductObject(R.drawable.text, "Banana"));
        list.add(new ProductObject(R.drawable.text, "Apple"));
        list.add(new ProductObject(R.drawable.text, "Cherry"));

        return list;
    }

    public static void ProvideFromServer(Context context, final ProviderListener callback) {
        //final ArrayList<ProductObject> product = new ArrayList<>();
        String url = "https://api.myjson.com/bins/27dd6";
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jsonData = null;

                        jsonData = response.optJSONArray("data");

                        Type listType = new TypeToken<ArrayList<ProductObject>>() {}.getType();
                        List<ProductObject> fruitList = new Gson().fromJson(jsonData.toString(), listType);

                        callback.provideProductList(fruitList);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }
        });

        queue.add(jsonObjectRequest);
    }

    public static  ArrayList<ProductObject> provideFromCart(Context context){
        SharedPreferences settings = context.getApplicationContext().getSharedPreferences("productscart", Context.MODE_PRIVATE);
        String jsonString = settings.getString("jsonProduct", "[]");

        Type type = new TypeToken<ArrayList<ProductObject>>() {}.getType();

        ArrayList<ProductObject> json = new Gson().fromJson(jsonString, type);

        return json;
    }

    public static void addToCart(Context context, ProductObject product){
        ArrayList<ProductObject> productList = provideFromCart(context);
        productList.add(product);
        saveToCart(context, productList);
    }

    public static void removeFromCart(Context context, ProductObject product){
        ArrayList<ProductObject> productList = provideFromCart(context);
        productList.remove(product);
        saveToCart(context, productList);
    }

    public static boolean checkCartExist(Context context, ProductObject product){
        ArrayList<ProductObject> productList = provideFromCart(context);
        return productList.contains(product);
    }

    public static void saveToCart(Context context, ArrayList<ProductObject> product){
        SharedPreferences settings = context.getApplicationContext().getSharedPreferences("productscart", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        Type type = new TypeToken<ArrayList<ProductObject>>() {}.getType();
        String json = new Gson().toJson(product, type);
        editor.putString("jsonProduct", json);
        editor.apply();
    }

    public static void totalPrice(Context context) {
        SharedPreferences settings = context.getApplicationContext().getSharedPreferences("productscart", Context.MODE_PRIVATE);
        String jsonString = settings.getString("jsonProduct", "[]");

        Type type = new TypeToken<ArrayList<ProductObject>>() {}.getType();

        ArrayList<ProductObject> json = new Gson().fromJson(jsonString, type);

        Log.e("json", String.valueOf(jsonString));
    }

    public interface ProviderListener{
        void provideProductList(List<ProductObject> productObjectList);
    }

}
