package com.finger.dance.adapters;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.finger.dance.R;
import com.finger.dance.helper.GeneralHelper;
import com.finger.dance.models.TileModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prakash-bala on 21/2/17.
 */

public class TileAdapter extends RecyclerView.Adapter<TileAdapter.MyViewHolder> {

    private Context mContext;
    private List<TileModel> tileModelList;
    private TileModel tileModel;
    private static int firstChange = -1;
    private static int playerColor;
    private List mActivePointers = new ArrayList();


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
                switch(event.getAction() & MotionEvent.ACTION_MASK)
                {
                    case MotionEvent.ACTION_DOWN:
                        mActivePointers.add(pointerId);
                        ColorDrawable cd = (ColorDrawable) holder.tileView.getBackground();
                        int colorCode = cd.getColor();
                        changeColor(colorCode);
                       return true;
                    case MotionEvent.ACTION_UP:
                        Log.i("poiterindex",pointerId+"");
                        if (mActivePointers.size()<=pointerId) {
                            mActivePointers.remove(pointerId);
                        }
                        if(pointerId==0) {
                            GeneralHelper.showMsg("Black Wins!!",mContext);
                            break;
                        }
                            GeneralHelper.checkWinner(mActivePointers,mContext);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }

                return false;
            }
        });
    }

    public void changeColor(int tileColor){

        if(tileColor != playerColor){
            if(playerColor==mContext.getResources().getColor(R.color.black)){
                GeneralHelper.showMsg("White Wins!",mContext);
            }
            else {
                GeneralHelper.showMsg("Black Wins!",mContext);
            }
            return;
        }
        int num = GeneralHelper.randIntUnique(1, 20);

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