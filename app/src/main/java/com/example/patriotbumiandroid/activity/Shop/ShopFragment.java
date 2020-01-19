package com.example.patriotbumiandroid.activity.Shop;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.adapter.RecyclerView.ProductRecyclerViewAdapter;
import com.example.patriotbumiandroid.adapter.RecyclerView.PromoRecyclerViewAdapter;
import com.example.patriotbumiandroid.data.APIService;
import com.example.patriotbumiandroid.data.APIUtils;
import com.example.patriotbumiandroid.model.Product.Product;

import java.util.List;

public class ShopFragment extends Fragment {

    private APIService mAPIService;
    private RecyclerView popularRecyclerView, promoRecyclerView;
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;
    private ProductRecyclerViewAdapter productRecyclerViewAdapter;
    private PromoRecyclerViewAdapter promoRecyclerViewAdapter;
    private String[] mDatasetProduct = {"Kompos", "Sedotan", "Tumblr", "Kompos", "Sedotan", "Tumblr"};
    private String[] mDatasetPromo = {"Earth Hour Promo", "Trash Promo", "Earth Hour Promo"};

    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        popularRecyclerView = view.findViewById(R.id.shop_popular);
        promoRecyclerView = view.findViewById(R.id.shop_promo);

        mAPIService = APIUtils.getAPIService();

        initPopularData();
        initPromo();
    }

    private void initPopularData() {
//        Call<List<Product>> productCall = mAPIService.getProduct();
//        productCall.enqueue(new Callback<List<Product>>() {
//            @Override
//            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
//                initPopular(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<Product>> call, Throwable t) {
//
//            }
//        });
    }

    private void initPopular(List<Product> body) {
        gridLayoutManager = new GridLayoutManager(this.getActivity(), 3);
        popularRecyclerView.setHasFixedSize(true);
        popularRecyclerView.setLayoutManager(gridLayoutManager);
        productRecyclerViewAdapter = new ProductRecyclerViewAdapter(body);
        popularRecyclerView.setAdapter(productRecyclerViewAdapter);
    }

    private void initPromo() {
        linearLayoutManager = new LinearLayoutManager(this.getActivity(), RecyclerView.HORIZONTAL, false);
        promoRecyclerView.setHasFixedSize(true);
        promoRecyclerView.setLayoutManager(linearLayoutManager);
        promoRecyclerViewAdapter = new PromoRecyclerViewAdapter(mDatasetPromo);
        promoRecyclerView.setAdapter(promoRecyclerViewAdapter);
    }
}
