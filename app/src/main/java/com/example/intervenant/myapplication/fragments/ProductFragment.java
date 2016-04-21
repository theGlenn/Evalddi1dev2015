package com.example.intervenant.myapplication.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.View;

import com.example.intervenant.myapplication.model.Product;
import com.example.intervenant.myapplication.widgets.ProductItemRecyclerViewAdapter;
import com.example.intervenant.myapplication.widgets.ProductsAdapter;

/**
 * Created by intervenant on 21/04/16.
 */
public abstract class  ProductFragment extends Fragment {

    protected OnProductItemClickListener mListener;

    public interface OnProductItemClickListener {
        // TODO: Update argument type and name
        void onProductItemSelected(Product product, ProductsAdapter.ViewHolder holder);
    }
}
