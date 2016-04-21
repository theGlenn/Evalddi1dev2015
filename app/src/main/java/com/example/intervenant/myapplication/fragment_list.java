package com.example.intervenant.myapplication;

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
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.com.example.intervenant.core.FoodProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragment_list.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_list#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_list extends Fragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    // TODO: Rename and change types of parameters

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int listType = 1;

    ListView listView;
    ListTestAdapter adapter;
    GridView gridView;

    ArrayList<Food> list;

    private OnFragmentInteractionListener mListener;

    public fragment_list() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment fragment_list.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_list newInstance(int columnCount ) {
        fragment_list fragment = new fragment_list();
        Bundle args = new Bundle();

        args.putInt(ARG_COLUMN_COUNT, columnCount);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            list = new ArrayList<>();
            listType = getArguments().getInt(ARG_COLUMN_COUNT);

            if(listType == 0){


                FoodProvider.provideFromServer(getActivity(), new Response.Listener<JSONObject>(){

                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonarray = response.getJSONArray("data");
                            for (int a = 0; a < jsonarray.length(); a++) {
                                String name = jsonarray.getJSONObject(a).getString("name");
                                String imageUrl = jsonarray.getJSONObject(a).getString("image");
                                String info = jsonarray.getJSONObject(a).getString("info");
                                int price = jsonarray.getJSONObject(a).getInt("price");

                                System.out.println(name + " -- " + imageUrl + " -- " + info + " -- " + price);
                                list.add(new Food(name, imageUrl, info, price));
                            }
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                });


                setHasOptionsMenu(true);    // info menu

            }else{
                //list = FoodProvider.provideFromFavorite();
            }

            adapter = new ListTestAdapter(list);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_list, container, false);

        gridView =  (GridView) view.findViewById(R.id.gridView);
        gridView.setOnItemClickListener(this);
        gridView.setAdapter(adapter);

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
        Food food =  adapter.getItem(i);
        if (mListener != null) {
            mListener.OnFragmentInteraction(food);
        }
    }


    public interface OnFragmentInteractionListener {
        void OnFragmentInteraction(Food food);
    }


    private class ListTestAdapter extends BaseAdapter {

        ArrayList<Food> mList;

        ListTestAdapter(ArrayList<Food> list){
            mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {

            Food food = getItem(i);
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            ViewHolder holder;
            if(view == null){
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.list_test_item, parent, false);
                holder.textView  = (TextView) view.findViewById(R.id.food_name);
                holder.imgView = (ImageView) view.findViewById(R.id.food_img);
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }

            holder.textView.setText(food.name);

            if(food.img != null) {
                Glide.with(parent.getContext())
                        .load(food.img)
                        .into(holder.imgView);
            }

            return view;
        }

        @Override
        public Food getItem(int i) {
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
