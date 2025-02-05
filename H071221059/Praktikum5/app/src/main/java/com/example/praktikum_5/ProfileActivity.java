package com.example.praktikum_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView ivProfile = findViewById(R.id.iv_profile);
        TextView tvName = findViewById(R.id.tv_name);
        TextView tvUsername = findViewById(R.id.tv_username);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        ivProfile.setVisibility(View.GONE);
        tvName.setVisibility(View.GONE);
        tvUsername.setVisibility(View.GONE);

        progressBar.setVisibility(View.VISIBLE);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);

                        ivProfile.setVisibility(View.VISIBLE);
                        tvName.setVisibility(View.VISIBLE);
                        tvUsername.setVisibility(View.VISIBLE);

                        Intent intent = getIntent();
                        Sosialmedia sosialmedia = intent.getParcelableExtra("sosialmedia");

                        ivProfile.setImageResource(sosialmedia.getFotoProfile());
                        tvName.setText(sosialmedia.getName());
                        tvUsername.setText(sosialmedia.getUsername());
                    }
                });
            }
        });
    }
}