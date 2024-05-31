package com.example.tugas5;

import java.util.ArrayList;

public class DataSource {

    public static ArrayList<Instagram> instagrams = generateData();

    private static ArrayList<Instagram> generateData() {
        ArrayList<Instagram> instagrams1 = new ArrayList<>();
        instagrams1.add(new Instagram("Taeyong", "tyongs", "just some photoshoot.", R.drawable.taeyong, R.drawable.taeyong_post));
        instagrams1.add(new Instagram("Mark", "markzen", "Do u want me to play some music?", R.drawable.mark, R.drawable.mark_post));
        instagrams1.add(new Instagram("Haechan", "sunflower", "230717 'ISTJ'", R.drawable.haechan, R.drawable.haechan_post));
        instagrams1.add(new Instagram("Jaehyun", "peachzen", "gotcha.", R.drawable.jaehyun, R.drawable.jaehyun_post));
        instagrams1.add(new Instagram("Jisung", "asteroid", "Last One!", R.drawable.jisung, R.drawable.jisung_post));

        return instagrams1;
    }
}
