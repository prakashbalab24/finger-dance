package com.finger.dance.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.finger.dance.R;
import com.finger.dance.component.ui.RadialGradientView;
import com.finger.dance.utils.AppConstants;
import com.finger.dance.utils.ColorGradient;
import com.finger.dance.utils.GeneralUtils;
import com.finger.dance.utils.SoundManager;

import java.util.ArrayList;
import java.util.List;

import static com.finger.dance.utils.SoundManager.muteSound;
import static com.finger.dance.utils.SoundManager.pauseMusic;
import static com.finger.dance.utils.SoundManager.resumeMusic;

public class SelectLevel extends AppCompatActivity {
    private TextView easy,medium,hard;
    private RadialGradientView gradientView;
    private ImageView sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);
        gradientView = (RadialGradientView) findViewById(R.id.background);
        ColorGradient.changeBackground(gradientView,this);
        easy = (TextView) findViewById(R.id.easy);
        medium = (TextView) findViewById(R.id.medium);
        hard = (TextView) findViewById(R.id.hard);
        sound = (ImageView) findViewById(R.id.music) ;

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                muteSound();
            }
        });
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

    @Override
    protected void onPause() {
        super.onPause();
        pauseMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumeMusic();
    }
}
