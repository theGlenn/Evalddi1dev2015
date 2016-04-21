package com.example.intervenant.myapplication.com.example.intervenant.core.widgets;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.Food;

import java.util.ArrayList;

/**
 * Created by totomac on 21/04/2016.
 */
public class GridAdapter extends BaseAdapter {
    ArrayList<Food> mList;

    public GridAdapter(ArrayList<Food> list){
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        Food food = (Food) getItem(i);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.grid_item, parent, false);

            holder.textView  = (TextView) view.findViewById(R.id.food_text);
            holder.imgView = (ImageView) view.findViewById(R.id.food_img);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.textView.setText(food.name);

        System.out.println(food.name);

        if(food.image != null){
            Glide.with(parent.getContext()).load(food.image).into(holder.imgView);
        }

        return view;
    }



    public class ViewHolder {
        TextView textView;
        ImageView imgView;
    }
}
