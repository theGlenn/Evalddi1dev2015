package com.example.intervenant.myapplication.fragments.normal;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.model.Product;
import com.example.intervenant.myapplication.model.ProductProvider;
import com.example.intervenant.myapplication.fragments.ProductFragment;
import com.example.intervenant.myapplication.widgets.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;



public class ProductCartFragment extends ProductFragment implements AdapterView.OnItemClickListener {

    private OnProductItemClickListener mListener;
    ListView mListView;
    private ProductsAdapter mAdapter;
    private ArrayList<Product> mList;

    public ProductCartFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProductCartFragment newInstance() {
        return new ProductCartFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mList = new ArrayList<>();
        mAdapter = new ProductsAdapter(mList, true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_product_cart, container, false);

        mListView = (ListView) v.findViewById(android.R.id.list);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Product> ps = ProductProvider.provideFromLocal(getContext());
        Toast.makeText(getContext(), "ok" + ps.size(), Toast.LENGTH_LONG).show();
        mAdapter.update(ps);
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Product product = mAdapter.getItem(i);
        mListener.onProductItemSelected(product, (ProductsAdapter.ViewHolder) view.getTag());
    }
}
