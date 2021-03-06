package com.example.patriotbumiandroid.activity.Checkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.activity.PostAuthenticate.PostMainActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class CheckoutActivity extends AppCompatActivity {

    private Button button;
    private ImageView image, back;
    private TextView title, price, coin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        button = findViewById(R.id.productButton);
        title = findViewById(R.id.productTitle);
        image = findViewById(R.id.productImage);
        price = findViewById(R.id.productPrice);
        back = findViewById(R.id.back);

        final Intent intent = getIntent();

        title.setText(intent.getStringExtra("name"));
        price.setText("Rp " + intent.getStringExtra("price"));
        try {
            Picasso
                    .get()
                    .load("http://192.168.100.11/patriot-bumi-api/public" + intent.getStringExtra("image"))
                    .into((ImageView) image, new Callback() {
                        @Override
                        public void onSuccess()
                        {
//                                holder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d("picasso failed", e.toString());
//                                holder.progressBar.setVisibility(View.GONE);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), PostMainActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
