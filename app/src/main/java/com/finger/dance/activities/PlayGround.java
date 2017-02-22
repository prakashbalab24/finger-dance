package com.finger.dance.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.finger.dance.R;
import com.finger.dance.fragments.PlayGroundFragment;
import com.finger.dance.utils.AppConstants;
import com.finger.dance.utils.GeneralUtils;

public class PlayGround extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_ground);
        int totalPointers = GeneralUtils.getValueSharePref(this);
        if(totalPointers<=2){
            GeneralUtils.showDialog("Device Not Supported","Oops! Yous device don't support Multi Pointers.",this,true);
        }
        GeneralUtils.showMsg(totalPointers+"",this);

        PlayGroundFragment playGroundFragment =
                (PlayGroundFragment) getSupportFragmentManager().findFragmentById(R.id.frgmentPlacer);
        if (playGroundFragment == null) {
            playGroundFragment = PlayGroundFragment.newInstance(totalPointers);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frgmentPlacer, playGroundFragment)
                    .commit();
        }
    }
}
