package com.example.intervenant.myapplication.com.example.intervenant.core.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intervenant.myapplication.R;
import com.example.intervenant.myapplication.com.example.intervenant.core.DetailActivity;
import com.example.intervenant.myapplication.com.example.intervenant.core.Fruit;
import com.example.intervenant.myapplication.com.example.intervenant.core.fragments.dummy.FruitProvider;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MListViewFragment.OnFragmentListInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MListViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MListViewFragment extends Fragment  implements AdapterView.OnItemClickListener {

    ListView listView;
    ListTestAdapter adapter;

    private static final String ARG_PARAM1 = "param1";

    private OnFragmentListInteractionListener mListener;

    ArrayList<Fruit> list;
    private int listType;

    public MListViewFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MListViewFragment newInstance(int type) {
        MListViewFragment fragment = new MListViewFragment();
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
                list = FruitProvider.provideFromServer();
            }else{
                list = FruitProvider.provideFromFavorite();
            }

            adapter = new ListTestAdapter(list);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mlist_view, container, false);

        listView = (ListView)view.findViewById(R.id.listView);
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
        Fruit fruit =  adapter.getItem(i);
        if (mListener != null) {
            mListener.onFragmentListInteraction(fruit);
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
        void onFragmentListInteraction(Fruit fruit);
    }

    private class ListTestAdapter extends BaseAdapter {

        ArrayList<Fruit> mList;

        ListTestAdapter(ArrayList<Fruit> list){
            mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {

            Fruit fruit = getItem(i);
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            ViewHolder holder;
            if(view == null){
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.list_test_item, parent, false);
                holder.textView  = (TextView) view.findViewById(R.id.fruit_text);
                holder.imgView = (ImageView) view.findViewById(R.id.fruit_img);
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }

            holder.textView.setText(fruit.name);
            holder.imgView.setImageResource(fruit.image);

            return view;
        }

        @Override
        public Fruit getItem(int i) {
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
