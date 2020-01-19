package com.example.patriotbumiandroid.activity.ProductDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.activity.Cart.CartActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView name, price, coin, description;
    private ImageView image;
    private Button productButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        name = findViewById(R.id.productTitle);
        price = findViewById(R.id.productPrice);
        coin = findViewById(R.id.productCoin);
        description = findViewById(R.id.productDescription);
        image = findViewById(R.id.productImage);
        productButton = findViewById(R.id.productButton);

        final Intent intent = getIntent();

        name.setText(intent.getStringExtra("name"));
        price.setText(intent.getStringExtra("price"));
        description.setText(intent.getStringExtra("description"));
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

        productButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pay = new Intent(view.getContext(), CartActivity.class);
                pay.putExtra("name", intent.getStringExtra("name"));
                pay.putExtra("price", intent.getStringExtra("price"));
                pay.putExtra("image", intent.getStringExtra("image"));
                startActivity(pay);
            }
        });
    }
}
