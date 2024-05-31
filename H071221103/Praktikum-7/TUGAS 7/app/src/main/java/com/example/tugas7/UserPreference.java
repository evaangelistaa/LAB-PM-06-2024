package com.example.tugas7;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class UserPreference {
    private static final String PREFS_NAME = "user_pref";
    private static final String NIM = "nim";
    private static final String PASSWORD = "pass";
    private final SharedPreferences preferences;

    // untuk menyimpan preferensi secara private
    UserPreference(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setUser(UserModel value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NIM, value.nim);
        editor.putString(PASSWORD, value.pass);
        editor.apply();
    }

     UserModel getUser() {
        UserModel model = new UserModel();
        model.setNim(preferences.getString(NIM, ""));
        model.setPass(preferences.getString(PASSWORD, ""));
        return model;
    }

    // Method untuk mendapatkan data NIM pengguna
    public String getNim() {
        return preferences.getString(NIM, "");
    }

    // Method untuk mendapatkan data password pengguna
    public String getPass() {
        return preferences.getString(PASSWORD, "");
    }

    // Method untuk mengecek apakah user sudah terdaftar atau belum
    public boolean isUserRegistered(String nim, String pass) {
        String savedNim = getNim();
        String savedPass = getPass();
        // untuk cek kalo NIM dan password yang disimpan itu cocok dengan NIM dan password yang diberikan
        return !TextUtils.isEmpty(savedNim) && savedNim.equals(nim) && !TextUtils.isEmpty(savedPass) && savedPass.equals(pass);
    }

    // Method untuk menyimpan akun baru dan menimpa akun sebelumnya
    public void saveNewUser(String nim, String pass) {
        // untuk modifikasi data dalam preferences nya
        SharedPreferences.Editor editor = preferences.edit();
        // untuk simpan data kalo sudah di edit dalam preference nya
        editor.putString(NIM, nim);
        editor.putString(PASSWORD, pass);
        editor.apply();
    }
}
