package com.finger.dance;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.finger.dance.activities.PlayGround;
import com.finger.dance.helper.GeneralHelper;
import com.finger.dance.utils.MultiTouchView;

import static android.R.attr.x;

public class MainActivity extends AppCompatActivity {
    private static final String KEY = "keypointers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button storeButton = (Button) findViewById(R.id.buttonstore);

        GeneralHelper.showDialog("Please Help us to analyze your phone's maximum pointer. Keep all Your fingers to" +
                "calibrate.",this);

        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MultiTouchView customView = (MultiTouchView) findViewById(R.id.multitouchview);
                int totalPointers = customView.getTotalPointers();

                SharedPreferences pref = getApplicationContext().getSharedPreferences("FingerDance", 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt(KEY,totalPointers);
                editor.commit();

                Intent intent = new Intent(MainActivity.this, PlayGround.class);
                startActivity(intent);
                finish();

            }
        });


    }
}
