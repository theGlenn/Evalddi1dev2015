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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.Product;
import com.example.intervenant.myapplication.com.example.intervenant.core.ProductProvider;

import java.util.ArrayList;
import java.util.List;

public class MGridViewFragment extends Fragment implements AdapterView.OnItemClickListener{

    ListView listView;
    ProductListAdapter adapter;

    private static final String ARG_PARAM1 = "param1";
    private int listType;
    ArrayList<Product> list;
    private OnFragmentInteractionListener mListener;

    public MGridViewFragment() {
        // Required empty public constructor
    }

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
            listType = getArguments().getInt(ARG_PARAM1);

        }
        adapter = new ProductListAdapter(list, getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_mgrid_view, container, false);
        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(new ProductListAdapter(list, getActivity()));
        ProductProvider.provideFromServer(getContext(), new ProductProvider.ProviderListener(){
            @Override
            public void provideProductlist(List<Product> productList) {
                //Log.e("data", productList.toString());
                adapter.update(productList);
            }
        });
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getContext(), "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        gridview.setAdapter(adapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class ProductListAdapter extends BaseAdapter {

        ArrayList<Product> mList;
        private Context mContext;

        ProductListAdapter(ArrayList<Product> list, Context context){
            mList = list;
            mContext = context;
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
                view = inflater.inflate(R.layout.grid_item_layout, parent, false);
                holder.textView  = (TextView) view.findViewById(R.id.product_grid_text);
                holder.imgView = (ImageView) view.findViewById(R.id.product_grid_image);
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }

            holder.textView.setText(product.name);
            Glide.with(getActivity()).load(product.image).into(holder.imgView);
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

        public void update(List<Product> list) {
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
