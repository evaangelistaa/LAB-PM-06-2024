package com.example.tugas7;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingActivity extends AppCompatActivity {
    // untuk menamai file preferensi
    private static final String PREF_NAME = "pref_name";

    // sbg kunci untuk mengakses data spesifik dari SharedPreference, kek bisa menyimpan dan mengambil informasi (mode aplikasi, pengaturan, dll)
    private static final String PREF_MODE_KEY = "pref_mode_key";
    private SharedPreferences sharedPreferences;
    private SwitchMaterial switchMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        boolean isDarkModeEnabled = sharedPreferences.getBoolean(PREF_MODE_KEY, false);
        // utk periksa dan menerapkan mode yang disimpan sebelum memanggil setContentView()
        updateNightMode(isDarkModeEnabled());

        setContentView(R.layout.activity_setting);

        switchMaterial = findViewById(R.id.switchMaterial);

        // untuk mengatur switch berdasarkan preferensi yang disimpan
        switchMaterial.setChecked(isDarkModeEnabled());

        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveDarkModePreference(isChecked);
                updateNightMode(isChecked);
            }
        });
    }

    private void saveDarkModePreference(boolean isDarkModeEnabled) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PREF_MODE_KEY, isDarkModeEnabled);
        editor.apply();
    }

    private boolean isDarkModeEnabled() {
        return sharedPreferences.getBoolean(PREF_MODE_KEY, false);
    }

    private void updateNightMode(boolean isDarkModeEnabled) {
        if (isDarkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}