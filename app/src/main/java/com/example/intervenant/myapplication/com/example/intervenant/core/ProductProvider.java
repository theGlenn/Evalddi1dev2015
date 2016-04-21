package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.intervenant.myapplication.Product;
import com.example.intervenant.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by intervenant on 18/04/16.
 */
public class ProductProvider {

    public static  ArrayList<Product> provideFromBasket(){
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product("apple", "http://vignette4.wikia.nocookie.net/farmville2/images/3/34/Lime.png/revision/latest?cb=20121011124753", "2", "foo"));
        list.add(new Product("grapefruit", "http://vignette4.wikia.nocookie.net/farmville2/images/3/34/Lime.png/revision/latest?cb=20121011124753", "2", "bar"));

        return list;
    }

    public static void provideFromServer(Context context, final VolleyCallback callback){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://api.myjson.com/bins/27dd6";
        final ArrayList<Product> list = new ArrayList<Product>();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jArray = (JSONArray)response.getJSONArray("data");

                            for (int i = 0; i < jArray.length(); i++){
                                JSONObject json_data = jArray.getJSONObject(i);

                                list.add(new Product(
                                        json_data.getString("name"),
                                        json_data.getString("image"),
                                        json_data.getString("price"),
                                        json_data.getString("info")));

                                callback.onSuccess(list);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        // Access the RequestQueue through your singleton class.
        queue.add(jsObjRequest);
    }

    public interface VolleyCallback{
        void onSuccess(ArrayList<Product> string);
    }
}
