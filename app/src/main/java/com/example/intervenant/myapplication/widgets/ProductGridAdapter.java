package com.example.intervenant.myapplication.widgets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.Product;
import com.example.intervenant.myapplication.R;

import java.util.ArrayList;

/**
 * Created by fmotte on 21/04/16.
 */
public class ProductGridAdapter extends BaseAdapter {

    ArrayList<Product> mList;

    public ProductGridAdapter(ArrayList<Product> list){
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

        if(view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.grid_view_layout, parent, false);

            holder.name = (TextView) view.findViewById(R.id.product_name);
            holder.image = (ImageView) view.findViewById(R.id.product_image);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.name.setText(product.name);
        Glide.with(parent.getContext()).load(product.image).into(holder.image);

        return view;
    }

    @Override
    public Product getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder {
        TextView name;
        ImageView image;
    }
}