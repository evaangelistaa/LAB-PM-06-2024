package com.example.prak08;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateActivity extends AppCompatActivity {
    private EditText et_title, et_desc;
    private Button btn_update;
    private ImageButton btn_back, btn_delete;
    private DatabaseHelper databaseHelper;
    private int recordId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        databaseHelper = new DatabaseHelper(this);

        et_title = findViewById(R.id.et_title);
        et_desc = findViewById(R.id.et_desc);
        btn_update = findViewById(R.id.btn_update);
        btn_back = findViewById(R.id.btn_back);
        btn_delete = findViewById(R.id.btn_delete);


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("record_id")) {
            recordId = intent.getIntExtra("record_id", -1);
            loadRecordData(recordId);
        }

        btn_back.setOnClickListener(v -> {
            showCancelConfirmationDialog();
        });

        btn_delete.setOnClickListener(v -> {
            databaseHelper.deleteRecords(recordId);
            showDeleteConfirmationDialog();
        });

        btn_update.setOnClickListener(v -> {
            String judul = et_title.getText().toString();
            String deskripsi = et_desc.getText().toString();

            if (!judul.isEmpty() && !deskripsi.isEmpty()) {
                databaseHelper.updateRecord(recordId, judul, deskripsi);
                finish();
            } else {
                if (judul.isEmpty()) {
                    et_title.setError("Please fill this field!");
                }
                if (deskripsi.isEmpty()) {
                    et_desc.setError("Please fill this field!");
                }
            }
        });
    }

    private void loadRecordData(int id) {
        Cursor cursor = databaseHelper.getReadableDatabase().rawQuery("SELECT * FROM " + databaseHelper.getTableName() + " WHERE " + databaseHelper.getColumnId() + " = ?", new String[]{String.valueOf(id)});
        if (cursor != null && cursor.moveToFirst()) {
            et_title.setText(cursor.getString(cursor.getColumnIndexOrThrow(databaseHelper.getColumnJudul())));
            et_desc.setText(cursor.getString(cursor.getColumnIndexOrThrow(databaseHelper.getColumnDeskripsi())));
            cursor.close();
        }
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Note");
        builder.setMessage("Are you sure want to delete this note?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            databaseHelper.deleteRecords(recordId);
            finish();
        });
        builder.setNegativeButton("No", (dialog, which) ->
                dialog.dismiss());
        builder.create().show();
    }

    private void showCancelConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cancel");
        builder.setMessage("Do you want to cancel changes to the form?");

        builder.setPositiveButton("Yes", (dialog, which) -> {
            Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.create().show();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        showCancelConfirmationDialog();
    }
}