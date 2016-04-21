package com.example.intervenant.myapplication.com.example.intervenant.core.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.intervenant.myapplication.MyApp;
import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.Adapters.ProductCartListAdapter;
import com.example.intervenant.myapplication.com.example.intervenant.core.Adapters.ProductGridAdapter;
import com.example.intervenant.myapplication.com.example.intervenant.core.Product;
import com.example.intervenant.myapplication.com.example.intervenant.core.ProductProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductCartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductCartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductCartFragment extends Fragment {

    private ProductCartFragment.Listener mListener;

    ArrayList<Product> list;
    ProductCartListAdapter listAdapter;
    ListView listView;

    public ProductCartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProductCartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductCartFragment newInstance() {
        ProductCartFragment fragment = new ProductCartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            setHasOptionsMenu(true);
            list = MyApp.getInstance().getProductsInCart();

            listAdapter = new ProductCartListAdapter(list);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_product_cart, container, false);

        listView = (ListView) view.findViewById(R.id.listView);
        // listView.setOnItemClickListener(this);
        listView.setAdapter(listAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProductCartFragment.Listener) {
            mListener = (ProductCartFragment.Listener) context;
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
    public void onResume() {
        super.onResume();
        listAdapter.notifyDataSetChanged();
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
    public interface Listener {
        // TODO: Update argument type and name
        // void onFragmentCartInteraction(int position, Product product);
    }
}
