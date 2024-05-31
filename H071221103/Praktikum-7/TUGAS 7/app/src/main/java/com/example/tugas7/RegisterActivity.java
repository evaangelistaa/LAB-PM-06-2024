package com.example.tugas7;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView et_nim, et_pass;
    private Button btn_regis;
    private final String FIELD_REQUIRED = "Please fill this field!";
    private UserModel userModel;
    private UserPreference userPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_nim = findViewById(R.id.et_nim);
        et_pass = findViewById(R.id.et_pass);
        btn_regis = findViewById(R.id.btn_regis);
        btn_regis.setOnClickListener(this);

        userPreference = new UserPreference(this);

        // ini untuk atasi error NullPointerExeption kalau userModel nya tidak ada atau null
        userModel = getIntent().getParcelableExtra("UserModel");
        if (userModel == null) {
            userModel = new UserModel();
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_regis) {
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

            // untuk simpan akun pengguna baru dan timpa akun sebelumnya
            userPreference.saveNewUser(nim, pass);

            Toast.makeText(this, "User Berhasil Terdaftar!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}