package com.example.patriotbumiandroid.data;

import android.database.Observable;

import com.example.patriotbumiandroid.model.Activity.Activity;
import com.example.patriotbumiandroid.model.Article.Article;
import com.example.patriotbumiandroid.model.Product.Product;
import com.example.patriotbumiandroid.model.Rank;
import com.example.patriotbumiandroid.model.User.LoginResponse;
import com.example.patriotbumiandroid.model.User.RegisterResponse;
import com.example.patriotbumiandroid.model.UserActivity.UserActivityDetail;
import com.example.patriotbumiandroid.model.UserActivity.UserActivityResponse;
import com.google.gson.JsonElement;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface APIService {
    // REGISTER
    @FormUrlEncoded
    @POST("register")
    Call<JsonElement> register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("c_password") String c_password
    );
    // REGISTER

    // LOGIN
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
        @Field("email") String email,
        @Field("password") String password
    );
    // LOGIN

    // LOGOUT
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("logout")
    Call<ResponseBody> logout(
        @Header("Authorization") String token
    );
    // LOGOUT

    // PROFILE
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("details")
    Call<RegisterResponse> getProfile(
        @Header("Authorization") String token
    );
    // PROFILE

    // ARTICLE
    @GET("articles")
    Call<List<Article>> getArticle();

    @GET("articles/{offset}")
    Call<List<Article>> getArticleOffset(
            @Path("offset") String offset
    );
    // ARTICLE

    // ACTIVITY
    @GET("activities/{offset}")
    Call<List<Activity>> getActivityOffset(
            @Path("offset") String offset
    );

    @GET("activities/detail/{id}")
    Call<Activity> getActivityDetail(
        @Path("id") String id
    );
    // ACTIVITY

    // USERACTIVITIES
    @Multipart
    @POST("useractivities/upload/{userid}/{activityid}")
    Call<ResponseBody> uploadUserActivity(
            @Header("Authorization") String token,
            @Path("userid") String userId,
            @Path("activityid") String activityId,
            @Part("count") RequestBody count,
            @Part("status") RequestBody status,
            @Part("mission") RequestBody mission,
            @Part MultipartBody.Part image);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("useractivities/details/{userid}/{activityid}")
    Call<List<UserActivityDetail>> getUserActivityDetail(
            @Header("Authorization") String token,
            @Path("userid") String userId,
            @Path("activityid") String activityId
    );
    // USERACTIVITIES

    // RANK
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("pockets/{id}")
    Call<List<Rank>> getRank(
            @Header("Authorization") String token,
            @Path("id") String id
    );
    // RANK
}
