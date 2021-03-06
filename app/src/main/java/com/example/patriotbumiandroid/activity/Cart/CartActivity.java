package com.example.patriotbumiandroid.activity.Cart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.activity.Transfer.TransferActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class CartActivity extends AppCompatActivity {

    private ImageView image, back;
    private TextView title, price, coin;
    private LinearLayout bankTransfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        title = findViewById(R.id.productTitle);
        image = findViewById(R.id.productImage);
        price = findViewById(R.id.productPrice);
        back = findViewById(R.id.back);
        bankTransfer = findViewById(R.id.bankTransfer);

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

        bankTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(view.getContext(), TransferActivity.class);
                intent1.putExtra("name", intent.getStringExtra("name"));
                intent1.putExtra("price", intent.getStringExtra("price"));
                intent1.putExtra("image", intent.getStringExtra("image"));
                startActivity(intent1);
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
