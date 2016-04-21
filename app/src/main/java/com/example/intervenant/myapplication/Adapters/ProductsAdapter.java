package com.example.intervenant.myapplication.Adapters;

import android.content.Context;
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
public class ProductsAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Product> mList;

    public ProductsAdapter(Context ctx, List<Product> list) {
        mContext = ctx;
        mList = (ArrayList<Product>) list;
    }

    public int getCount() {
        return mList.size();
    }

    public Product getItem(int position) {
        return mList.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    //create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder holder = new ViewHolder();

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        if (view == null) {
            view = inflater.inflate(R.layout.list_item, null);
            holder.imageView = (ImageView) view.findViewById(R.id.product_image);
            holder.productName = (TextView) view.findViewById(R.id.product_name);
            holder.productPrice = (TextView) view.findViewById(R.id.product_price);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.productName.setText(mList.get(position).getName());

        String priceString = viewGroup.getContext().getString(R.string.dash);
        priceString = priceString.concat(viewGroup.getContext().getString(R.string.dollar));
        priceString = priceString.concat(String.valueOf(mList.get(position).getPrice()));

        holder.productPrice.setText(priceString);
        Glide.with(viewGroup.getContext()).load(mList.get(position).getImage()).into(holder.imageView);

        return view;
    }

    public class ViewHolder {

        ImageView imageView;
        TextView productName;
        TextView productPrice;

        public ViewHolder() {

        }
    }

}