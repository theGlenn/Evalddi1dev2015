package com.example.intervenant.myapplication.com.example.intervenant.core;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intervenant.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class ListTestActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    ListTestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<Fruit> list = new ArrayList<>();
        list.add(new Fruit("banana", R.drawable.banana));
        list.add(new Fruit("pear", R.drawable.pear));
        list.add(new Fruit("apple", R.drawable.apple));
        list.add(new Fruit("grapefruit", R.drawable.grapefruit));
        list.add(new Fruit("orange", R.drawable.orange));

        adapter = new ListTestAdapter(list);
        setContentView(R.layout.activity_list_test);
        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Fruit fruit =  adapter.getItem(i);
        Toast.makeText(this, "Clicked" + i, Toast.LENGTH_SHORT).show();
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra("name", fruit.name);
        detailIntent.putExtra("image", fruit.image);

        startActivity(detailIntent);
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
        public View getView(int i, View view, ViewGroup viewGroup) {

            Fruit fruit = getItem(i);
            LayoutInflater inflater = getLayoutInflater();
            view = inflater.inflate(R.layout.list_test_item,null);

            TextView textView = (TextView) view.findViewById(R.id.fruit_text);
            textView.setText(fruit.name);

            ImageView imgView = (ImageView) view.findViewById(R.id.fruit_img);
            imgView.setImageResource(fruit.image);

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
    }
}
