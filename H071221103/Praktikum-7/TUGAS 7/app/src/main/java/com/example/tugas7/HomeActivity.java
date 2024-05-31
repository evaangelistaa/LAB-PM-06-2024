package com.example.tugas7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView tv_welcome;
    private Button btn_logout, btn_setting;
    private UserPreference mUserPreference;
    private UserModel userModel;

    // untuk menerima objek userModel kembali dari activity tersebut
    final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new
                    ActivityResultContracts.StartActivityForResult(),
            result -> {
                // kondisi dimana kalau data hasil nya itu tidak null
                // dan kode hasil nya itu sama dengan MainActivity.RESULT_CODE
                if (result.getData() != null && result.getResultCode() ==
                        MainActivity.RESULT_CODE) {
                    // untuk ambil data yang dikirim kembali oleh aktivitas yg ditargetkan yaitu MainActivity
                    userModel = result.getData().getParcelableExtra(MainActivity.EXTRA_RESULT);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tv_welcome = findViewById(R.id.tv_welcome);
        btn_logout = findViewById(R.id.btn_logout);
        btn_setting = findViewById(R.id.btn_setting);

        mUserPreference = new UserPreference(this);
        userModel = mUserPreference.getUser();

        tv_welcome.setText("Welcome, " + userModel.getNim() + "!");

        btn_logout.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
        });

        btn_setting.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
            startActivity(intent);
        });
    }
}