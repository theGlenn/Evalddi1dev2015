package com.example.intervenant.myapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Response;
import com.example.intervenant.myapplication.Product;
import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.ProductProvider;
import com.example.intervenant.myapplication.widgets.ProductGridAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by fmotte on 21/04/16.
 */
public class ProductGridFragment extends Fragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String POSITION = "position";

    // TODO: Rename and change types of parameters
    private int position;

    private OnFragmentListInteractionListener mListener;
    private GridView gridView;
    private ProductGridAdapter adapter;
    private ArrayList<Product> list;

    public ProductGridFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param position
     * @return A new instance of fragment ProductGridViewFragment.
     */
    public static ProductGridFragment newInstance(int position) {

        ProductGridFragment fragment = new ProductGridFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            position = getArguments().getInt(POSITION);
            list = new ArrayList<>();

            if(position == 0) {
                ProductProvider.provideFromServer(getContext(), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.d("myLogs", response.toString());

                        Gson gson = new Gson();
                        Type type = new TypeToken<ArrayList<Product>>(){}.getType();
                        JsonParser parser = new JsonParser();
                        JsonObject jo = (JsonObject) parser.parse(response.toString());
                        JsonArray ja = jo.getAsJsonArray("data");
                        ArrayList<Product> result = gson.fromJson(ja, type);

                        list.clear();
                        list.addAll(result);
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            adapter = new ProductGridAdapter(list);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grid_view, container, false);

        gridView = (GridView) view.findViewById(R.id.gridview);
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
        Product product = adapter.getItem(i);
        if (mListener != null) {
            mListener.onFragmentListInteraction(product);
        }
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
    public interface OnFragmentListInteractionListener {
        void onFragmentListInteraction(Product product);
    }
}