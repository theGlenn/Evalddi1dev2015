package com.example.intervenant.myapplication.widgets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by intervenant on 21/04/16.
 */
public class ProductsAdapter extends BaseAdapter {

    private final List<Product> mProducts;
    boolean isLocal = false;

    public ProductsAdapter(ArrayList<Product> products){
        mProducts = products;
    }

    public ProductsAdapter(ArrayList<Product> products, boolean local) {
        mProducts = products;
        isLocal = local;
    }

    @Override
    public int getCount() {
        return mProducts.size();
    }

    @Override
    public Product getItem(int i) {
        return mProducts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());

        ViewHolder holder;
        if(view == null){

            int res = !isLocal ? R.layout.product_grid_item : R.layout.product_local_item;

            view = inflater.inflate(res, parent, false);

            holder = new ViewHolder();

            holder.imageView = (ImageView) view.findViewById(R.id.product_img);
            holder.nameView = (TextView) view.findViewById(R.id.product_name);
            holder.priceView = (TextView) view.findViewById(R.id.product_price);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Product product = getItem(i);

        Glide.with(parent.getContext())
                .load(product.image)
                .thumbnail(0.8f)
                .into(holder.imageView);

        holder.nameView.setText(product.name);
        holder.priceView.setText(product.price + "€");

        return view;
    }

    public void update(List<Product> products){
        mProducts.clear();
        mProducts.addAll(products);

        notifyDataSetChanged();
    }

    public static class ViewHolder{
        public ImageView imageView;
        public TextView nameView;
        public TextView priceView;
    }


}
