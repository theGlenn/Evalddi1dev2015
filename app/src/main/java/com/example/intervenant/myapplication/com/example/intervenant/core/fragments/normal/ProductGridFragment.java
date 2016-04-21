package com.example.intervenant.myapplication.com.example.intervenant.core.fragments.normal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.fragments.widgets.ProductsAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductGridFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductGridFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductGridFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ProductsAdapter mAdapter;
    private GridView gridView;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_product_grid, container, false);
        gridView = (GridView) v.findViewById(R.id.product_grid);
        gridView.setAdapter(new ProductsAdapter());

        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            //mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
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
