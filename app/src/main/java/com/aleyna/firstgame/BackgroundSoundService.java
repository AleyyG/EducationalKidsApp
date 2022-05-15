package com.aleyna.firstgame;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;

import androidx.annotation.Nullable;

public class BackgroundSoundService extends Service {

    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent arg0) {

        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mediaPlayer != null){

        }else{
            mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.background_sound);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
    }
}
