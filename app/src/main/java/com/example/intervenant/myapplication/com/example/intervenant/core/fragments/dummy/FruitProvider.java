package com.example.intervenant.myapplication.com.example.intervenant.core.fragments.dummy;

import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.Fruit;

import java.util.ArrayList;

/**
 * Created by intervenant on 18/04/16.
 */
public class FruitProvider {

    public static  ArrayList<Fruit> provideFromServer(){
        ArrayList<Fruit> list = new ArrayList<>();
        list.add(new Fruit("banana", R.drawable.banana));
        list.add(new Fruit("pear", R.drawable.pear));
        list.add(new Fruit("apple", R.drawable.apple));
        list.add(new Fruit("grapefruit", R.drawable.grapefruit));
        list.add(new Fruit("orange", R.drawable.orange));

        list.add(new Fruit("banana", R.drawable.banana));
        list.add(new Fruit("pear", R.drawable.pear));
        list.add(new Fruit("apple", R.drawable.apple));
        list.add(new Fruit("grapefruit", R.drawable.grapefruit));
        list.add(new Fruit("orange", R.drawable.orange));
        return list;
    }

    public static  ArrayList<Fruit> provideFromFavorite(){
        ArrayList<Fruit> list = new ArrayList<>();
        list.add(new Fruit("apple", R.drawable.apple));
        list.add(new Fruit("grapefruit", R.drawable.grapefruit));

        return list;
    }

    public static  ArrayList<Fruit> provideFruits(int type){
        return type == 0 ? provideFromServer() : provideFromFavorite();
    }
}
