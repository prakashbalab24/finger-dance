package com.finger.dance.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.finger.dance.R;
import com.finger.dance.component.ui.RadialGradientView;
import com.finger.dance.utils.ColorGradient;

public class ScoreBoard extends AppCompatActivity {
    private Button playAgain,calibrate;
    private TextView winner;
    private RadialGradientView gradientView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        gradientView = (RadialGradientView) findViewById(R.id.background);
        ColorGradient.changeBackground(gradientView,this);
        playAgain = (Button) findViewById(R.id.playagainBut);
        calibrate = (Button) findViewById(R.id.calibrateBut);
        winner = (TextView) findViewById(R.id.winnerTv);
        Intent intent = getIntent();
        String winnerString = intent.getStringExtra("winner");
        if(winnerString!=null){
            winner.setText(winnerString);
        }

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ScoreBoard.this,SelectLevel.class);
                startActivity(intent1);
                finish();
            }
        });

        calibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ScoreBoard.this,MainActivity.class);
                startActivity(intent1);
                finish();

            }
        });


    }
}
