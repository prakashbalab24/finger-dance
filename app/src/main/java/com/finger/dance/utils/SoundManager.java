package com.finger.dance.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

/**
 * Created by prakash-bala on 25/3/17.
 */

public class SoundManager {
    private static  MediaPlayer mPlayer;
    private static Boolean playSoundBool = true;
    private static Context mContext;

    public static void inizializeAudio(Context context){
        mContext = context;

    }

    public static void pauseMusic(){
        if(mPlayer.isPlaying()) {
            mPlayer.stop();
        }
    }

    public static void resumeMusic(){
        mPlayer.start();
    }

    public static void muteSound() {
        if (mPlayer.isPlaying()) {
            mPlayer.stop();
        }
        if (playSoundBool) {
            playSoundBool = false;
        }
        else {
            playSoundBool = true;
        }

    }

    public static void playSound(int soundtoPlay, boolean loop){
        Log.i("playSoundBool",playSoundBool+"");
        if (!playSoundBool){
            return;
        }
        mPlayer = MediaPlayer.create(mContext, soundtoPlay);
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        if (loop) {
            mPlayer.setLooping(true);
        }
        mPlayer.start();
    }
}
