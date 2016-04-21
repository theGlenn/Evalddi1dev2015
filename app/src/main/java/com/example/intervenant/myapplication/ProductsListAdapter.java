package com.example.intervenant.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ksomville on 21/04/2016.
 */
public class ProductsListAdapter extends BaseAdapter {


    ArrayList<ProductObject> mList;

    public ProductsListAdapter(ArrayList<ProductObject> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public ProductObject getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ProductObject product = (ProductObject) getItem(i);
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_single, null);
            holder.textView = (TextView) view.findViewById(R.id.text_view_item);
            holder.imageView = (ImageView) view.findViewById(R.id.image_view_item);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.textView.setText(product.name);
        Glide.with(viewGroup.getContext()).load(product.image).into(holder.imageView);

        return view;
    }

    public void update(List<ProductObject> products){
        mList.clear();
        mList.addAll(products);
        this.notifyDataSetChanged();
    }

    public class ViewHolder {
        TextView textView;
        ImageView imageView;

    }
}