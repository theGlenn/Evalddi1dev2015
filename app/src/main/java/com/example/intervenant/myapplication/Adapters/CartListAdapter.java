package com.example.intervenant.myapplication.Adapters;

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
import java.util.List;

/**
 * Created by jfong on 21/04/16.
 */
public class CartListAdapter extends BaseAdapter {

    ArrayList<Product> mList;

    public CartListAdapter(List<Product> list) {
        mList = (ArrayList<Product>) list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Product getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.cart_item, viewGroup, false);
            holder.textView = (TextView) view.findViewById(R.id.cart_text);
            holder.imageView = (ImageView) view.findViewById(R.id.cart_image);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.textView.setText(mList.get(i).getName());
        Glide.with(viewGroup.getContext()).load(mList.get(i).getImage()).into(holder.imageView);

        return view;
    }

    static class ViewHolder {

        TextView textView;
        ImageView imageView;

        public ViewHolder() {

        }
    }
}
