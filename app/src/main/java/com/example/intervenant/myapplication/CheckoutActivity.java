package com.example.intervenant.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.intervenant.myapplication.com.example.intervenant.core.ProductProvider;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_checkout);


        Button clickButton = (Button) findViewById(R.id.button);
        assert clickButton != null;
        clickButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Bought",
                        Toast.LENGTH_LONG).show();
            }
        });

        ProductProvider.totalPrice(getApplicationContext());

    }


    @Override
    protected void onResume() {
        super.onResume();

        setTitle("Checkout & Buy");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
