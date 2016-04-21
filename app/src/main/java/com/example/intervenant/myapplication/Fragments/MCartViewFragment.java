package com.example.intervenant.myapplication.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.intervenant.myapplication.CheckoutActivity;
import com.example.intervenant.myapplication.ProductObject;
import com.example.intervenant.myapplication.ProductsListAdapter;
import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.ProductProvider;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MCartViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MCartViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MCartViewFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public MCartViewFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MCartViewFragment newInstance(int position) {
        MCartViewFragment fragment = new MCartViewFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public static final String KEY = "key";
    ProductsListAdapter adapter;

    ArrayList<ProductObject> list;
    private int listType;
    ListView listView;
    private static final String ARG_PARAM1 = "param1";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            list = new ArrayList<>();
            listType = getArguments().getInt(ARG_PARAM1);
            setHasOptionsMenu(true);
        }
        adapter = new ProductsListAdapter(list);
    }

    @Override
    public void onResume() {
        super.onResume();

        adapter.update(ProductProvider.provideFromCart(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mcart_view, container, false);

        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.cart, menu );
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.buy:
                Intent myIntent = new Intent(getContext(), CheckoutActivity.class);
                startActivity(myIntent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
