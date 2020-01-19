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
import com.example.patriotbumiandroid.activity.DonationDetail.DonationDetailActivity;
import com.example.patriotbumiandroid.activity.ProductDetail.ProductDetailActivity;
import com.example.patriotbumiandroid.model.Product.Product;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DonationRecyclerViewAdapter extends RecyclerView.Adapter<DonationRecyclerViewAdapter.ViewHolder> {
    public String[] image, name, url;

    public DonationRecyclerViewAdapter(String[] donationImage, String[] donationName, String[] donationUrl) {
        image = donationImage;
        name = donationName;
        url = donationUrl;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView image;
        public ProgressBar progressBar;
        public LinearLayout cardDonation;
        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.nameDonation);
            image = view.findViewById(R.id.donationImage);
            cardDonation = view.findViewById(R.id.cardDonation);
            progressBar = view.findViewById(R.id.progressBar);
        }
    }

    @NonNull
    @Override
    public DonationRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_donation, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        try {
            Picasso
                    .get()
                    .load(image[position])
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
        holder.name.setText(name[position]);
        holder.cardDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DonationDetailActivity.class);
                intent.putExtra("url", url[position]);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.length;
    }
}
