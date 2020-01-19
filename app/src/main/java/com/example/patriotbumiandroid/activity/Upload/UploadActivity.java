package com.example.patriotbumiandroid.activity.Upload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.patriotbumiandroid.R;

public class UploadActivity extends AppCompatActivity {

    private ImageView selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        selectedImage = findViewById(R.id.selectedImage);

        Intent intent = getIntent();
        Bitmap bitmap = intent.getParcelableExtra("bitmap");
        selectedImage.setImageBitmap(bitmap);
    }
}
