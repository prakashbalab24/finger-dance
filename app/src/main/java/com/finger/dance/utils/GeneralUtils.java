package com.finger.dance.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.widget.LinearLayout;

import com.finger.dance.R;
import com.finger.dance.activities.MainActivity;
import com.finger.dance.activities.SplashScreen;
import com.finger.dance.adapters.TileAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by prakash-bala on 21/2/17.
 */

public class GeneralUtils {
    public static List<Integer> a ;

    /**Method for generating random integer**/
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    /**Method for generating random UNIQUE integer**/
    public static int randIntUnique(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        if (a.size() > max) {
            return -1;
        }
        if (!a.contains(randomNum)) {
            a.add(randomNum);
            return randomNum;
        }
        return -1;

    }
    /**Method for Showing msg**/
    public static void showMsg(String msg, Context mContext) {
//        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    /**Method for Showing Dialog**/
    public static void showDialog(String title, String msg, final Context context, boolean showNegative){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setCancelable(false);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });
        if(showNegative==true) {
            builder.setNegativeButton("Calibrate again", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }

            });
        }
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**Method for Getting Shared pref **/
    public static int getValueSharePref(Context context){
        SharedPreferences pref = context.getSharedPreferences(AppConstants.SP_NAME, 0);
        int totalPointers = pref.getInt(AppConstants.KEY,0);
        return totalPointers;
    }

    /**Method for Setting Shared pref **/
    public static void setValueSharePref(Context context,int totalPointers){
        SharedPreferences pref = context.getSharedPreferences(AppConstants.SP_NAME, 0);
        SharedPreferences.Editor editor= pref.edit();;
        if(pref.contains(AppConstants.KEY)) {
            editor.clear();
        }
        editor.putInt(AppConstants.KEY, totalPointers);
        editor.commit();
    }

    public static void setCardDesign (int level, TileAdapter.MyViewHolder holder,Context mContext) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.tileView.getLayoutParams();
        switch (level){
            case 3:{
                params.height = mContext.getResources().getDimensionPixelSize(R.dimen.cardsizebig);
                params.width = mContext.getResources().getDimensionPixelSize(R.dimen.cardsizebig);
                holder.tileView.setLayoutParams(params);
                break;
            }

            case 4:{
                params.height = mContext.getResources().getDimensionPixelSize(R.dimen.cardsizemedium);
                params.width = mContext.getResources().getDimensionPixelSize(R.dimen.cardsizemedium);
                holder.tileView.setLayoutParams(params);
                break;
            }
            case 5:{
                params.height = mContext.getResources().getDimensionPixelSize(R.dimen.cardsizesmall);
                params.width = mContext.getResources().getDimensionPixelSize(R.dimen.cardsizesmall);
                holder.tileView.setLayoutParams(params);
                break;
            }
            default:{
                params.height = mContext.getResources().getDimensionPixelSize(R.dimen.cardsizesmall);
                params.width = mContext.getResources().getDimensionPixelSize(R.dimen.cardsizesmall);
                holder.tileView.setLayoutParams(params);
                break;

            }
        }

    }



}
