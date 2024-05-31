package com.example.prak08;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btn_add;
    private RecyclerView list_notes;
    private TextView no_data;
    private SearchView searchView;
    private DatabaseHelper databaseHelper;
    private List<Note> notes;
    private NoteAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        list_notes = findViewById(R.id.list_notes);
        no_data = findViewById(R.id.no_data);
        searchView = findViewById(R.id.search);

        databaseHelper = new DatabaseHelper(this);

        notes = new ArrayList<>();
        notesAdapter = new NoteAdapter(this, notes);
        list_notes.setAdapter(notesAdapter);
        list_notes.setLayoutManager(new LinearLayoutManager(this));

        loadData("");

        btn_add.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateActivity.class);
            startActivity(intent);
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                loadData(newText);
                return true;
            }
        });
    }


    // untuk data catatannya dari db
    private void loadData(String query) {
        notes.clear();
        Cursor cursor;
        if (query.isEmpty()) {
            cursor = databaseHelper.getAllRecords();
        } else {
            cursor = databaseHelper.searchByTitle(query);
        }

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(databaseHelper.getColumnId()));
                String judul = cursor.getString(cursor.getColumnIndexOrThrow(databaseHelper.getColumnJudul()));
                String deskripsi = cursor.getString(cursor.getColumnIndexOrThrow(databaseHelper.getColumnDeskripsi()));
                String createdAt = cursor.getString(cursor.getColumnIndexOrThrow(databaseHelper.getColumnCreatedAt()));
                String updatedAt = cursor.getString(cursor.getColumnIndexOrThrow(databaseHelper.getColumnUpdatedAt()));
                notes.add(new Note(id, judul, deskripsi, createdAt, updatedAt));
            } while (cursor.moveToNext());
            cursor.close();
        }

        if (notes.isEmpty()) {
            no_data.setVisibility(View.VISIBLE);
        } else {
            no_data.setVisibility(View.GONE);
        }

        notesAdapter.notifyDataSetChanged();
    }

    @Override
    // untuk dimuat ulang data catatan
    protected void onResume() {
        super.onResume();
        loadData("");
    }
}