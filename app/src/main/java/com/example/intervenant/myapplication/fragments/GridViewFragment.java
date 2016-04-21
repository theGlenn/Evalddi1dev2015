package com.example.intervenant.myapplication.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.Product;
import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.fragments.Providers.ProductProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GridViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GridViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GridViewFragment extends Fragment implements AdapterView.OnItemClickListener {

    GridBaseAdapter adapter;
    GridView gridView;

    ArrayList<Product> list;
    private int gridType;

    private static final String ARG_TYPE = "GridType";
    private OnFragmentInteractionListener mListener;

    public GridViewFragment() {
        // Required empty public constructor
    }

    public static GridViewFragment newInstance(int type) {
        GridViewFragment fragment = new GridViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = new ArrayList<>();

        if (getArguments() != null) {
            gridType = getArguments().getInt(ARG_TYPE);

            if (gridType == 0) {
                ProductProvider.provideFromServer(getActivity(), new Response.Listener<JSONObject>(){

                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonarray = response.getJSONArray("data");
                            for (int a = 0; a < jsonarray.length(); a++) {
                                String name = jsonarray.getJSONObject(a).getString("name");
                                String info = jsonarray.getJSONObject(a).getString("info");
                                String image = jsonarray.getJSONObject(a).getString("image");
                                String strPrice = jsonarray.getJSONObject(a).getString("price");

                                Float price = Float.parseFloat(strPrice);

                                list.add(new Product(name, info, image, price));
                            }
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                });
            }
        }
        adapter = new GridBaseAdapter(list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid_view, container, false);
        gridView = (GridView) view.findViewById(R.id.gridview);
        gridView.setOnItemClickListener(this);
        gridView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
        Product product =  adapter.getItem(i);
        if (mListener != null) {
            mListener.onFragmentInteraction(product);
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Product product);
    }


    public class GridBaseAdapter extends BaseAdapter {

        ArrayList<Product> mList;

        GridBaseAdapter(ArrayList<Product> list){
            mList = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Product getItem(int i) {
            return mList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {

            Product product = getItem(i);
            LayoutInflater inflater =(LayoutInflater) LayoutInflater.from(parent.getContext());



            ViewHolder holder;
            if(view == null){
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.grid_fragment_item, parent, false);
                holder.nameText  = (TextView) view.findViewById(R.id.product_text);
                holder.priceText  = (TextView) view.findViewById(R.id.gridPrice);
                holder.imageView = (ImageView) view.findViewById(R.id.productImage);
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }

            holder.nameText.setText(product.getName());
            holder.priceText.setText(product.getPrice().toString() + " $");

            if(product.getImage() != null) {
                Glide.with(parent.getContext())
                        .load(product.getImage())
                        .into(holder.imageView);
            }

            return view;

        }

        public class ViewHolder {
            TextView nameText;
            TextView priceText;
            ImageView imageView;
        }
    }

}
