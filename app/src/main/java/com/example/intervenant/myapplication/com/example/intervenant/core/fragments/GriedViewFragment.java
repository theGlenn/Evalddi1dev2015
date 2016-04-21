package com.example.intervenant.myapplication.com.example.intervenant.core.fragments;

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
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.DetailActivity;
import com.example.intervenant.myapplication.com.example.intervenant.core.Food;
import com.example.intervenant.myapplication.com.example.intervenant.core.FoodProvider;
import com.example.intervenant.myapplication.com.example.intervenant.core.FoodProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GriedViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GriedViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GriedViewFragment extends Fragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String POSITION = "position";

    // TODO: Rename and change types of parameters
    private int position;

    private OnFragmentInteractionListener mListener;

    ArrayList<Food> list = new ArrayList<>();
    GridView gridView;
    GridAdapter adapter;

    public GriedViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param position Parameter 1.
     * @return A new instance of fragment GriedViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GriedViewFragment newInstance(int position) {
        GriedViewFragment fragment = new GriedViewFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        fragment.setArguments(args);
        return fragment;

        /*MListViewFragment fragment = new MListViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, type);
        fragment.setArguments(args);
        return fragment;*/
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            list = new ArrayList<>();
            position = getArguments().getInt(POSITION);

            //if(position == 0) {
                FoodProvider.provideFromServer(getContext(), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("JSON/", response.toString());
                        JSONArray jsonArray = response.optJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.optJSONObject(i);
                            Food food = null;
                            try {
                                food = new Food(obj);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            list.add(food);
                        }
                        adapter.notifyDataSetChanged();
                    }

                });
            /*} else {
                list = FoodProvider.provideFromCart(getContext());
            }*/
            adapter = new GridAdapter(list);
        }
    }

    /*
    if(listType == 0){
               FruitProvider.provideFromServer(getContext(), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("JSON/",response.toString());
                        JSONArray jsonArray = response.optJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.optJSONObject(i);
                            Fruit fruit = new Fruit(obj);
                            list.add(fruit);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
                setHasOptionsMenu(true);
            }else{
                list = FruitProvider.provideFromFavorite();
            }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        if (getArguments() != null) {
            list = new ArrayList<>();
            listType = getArguments().getInt(ARG_PARAM1);


            if(listType == 0) {
                this.setHasOptionsMenu(true);
            }

            adapter = new ListTestAdapter(list);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (listType == 0) {
            FruitProvider.provideFromServer(getContext(), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Gson gson = new Gson(); // Or use new GsonBuilder().create();
                    JSONArray json = response.optJSONArray("data");

                    Type listType = new TypeToken<List<Fruit>>() {
                    }.getType();

                    ArrayList<Fruit> array = gson.fromJson(json.toString(), listType);

                    adapter.update(array);
                }
            });

        } else {
            adapter.update(FruitProvider.provideFromFavorite(this.getContext()));
        }
    }
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_gried_view, container, false);

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_gried_view, container, false);

        gridView = (GridView) view.findViewById(R.id.gridview);
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
        Food food = (Food) adapter.getItem(i);
        if (mListener != null) {
            mListener.onFragmentGridInteraction(food);
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
        void onFragmentGridInteraction(Food food);
    }

    private class GridAdapter extends BaseAdapter {

        ArrayList<Food> mList;

        GridAdapter(ArrayList<Food> list){
            mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int i) {
            return mList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            Food food = (Food) getItem(i);
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            ViewHolder holder;
            if(view == null){
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.grid_item, parent, false);
                holder.textView  = (TextView) view.findViewById(R.id.food_text);
                holder.imgView = (ImageView) view.findViewById(R.id.food_img);
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }

            holder.textView.setText(food.name);

            if(food.image != null){
                Glide.with(getContext()).load(food.image).into(holder.imgView);
            }

            return view;
        }

        public class ViewHolder {
            TextView textView;
            ImageView imgView;
        }
    }
}
