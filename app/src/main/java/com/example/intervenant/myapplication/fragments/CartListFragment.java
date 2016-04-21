package com.example.intervenant.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.intervenant.myapplication.Product;
import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.ProductProvider;
import com.example.intervenant.myapplication.widgets.CartListAdapter;

import java.util.ArrayList;

/**
 * Created by fmotte on 21/04/16.
 */
public class CartListFragment extends Fragment {

    private static final String POSITION = "position";
    private ListView listView;
    private CartListAdapter adapter;
    private ArrayList<Product> list;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param position
     * @return A new instance of fragment ProductGridViewFragment.
     */
    public static CartListFragment newInstance(int position) {

        CartListFragment fragment = new CartListFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);

        if(menuVisible) {
            list = ProductProvider.provideFromCart(getContext());

            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = ProductProvider.provideFromCart(getContext());

        adapter = new CartListAdapter(list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart_list_view, container, false);

        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        return view;
    }
}