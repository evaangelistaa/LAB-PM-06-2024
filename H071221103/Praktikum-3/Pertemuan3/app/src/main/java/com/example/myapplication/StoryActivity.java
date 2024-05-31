package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class StoryActivity extends AppCompatActivity {

    private ImageView iv_profile;
    private ImageView iv_story;
    private TextView tv_nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intent = getIntent();

        Instagram instagram = intent.getParcelableExtra("instagram");

        iv_profile = findViewById(R.id.iv_profile2);
        iv_story = findViewById(R.id.imageStory);
        tv_nama = findViewById(R.id.tv_profile2);

        iv_profile.setImageResource(instagram.getFotoProfile());
        iv_story.setImageResource(instagram.getFotoStory());
        tv_nama.setText(instagram.getUsername());

        tv_nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoryActivity.this, ProfileActivity.class);
                intent.putExtra("instagram", instagram);
                startActivity(intent);
            }
        });
    }
}