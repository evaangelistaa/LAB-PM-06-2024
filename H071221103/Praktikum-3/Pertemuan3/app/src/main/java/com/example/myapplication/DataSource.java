package com.example.myapplication;

import java.util.ArrayList;

public class DataSource {

    public static ArrayList<Instagram> instagrams = generateDummyUniversities();

    private static ArrayList<Instagram> generateDummyUniversities() {
        ArrayList<Instagram> instagrams1 = new ArrayList<>();
        instagrams1.add(new Instagram("Umji Viviz", "Menunggu Buka Puasa", R.drawable.umji, R.drawable.umji, R.drawable.umji,"1.5 JT", "5"));
        instagrams1.add(new Instagram("Kim So Hyun", "Pamer Jam Baru", R.drawable.kimsohyun, R.drawable.kimsohyun, R.drawable.kimsohyun, "13.5 JT", "6452"));
        instagrams1.add(new Instagram("Shin Seul-ki", "Sarjana nih bos", R.drawable.shin, R.drawable.shin, R.drawable.shin, "1.3 JT", "172"));
        instagrams1.add(new Instagram("Go Yoon Jung", "Selesai juga 10km Night Run with Aan", R.drawable.gyj, R.drawable.gyj, R.drawable.gyj, "6.9 JT", "1"));
        instagrams1.add(new Instagram("Mina Twice", "Ngabuburit sambil war Takjil", R.drawable.mina, R.drawable.mina, R.drawable.mina, "9.9 JT", "5"));
        instagrams1.add(new Instagram("Han So Hee", "Lagi pap miror nih", R.drawable.hansohe, R.drawable.hansohe, R.drawable.hansohe, "17.6 JT", "0"));
        instagrams1.add(new Instagram("Winter Aespa", "Ngapain ya Siang ini?", R.drawable.winter, R.drawable.winter, R.drawable.winter, "8.6 JT", "4"));
        instagrams1.add(new Instagram("Park Eunbi", "Lagi konser di Makassar", R.drawable.eunbi, R.drawable.eunbi, R.drawable.eunbi, "3.4 JT", "0"));
        instagrams1.add(new Instagram("Nmixx", "Menghadiri Pernikahan", R.drawable.nmixx, R.drawable.nmixx, R.drawable.nmixx, "5.4 JT", "3"));
        instagrams1.add(new Instagram("Kim Tae Ri", "Syuting drama 2521", R.drawable.kimtaeri, R.drawable.kimsohyun, R.drawable.kimtaeri, "2.4 JT", "1"));
        return instagrams1;
    }
}
