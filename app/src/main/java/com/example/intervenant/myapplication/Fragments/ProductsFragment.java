package com.example.intervenant.myapplication.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Response;
import com.example.intervenant.myapplication.Adapters.ProductsAdapter;
import com.example.intervenant.myapplication.Product;
import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.ProductProvider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int mParam1;

    private OnFragmentInteractionListener mListener;

    GridView gridView;
    ProductsAdapter adapter;
    List<Product> productsList = new ArrayList<>();

    public ProductsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProductsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductsFragment newInstance(int listType) {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, listType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            // IF WE NEED TO DIFFERENTIATE TABS
            mParam1 = getArguments().getInt(ARG_PARAM1);

            ProductProvider.provideFromServer(getContext(), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Gson gson = new Gson();
                    JSONArray json = response.optJSONArray("data");

                    Type listType = new TypeToken<List<Product>>() {
                    }.getType();

                    ArrayList<Product> array = gson.fromJson(json.toString(), listType);

                    productsList.clear();
                    productsList.addAll(array);
                    adapter.notifyDataSetChanged();
                }
            });

            adapter = new ProductsAdapter(getContext(), productsList);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        gridView = (GridView) view.findViewById(R.id.gridView);
        if (gridView != null) {
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    // TODO: ADD PRODUCT DETAILS
//                    Product product = adapter.getItem(i);
//
//                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
//                    intent.putExtra("name", product.getName());
//                    intent.putExtra("price", product.getPrice());
//                    intent.putExtra("info", product.getInfo());
//                    intent.putExtra("imageUrl", product.getImageUrl());
//
//                    startActivity(intent);
                }
            });
            gridView.setAdapter(adapter);
        }

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
