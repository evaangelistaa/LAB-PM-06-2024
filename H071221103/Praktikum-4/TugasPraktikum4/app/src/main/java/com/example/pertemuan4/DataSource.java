package com.example.pertemuan4;

import java.util.ArrayList;

public class DataSource {

    public static ArrayList<Instagram> instagrams = generateDummyInstagrams();

    private static ArrayList<Instagram> generateDummyInstagrams() {
        ArrayList<Instagram> instagrams1 = new ArrayList<>();
        instagrams1.add(new Instagram("_jeongjaehyun","Jaehyun"
                ,"Calon suaminya Rose"
                ,R.drawable.jaehyun,R.drawable.jaehyun));

        instagrams1.add(new Instagram("beomsoo", "Beomsoo"
                ,"Mukanya webtoonable"
                ,R.drawable.beomsoo,R.drawable.beomsoo));

        instagrams1.add(new Instagram("na.jaemin0813", "Na Jaemin"
                ,"Aibnya dimana-mana"
                ,R.drawable.jaemin, R.drawable.jaemin));

        instagrams1.add(new Instagram("leejen_o_423","Lee jeno"
                ,"Orang yang paling tidak bisa marah"
                ,R.drawable.jeno,R.drawable.jeno));

        instagrams1.add(new Instagram("the_and.y", "Jisung"
                ,"Maknaenya dreamis"
                ,R.drawable.jisung,R.drawable.jisung));

        instagrams1.add(new Instagram("soohyun_k216","Kim soo hyun"
                ,"Please, jangan sad ending"
                ,R.drawable.kimsoohyun, R.drawable.kimsoohyun));

        instagrams1.add(new Instagram("kyung_jun","Kyung jun"
                , "Emang boleh seganteng ituu"
                ,R.drawable.kyungjun,R.drawable.kyungjun));

        instagrams1.add(new Instagram("lee_mujin","Leemujin"
                ,"Saya suka semua lagu-lagumu"
                ,R.drawable.leemujin, R.drawable.leemujin));

        instagrams1.add(new Instagram("lucas_xx444", "lucas"
                ,"Akhirnya ini orang comeback juga"
                ,R.drawable.lucas, R.drawable.lucas));

        instagrams1.add(new Instagram("onyourm_ark", "Marklee"
                ,"Canadian Rapper"
                ,R.drawable.marklee, R.drawable.marklee));
        return instagrams1;
    }
}
