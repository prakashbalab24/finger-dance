package com.finger.dance.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.finger.dance.R;
import com.finger.dance.helper.GeneralHelper;
import com.finger.dance.models.TileModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by prakash-bala on 30/1/17.
 */

public class TileAdapter extends RecyclerView.Adapter<TileAdapter.MyViewHolder> {

    private Context mContext;
    private List<TileModel> tileModelList;
    private TileModel tileModel;
    private static int firstChange = -1;
    private static int playerColor;
    private List mActivePointers = new ArrayList();
    private List clonePointers ;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public  TextView tileView;
        public MyViewHolder(View view) {
            super(view);

            tileView = (TextView) view.findViewById(R.id.tileView);
        }
    }


    public TileAdapter(Context mContext, List<TileModel> tileModelList) {
        this.mContext = mContext;
        this.tileModelList = tileModelList;
        playerColor = mContext.getResources().getColor(R.color.black);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tile_layout, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        tileModel = tileModelList.get(position);
        holder.tileView.setBackgroundColor(tileModel.getColor());
        Handler handler = new Handler();

        if(firstChange == -1) {
            firstChange = 0;
            final Runnable r = new Runnable() {
                public void run() {
                    changeColor(playerColor);
                }
            };

            handler.post(r);
        }

        holder.tileView.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int pointerIndex = event.getActionIndex();

                // get pointer ID
                int pointerId = event.getPointerId(pointerIndex);
                Log.i("pointid",pointerId+"");
                switch(event.getAction() & MotionEvent.ACTION_MASK)
                {
                    case MotionEvent.ACTION_DOWN:
                        Log.i("actionbutton", "added:"+pointerId);
                        mActivePointers.add(pointerId);
                        ColorDrawable cd = (ColorDrawable) holder.tileView.getBackground();
                        int colorCode = cd.getColor();
                        changeColor(colorCode);
                       return true;
                    case MotionEvent.ACTION_UP:
                        Log.i("actionbutton", "removed:"+pointerId);
                        mActivePointers.remove(pointerId);
                        if(!mActivePointers.contains(0)) {
                            Toast.makeText(mContext,"Black Wins",Toast.LENGTH_LONG).show();
                            Log.i("playerWinn","Firstplayer out");
                            break;
                        }
                        else {
                            checkWinner(mActivePointers);
                        }
                        Log.i("actionbutton","Up");
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }

                return false;
            }
        });
    }

    public  void checkWinner (List allPointers){
       for (int i=1;i<=allPointers.size();i++){
           if(!allPointers.contains(i)){
               if(i%2==0){
                   Toast.makeText(mContext,"Black Wins",Toast.LENGTH_LONG).show();
                   Log.i("playerWinn","first player out");
               }
               else {
                   Toast.makeText(mContext,"White Wins",Toast.LENGTH_LONG).show();
                   Log.i("playerWinn","second player out");
               }
               break;
           }
       }


    }

    public void changeColor(int tileColor){

        if(tileColor != playerColor){
            Log.i("failedcolor","failed");
            if(playerColor==mContext.getResources().getColor(R.color.black)){
                Toast.makeText(mContext,"White Wins",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(mContext,"Black Wins",Toast.LENGTH_LONG).show();
            }
            return;
        }
        int num = GeneralHelper.randIntUnique(1, 20);

        Log.i("uniquenum",num+"");

        if (num != -1 ) {
            tileModel = tileModelList.get(num);
            if(playerColor == mContext.getResources().getColor(R.color.black)) {
                playerColor = mContext.getResources().getColor(R.color.white);
            }
            else if (playerColor == mContext.getResources().getColor(R.color.white)){
                playerColor = mContext.getResources().getColor(R.color.black);
            }
            tileModel.setColor(playerColor);
            notifyItemChanged(num);
        } else {
            Log.i("numberprint", "Out of bound");
        }

    }

    @Override
    public int getItemCount() {
         return tileModelList.size();
    }
}