package com.finger.dance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.finger.dance.activities.PlayGround;
import com.finger.dance.utils.AppConstants;

public class SplashScreen extends AppCompatActivity {
    int totalPointers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("FingerDance", 0);
        totalPointers= pref.getInt(AppConstants.KEY,0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                try {
                    if(totalPointers==0){
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    else {
                        Intent intent = new Intent(SplashScreen.this, PlayGround.class);
                        startActivity(intent);
                        finish();
                    }

                } catch (Exception e) {
                }
            }
        },2500);
    }
}
