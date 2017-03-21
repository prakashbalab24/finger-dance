package com.finger.dance.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.finger.dance.R;
import com.finger.dance.component.ui.RadialGradientView;
import com.finger.dance.utils.AppConstants;
import com.finger.dance.utils.ColorGradient;
import com.finger.dance.utils.GeneralUtils;

public class SplashScreen extends AppCompatActivity {
    int totalPointers;
    private RadialGradientView gradientView;
    private View name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        name = findViewById(R.id.head);
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
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
/**Animation trying **/
//      ActivityOptions options = ActivityOptions
//                                    .makeSceneTransitionAnimation(SplashScreen.this, name,getResources().getString(R.string.app_name));
//                            Intent intent = new Intent(SplashScreen.this, SelectLevel.class);
//                            Log.i("animationimage","Working");
//                            startActivity(intent,options.toBundle());
//                            finish();
                            Intent intent = new Intent(SplashScreen.this, SelectLevel.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Intent intent = new Intent(SplashScreen.this, SelectLevel.class);
                            startActivity(intent);
                            finish();
                        }

                    }

                } catch (Exception e) {
                }
            }
        },2500);
    }
}
