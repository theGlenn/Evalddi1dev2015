package com.example.intervenant.myapplication.com.example.intervenant.core.widgets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intervenant.myapplication.R;

import java.util.ArrayList;

/**
 * Created by totomac on 21/04/2016.
 */
private class ListAdapter extends BaseAdapter {

    ArrayList<Product> mList;

    ListAdapter(ArrayList<Product> list){
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
            view = inflater.inflate(R.layout.list_test_item, parent, false);
            holder.textView  = (TextView) view.findViewById(R.id.product_text);
            holder.imgView = (ImageView) view.findViewById(R.id.product_img);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.textView.setText(product.name);

        if(product.image != null){
            Glide.with(getContext()).load(product.image).into(holder.imgView);
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
        ImageView imgView;
    }
}