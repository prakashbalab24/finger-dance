package com.finger.dance.utils;

import android.content.Context;

import com.finger.dance.R;
import com.finger.dance.component.ui.RadialGradientView;

/**
 * Created by prakash-bala on 2/3/17.
 */

public class ColorGradient {
    private static final int[][] BACKGROUNDS = {
            {R.color.background_pink_start, R.color.background_pink_end},
            {R.color.background_yellow_start, R.color.background_yellow_end},
            {R.color.background_blue_start, R.color.background_blue_end},
            {R.color.background_green_start, R.color.background_green_end},
            {R.color.background_orange_start, R.color.background_orange_end},
            {R.color.background_purple_start, R.color.background_purple_end},
            {R.color.background_red_start, R.color.background_red_end},
            {R.color.background_teal_start, R.color.background_teal_end},
    };
    private static int backgroundIndex=0;

    public static void changeBackground(final RadialGradientView gradientView,final Context context) {
        backgroundIndex++;
        if (backgroundIndex == BACKGROUNDS.length) {
            backgroundIndex = 0;
        }
        gradientView.postDelayed(new Runnable() {
            @Override
            public void run() {
                int startColor = context.getResources().getColor(BACKGROUNDS[backgroundIndex][0]);
                int endColor = context.getResources().getColor(BACKGROUNDS[backgroundIndex][1]);
                gradientView.changeColor(startColor, endColor);
            }
        }, 500);
    }

}
