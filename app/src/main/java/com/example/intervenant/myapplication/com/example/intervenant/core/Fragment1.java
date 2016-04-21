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
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.ProductDetail;
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
 * {@link Fragment1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment implements AdapterView.OnItemClickListener  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    GridView gridView;
    ListView listView;
    ListAdapter adapter;
    ListAdapter listviewadapter;

    ArrayList<Product> list;
    private int listType;

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(int i) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            list = new ArrayList<>();
            listType = getArguments().getInt(ARG_PARAM1);


            if(listType == 0) {
                this.setHasOptionsMenu(true);
            }

            adapter = new ListAdapter(list);
            listviewadapter = new ListAdapter(list);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);

        gridView = (GridView)view.findViewById(R.id.gridView);
        gridView.setOnItemClickListener(this);
        gridView.setAdapter(adapter);

        listView = (ListView)view.findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        listView.setAdapter(listviewadapter);

        return view;
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

                    listView.setVisibility(View.GONE);
                    gridView.setVisibility(View.VISIBLE);

                    adapter.update(array);
                }
            });

        } else {

            listviewadapter.update(ProductProvider.provideFromCart(this.getContext()));
            listView.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.GONE);
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

    private class ListAdapter extends BaseAdapter {

        ArrayList<Product> mList;

        ListAdapter(ArrayList<Product> list){
            mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public View getView(int i, View view, final ViewGroup parent) {

            Product product = getItem(i);
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            ViewHolder holder;
            if(view == null){
                holder = new ViewHolder();
                if(listType == 0) {
                    view = inflater.inflate(R.layout.grid_item, parent, false);
                }
                else {
                    view = inflater.inflate(R.layout.list_item, parent, false);
                    holder.button = (Button) view.findViewById(R.id.cartButton);
                }
                holder.textView  = (TextView) view.findViewById(R.id.text);
                holder.imgView = (ImageView) view.findViewById(R.id.img);
                view.setTag(holder);

                if(listType == 1) {

                    holder.button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            View parentRow = (View) view.getParent();
                            ListView listView = (ListView) parentRow.getParent();
                            final int position = listView.getPositionForView(parentRow);

                            mList.remove(position);

                            update();
                            ProductProvider.removeProductFromCart(getContext(), position);

                        }
                    });
                }
            }else{
                holder = (ViewHolder) view.getTag();
            }

            holder.textView.setText(product.name);
            Glide.with(parent.getContext()).load(mList.get(i).getImageUrl()).into(holder.imgView);

            return view;
        }

        public void update(ArrayList<Product> list) {
            mList.clear();
            mList.addAll(list);
            this.notifyDataSetChanged();
        }

        public void update() {
            this.notifyDataSetChanged();
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
            Button button;
        }
    }
}
