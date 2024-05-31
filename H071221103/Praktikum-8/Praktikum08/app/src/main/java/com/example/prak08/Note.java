package com.example.prak08;


import android.os.Parcelable;

public class Note {

    private int id;
    private String title;
    private String desc;
    private String createdAt;
    private String updatedAt;

    public Note(int id, String title, String desc, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.desc = desc;
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
        return title;
    }

    public void setJudul(String judul) {
        this.title = judul;
    }

    public String getDeskripsi() {
        return desc;
    }

    public void setDeskripsi(String deskripsi) {
        this.desc = deskripsi;
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