package com.example.intervenant.myapplication.com.example.intervenant.core.fragments.widgets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.intervenant.myapplication.R;

/**
 * Created by intervenant on 21/04/16.
 */
public class ProductsAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.product_grid_item, null, false);
        return v;
    }

    static class ViewHolder{
        ImageView imageView;
        Te
        public ViewHolder(){

        }
    }


}
