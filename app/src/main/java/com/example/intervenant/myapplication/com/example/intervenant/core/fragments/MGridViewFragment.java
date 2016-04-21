package com.example.intervenant.myapplication.com.example.intervenant.core.fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.Product;
import com.example.intervenant.myapplication.com.example.intervenant.core.ProductProvider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by avanderpotte on 21/04/16.
 */
public class MGridViewFragment extends Fragment implements AdapterView.OnItemClickListener{
    GridView gridView;
    ListTestAdapter adapter;

    private static final String ARG_PARAM1 = "param1";

    private OnFragmentListInteractionListener mListener;

    ArrayList<Product> list;
    private int gridType;

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
            gridType = getArguments().getInt(ARG_PARAM1);

            if(gridType == 0){
                ProductProvider.provideFromServer(getContext(),  new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();

                        Gson gson = new Gson(); // Or use new GsonBuilder().create();
                        JSONArray json = null;
                        json = response.optJSONArray("data");

                        Type gridType = new TypeToken<List<Product>>(){}.getType();

                        ArrayList<Product> data  = gson.fromJson(json.toString(), gridType);

                        list.clear();
                        list.addAll(data);
                        adapter.notifyDataSetChanged();

                    }
                });
            }else{
                list = ProductProvider.provideFromFavorite();
                this.setHasOptionsMenu(true);
            }

            adapter = new ListTestAdapter(list);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mgrid_view, container, false);

        gridView = (GridView)view.findViewById(R.id.gridView);
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
        Product product =  adapter.getItem(i);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.pay:
                sync();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sync() {
        Toast.makeText(getContext(), "Product added to your cart", Toast.LENGTH_SHORT).show();
    }

    private class ListTestAdapter extends BaseAdapter {

        ArrayList<Product> mList;

        ListTestAdapter(ArrayList<Product> list){
            mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {

            Product product = getItem(i);
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            ViewHolder holder;
            if(view == null){
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.grid_test_item, parent, false);
                holder.textName  = (TextView) view.findViewById(R.id.product_name);
                holder.textPrice  = (TextView) view.findViewById(R.id.product_price);
                holder.imgView = (ImageView) view.findViewById(R.id.product_img);
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }

            holder.textName.setText(product.name);
            holder.textPrice.setText("$" + product.price);
            Glide.with(parent.getContext()).load(product.image).into(holder.imgView);


            return view;
        }

        @Override
        public Product getItem(int i) {
            return mList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        public class ViewHolder {
            TextView textName;
            TextView textPrice;
            ImageView imgView;
        }
    }
}
