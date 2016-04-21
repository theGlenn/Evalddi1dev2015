package com.example.intervenant.myapplication.com.example.intervenant.core.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.intervenant.myapplication.MyApp;
import com.example.intervenant.myapplication.ProductDetailActivity;
import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.Adapters.ProductGridAdapter;
import com.example.intervenant.myapplication.com.example.intervenant.core.Product;
import com.example.intervenant.myapplication.com.example.intervenant.core.ProductProvider;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductGridFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductGridFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductGridFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ProductGridFragment.Listener mListener;

    ProductGridAdapter gridAdapter;
    GridView gridView;

    public ProductGridFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProductGridFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductGridFragment newInstance() {
        ProductGridFragment fragment = new ProductGridFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gridAdapter = new ProductGridAdapter(new ArrayList<Product>());

        if (getArguments() != null) {
            setHasOptionsMenu(true);

            MyApp.getInstance().getAllProducts(getContext(), new MyApp.Listener() {
                @Override
                public void onAllProductsLoaded(ArrayList<Product> allProducts) {
                    gridAdapter.updateList(allProducts);
                    gridAdapter.notifyDataSetChanged();
                    Log.d("yolo", String.valueOf(allProducts.size()));
                }
            });
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_product_grid, container, false);

        gridView = (GridView) view.findViewById(R.id.gridView);
        gridView.setOnItemClickListener(this);
        gridView.setAdapter(gridAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProductGridFragment.Listener) {
            mListener = (ProductGridFragment.Listener) context;
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product product = gridAdapter.getItem(position);
        if (mListener != null) {
            mListener.onFragmentGridInteraction(product);
        }
    }

    /*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.main, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    */

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface Listener {
        void onFragmentGridInteraction(Product product);
    }
}
