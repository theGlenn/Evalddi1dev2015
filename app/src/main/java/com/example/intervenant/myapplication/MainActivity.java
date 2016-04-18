package com.example.intervenant.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.hello);
        textView.setText(R.string.text_test);

        Button goList = (Button)findViewById(R.id.goListButton);
        goList.setOnClickListener(this);
        goList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goListToListActivity();
            }
        });




    }

    @Override
    public void onClick(View view) {
        goListToListActivity();
    }

    public void goListToListActivity(){

    }
}
