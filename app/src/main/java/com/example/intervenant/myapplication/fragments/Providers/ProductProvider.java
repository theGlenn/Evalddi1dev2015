package com.example.intervenant.myapplication.fragments.Providers;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.intervenant.myapplication.Product;
import com.example.intervenant.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Created by kmoutier on 21/04/16.
 */
public class ProductProvider {


    public static void provideFromServer(Context ctx, Response.Listener<JSONObject> callback){

        // API URL
        String url = "https://api.myjson.com/bins/27dd6";

        // Make Json Request
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, callback, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO Auto-generated method stub
            }

        });


        // Add Request to Queue
        RequestQueue queue = Volley.newRequestQueue(ctx);
        queue.add(jsObjRequest);
    }

    public static void Save(Context ctx, Product product)
    {

        // Get the Saved ArrayList
        ArrayList<Product> list = ProductProvider.Load(ctx);
        list.add(product);

        // Convert the ArrayList into String
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson Gson = gsonBuilder.create();
        String data = Gson.toJson(list).toString();

        // Write String into a File
        File file = new File(ctx.getFilesDir() +"/data.txt");

        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        try
        {
            try
            {
                    fos.write(data.getBytes());
            }
            catch (IOException e) {e.printStackTrace();}
        }
        finally
        {
            try
            {
                fos.close();
            }
            catch (IOException e) {e.printStackTrace();}
        }
    }


    public static ArrayList<Product> Load(Context ctx)
    {

        // Get the File where data are saved
        File file = new File(ctx.getFilesDir() +"/data.txt");
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String test;
        int anzahl=0;
        try
        {
            while ((test=br.readLine()) != null)
            {
                anzahl++;
            }
        }
        catch (IOException e) {e.printStackTrace();}

        try
        {
            fis.getChannel().position(0);
        }
        catch (IOException e) {e.printStackTrace();}

        String[] array = new String[anzahl];

        String line;
        int i = 0;
        try
        {
            while((line=br.readLine())!=null)
            {
                array[i] = line;
                i++;
            }
        }
        catch (IOException e) {e.printStackTrace();}

        ArrayList<Product> list = new ArrayList<>();

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson Gson = gsonBuilder.create();
        JsonParser jsonParser = new JsonParser();

        // Get the String Result
        for (String str : array) {
            // Convert it into ArrayList
            JsonElement element = (JsonElement) jsonParser.parse(str);
            JsonObject result = element.getAsJsonObject();

            // TODO: This code is not working
            list = Gson.fromJson(result, new TypeToken<ArrayList<Product>>(){}.getType());
        }

        return list;
    }
}
