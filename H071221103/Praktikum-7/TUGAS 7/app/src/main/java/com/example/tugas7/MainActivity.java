package com.example.tugas7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView et_nim, et_pass;
    private Button btn_login, btn_regis;
    public final static String EXTRA_RESULT = "extra_result";
    public static final int RESULT_CODE = 101;
    private final String FIELD_REQUIRED = "Please fill this field!";
    private UserModel userModel;
    private UserPreference userPreference;
    private static final String PREF_NAME = "pref_name";
    SharedPreferences sharedPreferences;
    private static final String PREF_MODE_KEY = "pref_mode_key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        boolean isDarkModeEnabled = sharedPreferences.getBoolean(PREF_MODE_KEY, false);
            if (isDarkModeEnabled) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

        setContentView(R.layout.activity_main);

        et_nim = findViewById(R.id.et_nim);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_regis = findViewById(R.id.btn_regis);
        btn_login.setOnClickListener(this);

        userPreference = new UserPreference(this);

        userModel = getIntent().getParcelableExtra("UserModel");
        // ini untuk atasi error NullPointerExeption kalau userModel nya tidak ada atau null
        if (userModel == null) {
            userModel = new UserModel();
        }

        btn_regis.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login) {
            String nim = et_nim.getText().toString().trim();
            String pass = et_pass.getText().toString();

            // kondisi kalo edit text nim dan password kosong
            if (TextUtils.isEmpty(nim)) {
                et_nim.setError(FIELD_REQUIRED);
                return;
            }
            if (TextUtils.isEmpty(pass)) {
                et_pass.setError(FIELD_REQUIRED);
                return;
            }

            // cek apakah nim dan pass nya sudah terdaftar atau belum
            if (userPreference.isUserRegistered(nim, pass)) {
                // klo terdaftar
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("NIM", nim);
                startActivity(intent);
            } else {
                // klo belum terdaftar
                Toast.makeText(this, "NIM atau Password Salah!", Toast.LENGTH_SHORT).show();
                et_nim.setText("");
                et_pass.setText("");
            }

            // untuk mengirim hasil ke HomeActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_RESULT, userModel);
            setResult(RESULT_CODE, resultIntent);
        }
    }
}