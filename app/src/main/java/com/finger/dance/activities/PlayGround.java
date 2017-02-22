package com.finger.dance.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.finger.dance.R;
import com.finger.dance.fragments.PlayGroundFragment;
import com.finger.dance.utils.AppConstants;
import com.finger.dance.utils.GeneralUtils;

public class PlayGround extends AppCompatActivity {
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_ground);
        int level = getIntent().getIntExtra(AppConstants.LEVEL,0);
        int totalPointers = GeneralUtils.getValueSharePref(this);
        if(totalPointers<=2){
            GeneralUtils.showDialog("Device Not Supported","Oops! Yous device don't support Multi Pointers.",this,true);
        }
        GeneralUtils.showMsg(totalPointers+"",this);

        PlayGroundFragment playGroundFragment =
                (PlayGroundFragment) getSupportFragmentManager().findFragmentById(R.id.frgmentPlacer);
        if (playGroundFragment == null) {
            playGroundFragment = PlayGroundFragment.newInstance(level);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frgmentPlacer, playGroundFragment)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
