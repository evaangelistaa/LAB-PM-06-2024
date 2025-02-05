package com.example.praktikum7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.textfield.TextInputEditText;
// mngatur tmpilan activity xml mnggunakan setcontent dan inisialisasi elemen ui
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextInputEditText nimInput = findViewById(R.id.nim);
        TextInputEditText passInput = findViewById(R.id.password);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView tvReg = findViewById(R.id.register);
        LinearLayout linearLayout = findViewById(R.id.main);
// memeriksa mode mlm dari shared dan mengaktifkan mode mlm jika diprlukan
//latar blkng layout linear diubah sesuai dngn mode mlm atau biasa
        SharedPreferences sharedPreferences1 = getSharedPreferences("MODE", MODE_PRIVATE);
        boolean nightMode = sharedPreferences1.getBoolean("darkMode", false);
        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            linearLayout.setBackgroundResource(R.drawable.bgdark);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            linearLayout.setBackgroundResource(R.drawable.bgwhite);
        }

        SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
        String NIM = sharedPreferences.getString("nim", "");
        String PASSWORD = sharedPreferences.getString("password", "");

        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
// ktika  tombol login di tekan dia akan mengambil nim dan pass
        btnLogin.setOnClickListener(v -> {
            String nim = nimInput.getText().toString();
            String password = passInput.getText().toString();
//apakah kedua dia isi klau tdk dia menampilkan pesan kesalahan
            if (nim.isEmpty()) {
                nimInput.setError("Please fill this field");
            } else if (password.isEmpty()) {
                passInput.setError("Please fill this field");
//jika telah diisi,aka di priksa apakah cocok dngn data yg trsimpan di sharepre
//jika cocok status login disetel jdi true dan di alihkan ke hlm homeact
            } else if (nim.equals(NIM) && password.equals(PASSWORD)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.apply();

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
//jiKa tdk cck kode akan mnplkn pesan kslhn
            } else {
                Toast.makeText(MainActivity.this, "NIM atau Password salah.", Toast.LENGTH_SHORT).show();
            }

        });
//ktika teks pndftr di tekan,apk akan buka activityregis,ini mnyuruh pengguna jika blm memliki akunm
        tvReg.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

    }
}