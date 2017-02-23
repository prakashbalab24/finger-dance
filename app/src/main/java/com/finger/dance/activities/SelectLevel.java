package com.finger.dance.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.finger.dance.R;
import com.finger.dance.utils.AppConstants;
import com.finger.dance.utils.GeneralUtils;

import java.util.ArrayList;
import java.util.List;

public class SelectLevel extends AppCompatActivity {
    private Button easy,medium,hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);
        easy = (Button) findViewById(R.id.easy);
        medium = (Button) findViewById(R.id.medium);
        hard = (Button) findViewById(R.id.hard);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GeneralUtils.a = new ArrayList<Integer>();
                startGame(3);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GeneralUtils.a = new ArrayList<Integer>();
                startGame(4);

            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GeneralUtils.a = new ArrayList<Integer>();
                startGame(5);
            }
        });
    }
    private void startGame(int level){
        Intent intent = new Intent(SelectLevel.this,PlayGround.class);
        intent.putExtra(AppConstants.LEVEL,level);
        startActivity(intent);
        finish();
    }
}
