package com.example.pertemuan8;

import android.os.Parcelable;
// digunakan untuk merepresentasikan catatan
public class Notes {

    private int id;
    private String judul;
    private String deskripsi;
    private String createdAt;
    private String updatedAt;
//membuat objek notes
    public Notes(int id, String judul, String deskripsi, String createdAt, String updatedAt) {
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
