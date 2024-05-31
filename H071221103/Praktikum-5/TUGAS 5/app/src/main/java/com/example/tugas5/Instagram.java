package com.example.tugas5;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Instagram implements Parcelable {
    private String username;
    private String name;
    private String description;
    private int fotoProfile;
    private int fotoPostingan;
    private Uri selectedImageUri;

    public Instagram(String username, String name, String description, int fotoProfile, int fotoPostingan) {
        this.username = username;
        this.name = name;
        this.description = description;
        this.fotoProfile = fotoProfile;
        this.fotoPostingan = fotoPostingan;
    }

    public Instagram(String Na_Jaemin, String nana, String konten, int user, Uri selectedImageUri) {
        this.username = Na_Jaemin;
        this.name = nana;
        this.description = konten;
        this.fotoProfile = user;
        this.selectedImageUri = selectedImageUri;
    }


    protected Instagram(Parcel in) {
        username = in.readString();
        name = in.readString();
        description = in.readString();
        fotoProfile = in.readInt();
        fotoPostingan = in.readInt();
        selectedImageUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Instagram> CREATOR = new Creator<Instagram>() {
        @Override
        public Instagram createFromParcel(Parcel in) {
            return new Instagram(in);
        }

        @Override
        public Instagram[] newArray(int size) {
            return new Instagram[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFotoProfile() {
        return fotoProfile;
    }

    public void setFotoProfile(int fotoProfile) {
        this.fotoProfile = fotoProfile;
    }

    public int getFotoPostingan() {
        return fotoPostingan;
    }

    public void setFotoPostingan(int fotoPostingan) {
        this.fotoPostingan = fotoPostingan;
    }

    public Uri getSelectedImageUri() {
        return selectedImageUri;
    }

    public void setSelectedImageUri(Uri selectedImageUri) {
        this.selectedImageUri = selectedImageUri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(fotoProfile);
        dest.writeInt(fotoPostingan);
        dest.writeParcelable(selectedImageUri, flags);
    }
}
