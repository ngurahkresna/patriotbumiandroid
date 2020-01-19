package com.example.patriotbumiandroid.activity.ActivityDetail;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.activity.Authenticate.LoginActivity;
import com.example.patriotbumiandroid.adapter.RecyclerView.MissionPhotoRecyclerViewAdapter;
import com.example.patriotbumiandroid.data.APIService;
import com.example.patriotbumiandroid.data.APIUtils;
import com.example.patriotbumiandroid.model.Activity.Activity;
import com.example.patriotbumiandroid.model.UserActivity.UserActivityDetail;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ActivityDetailActivity extends AppCompatActivity {

    private APIService mAPIService;
    private ImageView back;
    private RecyclerView missionPhotoRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MissionPhotoRecyclerViewAdapter missionPhotoRecyclerViewAdapter;
    private Button uploadButton;
    private ProgressBar progressBar, uploadedProgressBar;
    private TextView missionTitle, missionXP, missionCoin, missionPurpose, missionProgress, missionCount;
    private ImageView missionImage;
    private Activity activity;
    private List<UserActivityDetail> userActivityDetails;
    private SharedPreferences sharedPreferences;
    private String token, userId, username, countNumber;
    static final int PICK_IMAGE = 100;
    private double progressNumber, done;
    private SwipeRefreshLayout activitySwipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        back = findViewById(R.id.back);
        missionProgress = findViewById(R.id.missionProgress);
        missionTitle = findViewById(R.id.missionTitle);
        missionXP = findViewById(R.id.missionXP);
        missionCoin = findViewById(R.id.missionCoin);
        missionPurpose = findViewById(R.id.missionPurpose);
        missionImage = findViewById(R.id.missionImage);
        missionCount = findViewById(R.id.missionCount);
        progressBar = findViewById(R.id.determinateBar);
        missionPhotoRecyclerView = findViewById(R.id.missionPhotoRecyclerView);
        uploadButton = findViewById(R.id.missionButton);
        uploadedProgressBar = findViewById(R.id.uploadedProgressBar);
        activitySwipeRefresh = findViewById(R.id.activitySwipeRefresh);

        mAPIService = APIUtils.getAPIService();
        activity = null;

        sharedPreferences = this.getSharedPreferences("Authenticate", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN", "");
        userId = sharedPreferences.getString("USERID", "");
        username = sharedPreferences.getString("USERNAME", "");

        initActivityData();

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!token.equals("") && !token.isEmpty()) {
                    selectImageSource();
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        });

        activitySwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initActivityData();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initActivityData() {
        Intent intent = getIntent();
        Call<Activity> listCall = mAPIService.getActivityDetail(intent.getStringExtra("id"));
        listCall.enqueue(new Callback<Activity>() {
            @Override
            public void onResponse(Call<Activity> call, Response<Activity> response) {
                if (response.code() == 200) {
                    if (activity == null) {
                        activity = response.body();
                    }
                    initActivityDetail(activity);
                    activitySwipeRefresh.setRefreshing(false);
                } else {
                    Log.d("activityDetail", response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<Activity> call, Throwable t) {

            }
        });
    }

    private void initActivityDetail(Activity body) {
        missionTitle.setText(body.getTitle());
        missionXP.setText(body.getXp());
        missionCoin.setText(body.getGold());
        missionPurpose.setText(body.getDescription());
        try {
            Picasso
                    .get()
                    .load("https://patriot-bumi.artcart.id" + body.getImage())
                    .into(missionImage, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess()
                        {
                            // progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d("picasso failed", e.toString());
                            // progressBar.setVisibility(View.GONE);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        initProgressData();
    }

    private void initProgressData() {
        Call<List<UserActivityDetail>> listCall = mAPIService.getUserActivityDetail("Bearer " + token, userId, activity.getId());
        listCall.enqueue(new Callback<List<UserActivityDetail>>() {
            @Override
            public void onResponse(Call<List<UserActivityDetail>> call, Response<List<UserActivityDetail>> response) {
                if (response.code() == 200) {
                    userActivityDetails = response.body();
                    initUserProgress(userActivityDetails);

                    int lastIndex = userActivityDetails.size();
                    if (lastIndex > 0) {
                        String progress = userActivityDetails.get(lastIndex - 1).getCount();
                        progressNumber = Double.valueOf(progress);
                        if (progressNumber == 4) {
                            missionProgress.setText("1");
                            done = 25.0;
                        } else if(progressNumber == 3) {
                            missionProgress.setText("2");
                            done = 50.0;
                        } else if(progressNumber == 2) {
                            missionProgress.setText("3");
                            done = 75.0;
                        } else if(progressNumber == 1) {
                            missionProgress.setText("4");
                            done = 100.0;
                            uploadButton.setEnabled(false);
                            uploadButton.setBackground(getResources().getDrawable(R.drawable.button_stroke_background));
                            uploadButton.setText("Misi Selesai");
                            uploadButton.setTextColor(getResources().getColor(R.color.colorPrimary));
                        } else {
                            missionProgress.setText("0");
                            done = 0.0;
                        }

//                        missionCount.setText("4");
                        progressBar.setProgress((int) done);
                    }
                } else {
                    Log.d("userProgress", response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<List<UserActivityDetail>> call, Throwable t) {
                Log.d("userProgress", t.toString());
            }
        });
    }

    private void initUserProgress(List<UserActivityDetail> userActivityDetails) {
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        missionPhotoRecyclerViewAdapter = new MissionPhotoRecyclerViewAdapter(userActivityDetails);
        missionPhotoRecyclerView.setLayoutManager(linearLayoutManager);
        missionPhotoRecyclerView.setHasFixedSize(true);
        missionPhotoRecyclerView.setAdapter(missionPhotoRecyclerViewAdapter);
    }

    private void selectImageSource() {
        Intent intentPick = new Intent(Intent.ACTION_PICK);
        intentPick.setType("image/*");
        startActivityForResult(intentPick, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == PICK_IMAGE) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedBitmap = BitmapFactory.decodeStream(imageStream);

                    try {
                        createFile(selectedBitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            Toast.makeText(getApplicationContext(), "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    private void createFile(Bitmap selectedBitmap) throws IOException {
        File file = new File(getApplicationContext().getCacheDir(), "image");
        file.createNewFile();

        Bitmap bitmap = selectedBitmap;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
        fileOutputStream.close();

        RequestBody requestBody = RequestBody.create(MediaType.parse("/image/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), requestBody);

        uploadActivity(part);
    }

    private void uploadActivity(MultipartBody.Part part) {
        uploadedProgressBar.setVisibility(View.VISIBLE);

        String missionState;

        if (progressNumber == 4) {
            countNumber = "3";
        } else if (progressNumber == 3) {
            countNumber = "2";
        } else if (progressNumber == 2) {
            countNumber = "1";
        } else if (progressNumber == 0) {
            countNumber = "4";
        }

        if (countNumber == "1") {
            missionState = "Completed";
        } else {
            missionState = "On Going";
        }

        RequestBody count = RequestBody.create(MediaType.parse("text/plain"), countNumber);
        RequestBody status = RequestBody.create(MediaType.parse("text/plain"), "Not Confirmed");
        RequestBody mission = RequestBody.create(MediaType.parse("text/plain"), missionState);
        Call<ResponseBody> responseBodyCall = mAPIService.uploadUserActivity("Bearer " + token, userId, activity.getId(), count, status, mission, part);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 201) {
                    Toast.makeText(getApplicationContext(), "Upload Successful", Toast.LENGTH_SHORT).show();
                    uploadedProgressBar.setVisibility(View.GONE);
                    initActivityData();
                } else {
                    uploadedProgressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), response.code() + "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                uploadedProgressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Upload Failed", Toast.LENGTH_SHORT).show();
                Log.d("uploadActivity", t.toString());
            }
        });
    }
}
