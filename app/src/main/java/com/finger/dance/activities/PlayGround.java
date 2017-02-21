package com.finger.dance.activities;

import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.finger.dance.R;
import com.finger.dance.fragments.PlayGroundFragment;

public class PlayGround extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_ground);
        PlayGroundFragment playGroundFragment =
                (PlayGroundFragment) getSupportFragmentManager().findFragmentById(R.id.frgmentPlacer);
        if (playGroundFragment == null) {
            playGroundFragment = PlayGroundFragment.newInstance(2);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frgmentPlacer, playGroundFragment)
                    .commit();
        }
    }
}
