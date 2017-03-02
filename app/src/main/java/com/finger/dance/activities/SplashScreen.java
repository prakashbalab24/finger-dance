package com.finger.dance.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.finger.dance.R;
import com.finger.dance.component.ui.RadialGradientView;
import com.finger.dance.utils.AppConstants;
import com.finger.dance.utils.ColorGradient;
import com.finger.dance.utils.GeneralUtils;

public class SplashScreen extends AppCompatActivity {
    int totalPointers;
    private RadialGradientView gradientView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        gradientView = (RadialGradientView) findViewById(R.id.background);
        ColorGradient.changeBackground(gradientView,this);
        totalPointers= GeneralUtils.getValueSharePref(this);
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
                        Intent intent = new Intent(SplashScreen.this, SelectLevel.class);
                        startActivity(intent);
                        finish();
                    }

                } catch (Exception e) {
                }
            }
        },2500);
    }
}
