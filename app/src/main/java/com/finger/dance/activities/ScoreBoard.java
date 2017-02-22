package com.finger.dance.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.finger.dance.R;
import com.finger.dance.SplashScreen;

public class ScoreBoard extends AppCompatActivity {
    private Button playAgain;
    private TextView winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        playAgain = (Button) findViewById(R.id.playagainBut);
        winner = (TextView) findViewById(R.id.winnerTv);
        Intent intent = getIntent();
        String winnerString = intent.getStringExtra("winner");
        if(winnerString!=null){
            winner.setText(winnerString+" "+"Wins !!!");
        }

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ScoreBoard.this,SplashScreen.class);
                startActivity(intent1);
                finish();
            }
        });
    }
}
