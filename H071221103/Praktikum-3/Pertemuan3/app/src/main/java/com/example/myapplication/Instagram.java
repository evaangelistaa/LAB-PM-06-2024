package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Instagram implements Parcelable {

    private String username;
    private String deskripsi;
    private Integer fotoProfile;
    private Integer fotoPostingan;
    private Integer fotoStory;
    private String followers;
    private String following;

    public Instagram(String username, String deskripsi, Integer fotoProfile, Integer fotoPostingan, Integer fotoStory, String followers, String following) {
        this.username = username;
        this.deskripsi = deskripsi;
        this.fotoProfile = fotoProfile;
        this.fotoPostingan = fotoPostingan;
        this.fotoStory = fotoStory;
        this.followers = followers;
        this.following = following;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Integer getFotoProfile() {
        return fotoProfile;
    }

    public void setFotoProfile(Integer fotoProfile) {
        this.fotoProfile = fotoProfile;
    }

    public Integer getFotoPostingan() {
        return fotoPostingan;
    }

    public void setFotoPostingan(Integer fotoPostingan) {
        this.fotoPostingan = fotoPostingan;
    }

    public Integer getFotoStory() {
        return fotoStory;
    }

    public void setFotoStory(Integer fotoStory) {
        this.fotoStory = fotoStory;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    protected Instagram(Parcel in) {
        username = in.readString();
        deskripsi = in.readString();
        if (in.readByte() == 0) {
            fotoProfile = null;
        } else {
            fotoProfile = in.readInt();
        }
        if (in.readByte() == 0) {
            fotoPostingan = null;
        } else {
            fotoPostingan = in.readInt();
        }
        if (in.readByte() == 0) {
             fotoStory = null;
        } else {
            fotoStory = in.readInt();
        }
       followers = in.readString();
        following = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(deskripsi);
        if (fotoProfile == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(fotoProfile);
        }
        if (fotoPostingan == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(fotoPostingan);
        }
        if (fotoStory == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(fotoStory);
        }
        dest.writeString(followers);
        dest.writeString(following);
    }
}
