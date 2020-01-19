package com.example.patriotbumiandroid.adapter.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.activity.ProductDetail.ProductDetailActivity;
import com.example.patriotbumiandroid.model.Product.Product;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {
    public List<Product> productList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price, coin;
        public ImageView image;
        public LinearLayout cardProduct;
        public ProgressBar progressBar;
        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.product_name);
            price = view.findViewById(R.id.product_price);
            coin = view.findViewById(R.id.product_coin);
            image = view.findViewById(R.id.product_image);
            cardProduct = view.findViewById(R.id.cardProduct);
            progressBar = view.findViewById(R.id.progressBar);
        }
    }

    public ProductRecyclerViewAdapter(List<Product> myDataSet) {
        productList = myDataSet;
    }

    @NonNull
    @Override
    public ProductRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        try {
            Picasso
                    .get()
                    .load("http://192.168.43.34/patriot-bumi-api/public" + productList.get(position).getImage())
                    .into((ImageView) holder.image, new Callback() {
                        @Override
                        public void onSuccess()
                        {
                            holder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d("picasso failed", e.toString());
                            holder.progressBar.setVisibility(View.GONE);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.name.setText(productList.get(position).getName());
        holder.price.setText("Rp " + productList.get(position).getPrice());
        holder.coin.setText("50 Koin");
        holder.cardProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ProductDetailActivity.class);
                intent.putExtra("name", productList.get(position).getName());
                intent.putExtra("price", productList.get(position).getPrice());
                intent.putExtra("image", productList.get(position).getImage());
                intent.putExtra("description", productList.get(position).getDescription());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
