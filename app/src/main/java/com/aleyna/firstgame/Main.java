package com.aleyna.firstgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public void StudyTimer(Context context){

        sharedPreferences = context.getSharedPreferences("com.aleyna.firstgame",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //sharedPreferences.edit().remove("second").commit();
                int timeSecond = sharedPreferences.getInt("second",0);
                int minute = sharedPreferences.getInt("minute",0);
                timeSecond++;
                editor.putInt("second",timeSecond).apply();
                if(timeSecond%60==0){
                    minute++;
                    editor.putInt("minute",minute).apply();
                    Log.e("denemee","minute: " + String.valueOf(minute));
                }
                if(minute==2){
                    minute = 0;
                    timeSecond = 0;
                    sharedPreferences.edit().remove("second").commit();
                    sharedPreferences.edit().remove("minute").commit();
                    timer.cancel();
                }
                Log.e("denemee","second: " + String.valueOf(timeSecond));
            }
        };
        timer.schedule(timerTask,0,1000); //saniyeyi olcmus oluyor.
    }


    public void ChangeBackground(LottieAnimationView background) { //her sahnede arka plan böyle olacaginden dolayi burayi bu sekilde yaptim.
        Date date = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("k");
        int time = Integer.parseInt(simpleDateFormat.format(date));
        if (time > 20 || time < 7) {
            background.setAnimation(R.raw.night_background);
        } else {
            background.setAnimation(R.raw.morning_background);
        }
    }
}

