package com.example.intervenant.myapplication.com.example.intervenant.core.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.Product;

import java.util.ArrayList;

/**
 * Created by pnguyen on 21/04/16.
 */
public class ProductFragmentView extends Fragment implements AdapterView.OnItemClickListener {

    GridView listView;
    ListTestAdapter adapter;

    private static final String ARG_PARAM1 = "param1";

    private OnFragmentListInteractionListener mListener;

    ArrayList<Product> list;
    private int listType;

    public ProductFragmentView() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ProductFragmentView newInstance(int type) {
        ProductFragmentView fragment = new ProductFragmentView();
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

            if(listType == 0){
                ProductProvider.provideFromServer(getContext(), new ProductProvider.ProductProviderListener() {
                    @Override
                    public void provideProducts(ArrayList<Product> products) {
                        list.clear();
                        list.addAll(products);
                        adapter.notifyDataSetChanged();
                    }
                });

                setHasOptionsMenu(true);
            }else{
                list = ProductProvider.provideFromFavorite();
            }

            adapter = new ListTestAdapter(list);


        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mlist_view, container, false);

        listView = (GridView)view.findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

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
                view = inflater.inflate(R.layout.product_item, parent, false);
                holder.textView  = (TextView) view.findViewById(R.id.product_text);
                holder.imgView = (ImageView) view.findViewById(R.id.product_img);
                holder.textPrice = (TextView) view.findViewById(R.id.product_price);

                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }

            holder.textView.setText(product.name);
            holder.textPrice.setText(product.price + " €");
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
            TextView textView;
            ImageView imgView;
            TextView textPrice;
        }
    }
}
