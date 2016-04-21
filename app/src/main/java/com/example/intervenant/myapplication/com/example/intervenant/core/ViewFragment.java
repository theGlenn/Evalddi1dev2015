package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.MainActivity;
import com.example.intervenant.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.intervenant.myapplication.MainActivity.*;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewFragment extends Fragment implements AdapterView.OnItemClickListener {

    GridView gridView;
    ListView listView;
    GridViewAdapter adapter;
    ListTestAdapter listAdapter;


    ArrayList<Product> list;
    private int gridType;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    private OnFragmentInteractionListener mListener;

    public ViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param position Parameter 1.
     * @return A new instance of fragment ViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewFragment newInstance(int position) {
        ViewFragment fragment = new ViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            list = new ArrayList<>();
            gridType = getArguments().getInt(ARG_PARAM1);
            Log.v("grIDTYPe", String.valueOf(gridType));

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
                list = ProductProvider.provideFromFavorite(getContext());
                listAdapter = new ListTestAdapter(list);
            }
            adapter = new GridViewAdapter(list);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v("grIDTYPe", String.valueOf(gridType));
//
//        adapter.notifyDataSetChanged();
//        adapter = new GridViewAdapter(list);
        if(gridType == 1){

            ArrayList<Product> tempList = ProductProvider.provideFromFavorite(getContext());

            Log.v("PRODUCT IN CART", tempList.toString());
            list.clear();
            list.addAll(tempList);
            listAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_view, container, false);
        if(gridType == 0) {
            gridView = (GridView) view.findViewById(R.id.gridView);
            gridView.setOnItemClickListener(this);
            gridView.setAdapter(adapter);
        }else{
            listView = (ListView) view.findViewById(R.id.listView);
            listView.setOnItemClickListener(this);
            listView.setAdapter(adapter);
            listView.setVisibility(View.VISIBLE);
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Product product) {
        if (mListener != null) {
            mListener.onFragmentInteraction(product);
        }
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
        void onFragmentInteraction(Product product);
    }


    private class GridViewAdapter extends BaseAdapter {

        ArrayList<Product> mList;

        GridViewAdapter(ArrayList<Product> list){
            mList = list;
        }

        @Override
        public int getCount() {
            Log.i("getCount",""+mList.size());
            return mList.size();
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {

            Product product = getItem(i);
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            ViewHolder holder;
            if(view == null){
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.grid_item, parent, false);
                holder.nameView  = (TextView) view.findViewById(R.id.name);
                holder.priceView  = (TextView) view.findViewById(R.id.price);
                holder.imgView = (ImageView) view.findViewById(R.id.img);
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }

            holder.nameView.setText(product.name);
            holder.priceView.setText(String.valueOf(product.price) + "$");
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
            TextView nameView;
            TextView priceView;
            ImageView imgView;
        }
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

            Product Product = getItem(i);
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            ViewHolder holder;
            if(view == null){
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.liste_item, parent, false);
                holder.textView  = (TextView) view.findViewById(R.id.cart_text);
                holder.imgView = (ImageView) view.findViewById(R.id.cart_image);
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }

            holder.textView.setText(Product.name);
            Glide.with(parent.getContext()).load(mList.get(i).image).into(holder.imgView);

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
            TextView textView;
            ImageView imgView;
        }
    }
}
