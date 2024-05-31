package com.example.tugaspraktikum2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    Button btnsubmit;
    ImageView img;
    EditText nama;
    EditText usn;

    String editNama;
    String editUsn;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        untuk mengambil id
        btnsubmit = findViewById(R.id.button);
        img = findViewById(R.id.imageView);
        nama = findViewById(R.id.TextName);
        usn = findViewById(R.id.TextName2);

//        untuk pilih gambar
        img.setOnClickListener(view -> {
            Intent open = new Intent(Intent.ACTION_PICK);
            open.setType("image/*");
            launcherIntentGallery.launch(open);
        });

//        untuk button submmit
        btnsubmit.setOnClickListener(view -> {
            editNama = nama.getText().toString().trim();
            editUsn = usn.getText().toString().trim();

            if (editNama.isEmpty() || editUsn.isEmpty()) {
                Toast.makeText(this, "Isi semua field terlebih dahulu", Toast.LENGTH_SHORT).show();
            }  else if (imageUri == null) {
                Toast.makeText(this, "Pilih gambar terlebih dahulu", Toast.LENGTH_SHORT).show();
            } else {
                Intent activity2 = new Intent(MainActivity.this, MainActivity2.class);
                activity2.putExtra("editNama", editNama);
                activity2.putExtra("editUsn", editUsn);
                activity2.putExtra("imageUri", imageUri.toString());
                startActivity(activity2);
            }
        });
    }

    ActivityResultLauncher<Intent> launcherIntentGallery = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        imageUri = data.getData();
                        img.setImageURI(imageUri);
                    }
                }
            });


}
