package com.finger.dance.helper;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by prakash-bala on 21/2/17.
 */

public class GeneralHelper {
    private static List<Integer> a = new ArrayList<Integer>();

    public static int randInt(int min, int max) {
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static int randIntUnique(int min, int max) {
        Random rand = new Random();


        if (a.size()>=20){
            return -1;
        }
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        if(!a.contains(randomNum)){
            a.add(randomNum);
            return randomNum;
        }
         return randIntUnique(min,max);

    }
}
