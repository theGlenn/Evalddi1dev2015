package com.example.intervenant.myapplication.com.example.intervenant.core.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.Product;

import java.util.ArrayList;

/**
 * Created by etienne-dldc on 21/04/2016.
 */
/*
public class ProductGridAdapter extends BaseAdapter {

    ArrayList<Product> mList;

    ProductGridAdapter(ArrayList<Product> list){
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        Product product = getItem(i);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.product_grid_item, parent, false);
            holder.textView  = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.textView.setText(fruit.name);
        if(fruit.image != null){
            Glide.with(parent.getContext()).load(fruit.image).into(holder.imgView);
        } else if (fruit.resid != -1) {
            holder.imgView.setImageResource(fruit.resid);
        }

        return view;
    }

    @Override
    public Product getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder {
        TextView textView;
    }
}
*/