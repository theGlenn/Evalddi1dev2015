package com.example.intervenant.myapplication.fragments.normal;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.model.Product;
import com.example.intervenant.myapplication.model.ProductProvider;
import com.example.intervenant.myapplication.fragments.ProductFragment;
import com.example.intervenant.myapplication.widgets.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;



public class ProductGridFragment extends ProductFragment implements ProductProvider.ProductProviderListener, AdapterView.OnItemClickListener {

    private ProductsAdapter mAdapter;
    private GridView gridView;
    private ArrayList<Product> mProducts = new ArrayList<>();

    public ProductGridFragment() {
        // Required empty public constructor
    }


    public static ProductGridFragment newInstance() {
        return new ProductGridFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

        mAdapter = new ProductsAdapter(mProducts);
        ProductProvider.provideFromServer(getContext(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_product_grid, container, false);
        gridView = (GridView) v.findViewById(R.id.product_grid);
        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(this);

        return v;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnProductItemClickListener) {
            mListener = (OnProductItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onProducts(List<Product> products) {
        mAdapter.update(products);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Product product = mProducts.get(i);
        mListener.onProductItemSelected(product, (ProductsAdapter.ViewHolder) view.getTag());
    }


}
