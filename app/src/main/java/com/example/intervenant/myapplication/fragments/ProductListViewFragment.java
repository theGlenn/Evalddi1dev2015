package com.example.intervenant.myapplication.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.intervenant.myapplication.Product;
import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.ProductProvider;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ProductListViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductListViewFragment extends Fragment implements AdapterView.OnItemClickListener {

    GridView gridView;
    ProductListAdapter adapter;

    private static final String ARG_PARAM1 = "param1";

    ArrayList<Product> list;
    ArrayList<Product> cartList;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    private int listType;

    private OnFragmentListInteractionListener mListener;

    public ProductListViewFragment() {
        // Required empty public constructor
    }

    public static ProductListViewFragment newInstance(int type) {
        ProductListViewFragment fragment = new ProductListViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPref = getActivity().getSharedPreferences("products", Context.MODE_PRIVATE);
        editor     = sharedPref.edit();

        //Retrive cart
        ArrayList<Product> cartList = null;

        try {
            cartList = ProductProvider.provideFromBasket(sharedPref);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (getArguments() != null) {
            list = new ArrayList<>();
            listType = getArguments().getInt(ARG_PARAM1);

            if(listType == 0){
                ProductProvider.provideFromServer(getContext(), new ProductProvider.VolleyCallback() {
                    @Override
                    public void onSuccess(ArrayList<Product> list) {
                        adapter.mList = list;
                        adapter.notifyDataSetChanged();
                    }
                });
            }else{
                list = new ArrayList<Product>(cartList);
            }

            adapter = new ProductListAdapter(list, cartList);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_product_list_view, container, false);

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
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product product =  adapter.getItem(position);
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

    private class ProductListAdapter extends BaseAdapter {

        ArrayList<Product> mList;
        ArrayList<Product> cartList;

        ProductListAdapter(ArrayList<Product> list, ArrayList<Product> cart){
            mList = list;
            cartList = cart;
;        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {

            final Product product = getItem(i);
            product.inCart = isInCart(product, cartList);
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            final ViewHolder holder;
            if(view == null){
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.fragment_item, parent, false);
                holder.textView  = (TextView) view.findViewById(R.id.productName);
                holder.imgView = (ImageView) view.findViewById(R.id.productImage);
                holder.button = (TextView) view.findViewById(R.id.button);

                if(product.inCart) {
                    holder.button.setText("-");
                    holder.button.setBackgroundColor(Color.RED);
                }

                holder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(product.inCart) {
                            removeFromCart(product);
                            adapter.notifyDataSetChanged();
                        }
                        else {
                            addToCart(product);
                            holder.button.setText("-");
                            holder.button.setBackgroundColor(Color.RED);
                        }
                    }
                });

                view.setTag(holder);
            } else{
                holder = (ViewHolder) view.getTag();
            }

            holder.textView.setText(product.name);

            Picasso.with(getContext())
                    .load(product.image)
                    .fit()
                    .into(holder.imgView);

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
            ImageView imgView;
            TextView textView;
            TextView button;
        }

        public void addToCart(Product product) {
            cartList.add(product);

            Gson gson = new Gson();
            String json = gson.toJson(cartList);
            editor.putString("cart", json);
            editor.commit();
        }

        public void removeFromCart(Product product) {
            cartList.remove(product);

            for(Product item : list) {
                if(item.name == product.name) {
                    list.remove(item);
                }
            }

            Gson gson = new Gson();
            String json = gson.toJson(cartList);
            editor.putString("cart", json);
            editor.commit();
        }

        private Boolean isInCart(Product product, ArrayList<Product> cartList) {
            Boolean isIn = false;

            for(int i = 0; i < cartList.size(); i++) {
                isIn = isIn ? isIn : product.name.equals(cartList.get(i).name);
            }

            return isIn;
        }
    }
}
