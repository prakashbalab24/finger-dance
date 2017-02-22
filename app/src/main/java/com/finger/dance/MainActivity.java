package com.finger.dance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.finger.dance.activities.PlayGround;
import com.finger.dance.utils.AppConstants;
import com.finger.dance.utils.GeneralUtils;
import com.finger.dance.component.ui.MultiTouchView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button storeButton = (Button) findViewById(R.id.buttonstore);

        GeneralUtils.showDialog("Pointer Calibration","This is one time process! Keep all Your fingers on the screen to " +
                "calibrate.",this);

        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MultiTouchView customView = (MultiTouchView) findViewById(R.id.multitouchview);
                int totalPointers = customView.getTotalPointers();
                Log.i("totalpointers",totalPointers+"");
                if(totalPointers==0) {
                    GeneralUtils.showDialog("Try again","Please try to keep maximum fingers on the blue screen.",MainActivity.this);
                }
                else {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("FingerDance", 0);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putInt(AppConstants.KEY, totalPointers);
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this, PlayGround.class);
                    startActivity(intent);
                    finish();
                     }

            }
        });


    }
}
