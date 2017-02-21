package com.finger.dance.data;

import android.content.Context;
import android.util.Log;

import com.finger.dance.R;
import com.finger.dance.adapters.TileAdapter;
import com.finger.dance.helper.GeneralHelper;
import com.finger.dance.models.TileModel;

import java.util.List;
import java.util.Random;

/**
 * Created by prakash-bala on 21/2/17.
 */

public class TileColorData {
    int color[] = {R.color.colorAccent,
            R.color.color1,
            R.color.color2,
            R.color.color3,
            R.color.color4,
            R.color.color5,
            R.color.color6,
            R.color.color7,
            R.color.color8,
            R.color.color9,
            R.color.color10,};
    int randomColor;
    public TileColorData(List<TileModel> tileModelList, TileAdapter adapter, Context context) {

        Log.i("colorgenerated",randomColor+"");

        TileModel offerModel;

        for (int i =1;i<=32;i++){
            randomColor = GeneralHelper.randInt(0,9);
            offerModel = new TileModel(context.getResources().getColor(color[randomColor]));
            tileModelList.add(offerModel);

        }
        adapter.notifyDataSetChanged();

    }


}
