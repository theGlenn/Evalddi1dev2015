package com.example.intervenant.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        TextView checkoutPrice = (TextView) findViewById(R.id.checkout_price);
        Button checkoutButton = (Button) findViewById(R.id.checkout_pay);

        ArrayList<Product> totalList = new ArrayList<>();

        totalList = MyApp.getInstance().getCartList();

        final int size = totalList.size();
        float totalPrice = 0f;

        for (int i = 0; i < size; i++) {
            Product product = totalList.get(i);
            totalPrice += product.getPrice();
        }

        assert checkoutPrice != null;
        String checkoutText = this.getString(R.string.checkout_price) + String.valueOf(totalPrice);
        checkoutPrice.setText(checkoutText);

        assert checkoutButton != null;
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getBaseContext(), "Thanks for buying!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                MyApp.getInstance().clearCartList();
                finish();
            }
        });
    }
}
