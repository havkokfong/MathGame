package com.example.math_game;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class SoundPlayer {
    private AudioAttributes audioAttributes;
    final int SOUND_POOL_MAX = 3;

    private static SoundPool soundPool;
    private static int correctSound;
    private static int incorrectSound;

    public SoundPlayer(Context context){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(SOUND_POOL_MAX)
                    .build();
        }else {
            soundPool = new SoundPool(SOUND_POOL_MAX, AudioManager.STREAM_MUSIC, 0);
        }
        correctSound = soundPool.load(context, R.raw.correct, 1);
        incorrectSound = soundPool.load(context, R.raw.incorrect, 1);
    }

    public void playCorrectSound(){
        soundPool.play(correctSound, 1.0f, 1.0f,1,0,1.0f);
    }

    public void playIncorrectSound(){
        soundPool.play(incorrectSound, 1.0f,1.0f,1,0,1.0f);
    }
}
