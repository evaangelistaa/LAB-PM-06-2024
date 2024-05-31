package com.example.tugas5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView profile = findViewById(R.id.iv_profile);
        TextView name = findViewById(R.id.tv_name);
        TextView username = findViewById(R.id.tv_username);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        profile.setVisibility(View.GONE);
        name.setVisibility(View.GONE);
        username.setVisibility(View.GONE);

        progressBar.setVisibility(View.VISIBLE);

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);

                profile.setVisibility(View.VISIBLE);
                name.setVisibility(View.VISIBLE);
                username.setVisibility(View.VISIBLE);

                Intent intent = getIntent();
                Instagram instagram = intent.getParcelableExtra("instagram");

                profile.setImageResource(instagram.getFotoProfile());
                name.setText(instagram.getName());
                username.setText(instagram.getUsername());
            });
        });
    }
}