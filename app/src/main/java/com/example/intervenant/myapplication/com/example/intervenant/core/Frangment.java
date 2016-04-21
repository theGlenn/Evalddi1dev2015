package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Frangment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Frangment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frangment extends Fragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    GridView gridView;
    ListView listView;
    ListPagerAdapter adapter;
    ListPagerAdapter adapterCart;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ArrayList<Product> list;
    private int listType;

    public Frangment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment Frangment.
     */
    // TODO: Rename and change types and number of parameters
    public static Frangment newInstance(int position) {
        Frangment fragment = new Frangment();
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
            listType = getArguments().getInt(ARG_PARAM1);

            if(listType == 0){
                ProductProvider.provideFromServer(getContext(), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson(); // Or use new GsonBuilder().create();
                        JSONArray json = response.optJSONArray("data");

                        Type listType = new TypeToken<List<Product>>(){}.getType();

                        ArrayList<Product> array = gson.fromJson(json.toString(), listType);
                        Log.d("onCreate", ""+array.size());
                        list.clear();
                        list.addAll(array);
                        adapter.notifyDataSetChanged();
                    }
                });
            }else{

            }

            adapter = new ListPagerAdapter(list);
            adapterCart = new ListPagerAdapter(list);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (listType == 0) {
            ProductProvider.provideFromServer(getContext(), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Gson gson = new Gson(); // Or use new GsonBuilder().create();
                    JSONArray json = response.optJSONArray("data");

                    Type listType = new TypeToken<List<Product>>() {
                    }.getType();

                    ArrayList<Product> array = gson.fromJson(json.toString(), listType);
                    gridView.setVisibility(View.VISIBLE);

                    adapter.update(array);
                }
            });

        } else {
            adapterCart.update(ProductProvider.provideFromCart(this.getContext()));
            listView.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_frangment, container, false);


        gridView = (GridView) view.findViewById(R.id.gridView);
        gridView.setOnItemClickListener(this);
        gridView.setAdapter(adapter);

        listView = (ListView) view.findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapterCart);




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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Product product);


    }

    private class ListPagerAdapter extends BaseAdapter {

        ArrayList<Product> mList;

        ListPagerAdapter(ArrayList<Product> list){
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
                view = inflater.inflate(R.layout.grid_item, parent, false);
                holder.textView  = (TextView) view.findViewById(R.id.textViewProduct);
                holder.imgView = (ImageView) view.findViewById(R.id.imageView);
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }

            holder.textView.setText(product.name);
            Glide.with(getContext()).load(product.image).into(holder.imgView);
//            holder.imgView.setImageResource(product.image);

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

        public void update(ArrayList<Product> list) {
            mList.clear();
            mList.addAll(list);
            this.notifyDataSetChanged();
        }


        public class ViewHolder {
            TextView textView;
            ImageView imgView;
        }
    }
}
