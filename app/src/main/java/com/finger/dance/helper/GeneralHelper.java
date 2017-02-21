package com.finger.dance.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.finger.dance.utils.MultiTouchView;

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
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static int randIntUnique(int min, int max) {
        Random rand = new Random();
        if (a.size() >= 20) {
            return -1;
        }
        int randomNum = rand.nextInt((max - min) + 1) + min;
        if (!a.contains(randomNum)) {
            a.add(randomNum);
            return randomNum;
        }
        return randIntUnique(min, max);

    }

    public static void showMsg(String msg, Context mContext) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }


    public static void showDialog(String msg,Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Total Pointers");
        builder.setMessage(msg);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });
        AlertDialog alert = builder.create();
        alert.show();
    }


}
