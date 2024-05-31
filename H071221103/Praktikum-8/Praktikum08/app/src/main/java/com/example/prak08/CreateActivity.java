package com.example.prak08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


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

public class CreateActivity extends AppCompatActivity {

    ImageButton btn_back;
    Button btn_submit;
    TextInputLayout input_title;
    TextInputLayout input_desc;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        databaseHelper = new DatabaseHelper(this);

        btn_submit = findViewById(R.id.btn_add);
        input_title = findViewById(R.id.titleInput);
        input_desc = findViewById(R.id.descInput);

        btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(v ->
                showExitConfirmationDialog());

        btn_submit.setOnClickListener(v -> {
            String title = input_title.getEditText().getText().toString().trim();
            String desc = input_desc.getEditText().getText().toString().trim();

            if (title.isEmpty() ) {
                input_title.setError("Please fill this field!");
            } else {
                databaseHelper.insertData(title, desc);
                Toast.makeText(CreateActivity.this, "Added successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Cancel")
                .setMessage("Do you want to cancel changes to the form?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    dialog.dismiss();
                    super.onBackPressed();
                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        showExitConfirmationDialog();
    }
}