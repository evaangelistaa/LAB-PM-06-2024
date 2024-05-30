package com.example.pertemuan8;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class AddActivity extends AppCompatActivity {

//Deklarasi variabel instance untuk elemen UI dan konfigurasi database
    ImageButton ib_back;
    Button btn_submit;
    TextInputLayout textInputLayout_judul;
    TextInputLayout textInputLayout_deskripsi;
    DBConfig dbConfig;

// untuk menyesuaikan padding berdasarkan sistem insets (misalnya, status bar dan navigation bar)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//mengakses dan mengelola basis data dalam konteks aplikasi tersebut
        dbConfig = new DBConfig(this);
//Menghubungkan variabel instance dengan elemen UI dalam tata letak XML
        btn_submit = findViewById(R.id.btn_submit);
        textInputLayout_judul = findViewById(R.id.titleInput);
        textInputLayout_deskripsi = findViewById(R.id.descInput);

        ib_back = findViewById(R.id.btn_back);
//klik untuk tombol kembali yang menampilkan dialog konfirmasi keluar
        ib_back.setOnClickListener(v ->
                showExitConfirmationDialog());
//Mengatur pendengar klik untuk tombol submit yang mengambil teks input dari pengguna
        btn_submit.setOnClickListener(v -> {
            String judul = textInputLayout_judul.getEditText().getText().toString().trim();
            String deskripsi = textInputLayout_deskripsi.getEditText().getText().toString().trim();
//Memeriksa apakah judul kosong. Jika iya, tampilkan pesan error.
// Jika tidak, masukkan data ke database dan tampilkan pesan toast, lalu tutup aktivitas
            if (judul.isEmpty() ) {
                textInputLayout_judul.setError("Please enter your Judul");
            } else {
                dbConfig.insertData(judul, deskripsi);
                Toast.makeText(AddActivity.this, "Note added successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        showExitConfirmationDialog();
    }
//Metode untuk menampilkan dialog konfirmasi saat pengguna ingin keluar tanpa menyimpan perubahan
// Jika pengguna menekan "Yes", aktivitas akan ditutup
    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Cancel")
                .setMessage("Apakah anda ingin membatalkan penambahan pada form?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AddActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }
//untuk menampilkan dialog konfirmasi keluar saat tombol kembali ditekan

}
