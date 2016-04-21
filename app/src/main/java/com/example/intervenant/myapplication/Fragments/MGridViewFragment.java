package com.example.intervenant.myapplication.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.intervenant.myapplication.ProductsGridAdapter;
import com.example.intervenant.myapplication.ProductObject;
import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.ProductProvider;

import java.util.ArrayList;
import java.util.List;


public class MGridViewFragment extends Fragment implements AdapterView.OnItemClickListener{

    private ProductsGridAdapter adapter;
    private OnFragmentListInteractionListener mListener;
    public static final String KEY = "key";
    public int gridViewType;

    ArrayList<ProductObject> list;
    private int listType;
    GridView gridView;
    private static final String ARG_PARAM1 = "param1";



    public MGridViewFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MGridViewFragment newInstance(int type) {
        MGridViewFragment fragment = new MGridViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            list = new ArrayList<>();
            listType = getArguments().getInt(ARG_PARAM1);

            setHasOptionsMenu(true);
            ProductProvider.ProvideFromServer(getContext(), new ProductProvider.ProviderListener() {

                @Override
                public void provideProductList(List<ProductObject> product) {
                    adapter.update(product);
                }
            });
            list = ProductProvider.provideFromCart(getContext());
            adapter = new ProductsGridAdapter(list);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grid_view, container, false);

        gridView = (GridView) view.findViewById(R.id.gridView);
        gridView.setOnItemClickListener(this);
        gridView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentListInteractionListener) {
            mListener = (OnFragmentListInteractionListener) context;
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
        ProductObject product =  adapter.getItem(i);
        if (mListener != null) {
            mListener.onFragmentListInteraction(product);
        }
    }

    public interface OnFragmentListInteractionListener {
        // TODO: Update argument type and name
        void onFragmentListInteraction(ProductObject productObject);
    }
}
