package com.finger.dance.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.finger.dance.R;
import com.finger.dance.activities.ScoreBoard;
import com.finger.dance.component.ui.RadialGradientView;
import com.finger.dance.utils.AppConstants;
import com.finger.dance.utils.ColorGradient;
import com.finger.dance.utils.GeneralUtils;
import com.finger.dance.models.TileModel;

import java.util.List;

/**
 * Created by prakash-bala on 21/2/17.
 */

public class TileAdapter extends RecyclerView.Adapter<TileAdapter.MyViewHolder> {

    private static final long FADE_DURATION = 1000;
    private Context mContext;
    private List<TileModel> tileModelList;
    private TileModel tileModel;
    private int firstChange = -1;
    private static int playerColor;
    private int intentStarted = 0;
    private int level;
    private RadialGradientView gradientView;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public Button tileView;
        public CardView cardView;
        public MyViewHolder(View view) {
            super(view);

            tileView = (Button) view.findViewById(R.id.tileView);
            cardView = (CardView) view.findViewById(R.id.cardView);
        }
    }


    public TileAdapter(Context mContext, List<TileModel> tileModelList, int level, RadialGradientView gradientView) {
        this.mContext = mContext;
        this.tileModelList = tileModelList;
        this.level = level;
        this.gradientView= gradientView;
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
        GeneralUtils.setCardDesign(level,holder,mContext);
        Handler handler = new Handler();

        if(firstChange == -1) {
            firstChange = 0;
            final Runnable r = new Runnable() {
                public void run() {
                    checkClickedTile(playerColor);
                }
            };
            handler.post(r);
        }
        holder.tileView.setBackgroundColor(tileModel.getColor());


        holder.tileView.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int pointerIndex = event.getActionIndex();

                // get pointer ID
                int pointerId = event.getPointerId(pointerIndex);
                switch(event.getAction() & MotionEvent.ACTION_MASK)
                {
                    case MotionEvent.ACTION_DOWN:
                        ColorGradient.changeBackground(gradientView,mContext);
                        ColorDrawable cd = (ColorDrawable) holder.tileView.getBackground();
                        int colorCode = cd.getColor();
                        if(checkMaxPointReached(pointerId)){
                            startIntent("Match Draw");
                        }
                        else {
                            checkClickedTile(colorCode);
                        }
                       return true;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        if (pointerId==0){
                            startIntent(mContext.getString(R.string.blackwinner));
                            break;
                        }
                        if(pointerId%2==0){
                            startIntent(mContext.getString(R.string.blackwinner));
                        }
                        else {
                            startIntent(mContext.getString(R.string.whitewinner));
                        }
                        break;
                }

                return true;
            }
        });
    }


    /**method for maximum device pointer checking **/
    private boolean checkMaxPointReached(int pointerId){
        int value = GeneralUtils.getValueSharePref(mContext);
        if (pointerId==(value-1)) {
            return true;
        }
        return false;
    }


    /**method for checking tile click **/
    private void checkClickedTile(int tileColor){
        Log.i("playercolor",tileColor+"");

        if(tileColor != playerColor){
            if(playerColor==mContext.getResources().getColor(R.color.black)){
                startIntent(mContext.getString(R.string.whitewinner));
            }
            else {
                startIntent(mContext.getString(R.string.blackwinner));
            }
            return;
        }
        else {
            togglePlayer();
        }


    }

    /** method for changing player turn **/
    private void togglePlayer(){
        GeneralUtils.playSound(AppConstants.CLICK_SOUND,mContext);
        int num = GeneralUtils.randIntUnique(0, (level*level)-1);
        Log.i("randomnum",num+"");

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
            Log.i("numberprint", "Exception: All tiles finished");
        }
    }


    /** Genral method for intent **/
    private void startIntent(String winner){
        if (!winner.equalsIgnoreCase("Match Draw")){
            Toast.makeText(mContext,mContext.getString(R.string.wrong_des),Toast.LENGTH_SHORT).show();
        }
        if(intentStarted!=-1) {
            GeneralUtils.playSound(AppConstants.WRONG_CLICK_SOUND,mContext);
            intentStarted = -1;
            Intent intent = new Intent((Activity) mContext, ScoreBoard.class);
            intent.putExtra("winner", winner);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(((Activity) mContext)).toBundle());
                ((Activity) mContext).finish();
            }
            else {
                mContext.startActivity(intent);
                ((Activity) mContext).finish();
            }

        }
    }



    @Override
    public int getItemCount() {
         return tileModelList.size();
    }
}