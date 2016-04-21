package com.example.intervenant.myapplication.com.example.intervenant.core.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.Product;

import java.util.ArrayList;

/**
 * Created by etienne-dldc on 21/04/2016.
 */
public class ProductGridAdapter extends BaseAdapter {

    ArrayList<Product> mList;

    public ProductGridAdapter(ArrayList<Product> list) {
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
            holder.name  = (TextView) view.findViewById(R.id.name);
            holder.image = (ImageView) view.findViewById(R.id.image);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.name.setText(product.name);

        if(product.image != null){
            Glide.with(parent.getContext()).load(product.image).into(holder.image);
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
        TextView name;
        ImageView image;
    }
}