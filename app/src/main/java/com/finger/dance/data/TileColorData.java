package com.finger.dance.data;

import android.content.Context;
import android.util.Log;

import com.finger.dance.R;
import com.finger.dance.adapters.TileAdapter;
import com.finger.dance.utils.GeneralUtils;
import com.finger.dance.models.TileModel;

import java.util.List;

/**
 * Created by prakash-bala on 21/2/17.
 */

public class TileColorData {
    int color[] = {R.color.color10,
            R.color.color1,
            R.color.color2,
            R.color.color3,
            R.color.color4,
            R.color.color5,
           R.color.color6,
            R.color.color7,
            R.color.color8,
            R.color.color9,
    R.color.color10,R.color.color11,R.color.color12,
            R.color.color13,
            R.color.color14,
            R.color.color15,
            R.color.color16,
            R.color.color17,
            R.color.color18,
            R.color.color19,
            R.color.color20,
            R.color.color21,
            R.color.color22,
            R.color.color23,
            R.color.color24,
            R.color.color25,
            R.color.color26};
    int randomColor;
    public TileColorData(List<TileModel> tileModelList, TileAdapter adapter, Context context,int level) {

        Log.i("leveldata",level+"");

        TileModel offerModel;

        for (int i =1;i<=(level*level);i++){
            randomColor = GeneralUtils.randInt(0,25);
            offerModel = new TileModel(context.getResources().getColor(color[randomColor]));
            tileModelList.add(offerModel);

        }
        adapter.notifyDataSetChanged();

    }


}
