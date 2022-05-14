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

    public int getMinute(Context context){
        sharedPreferences = context.getSharedPreferences("com.aleyna.firstgame",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        int minute = sharedPreferences.getInt("minute",0);
        return minute;
    }
    public int getSnakeMinute(Context context){
        sharedPreferences = context.getSharedPreferences("com.aleyna.firstgame",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        int minute = sharedPreferences.getInt("snakeMinute",0);
        return minute;
    }
    public void ResetMinute(Context context){
        sharedPreferences = context.getSharedPreferences("com.aleyna.firstgame",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.remove("minute").commit();
    }
    public void ResetSnakeMinute(Context context){
        sharedPreferences = context.getSharedPreferences("com.aleyna.firstgame",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.remove("snakeMinute").commit();
    }

    public void StudyTimer(Context context){
        sharedPreferences = context.getSharedPreferences("com.aleyna.firstgame",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                editor.remove("second").commit(); //burasi yorum satirina alinacak
                editor.remove("minute").commit(); //burasi yorum satirina alinacak
                int timeSecond = sharedPreferences.getInt("second",0);
                int minute = sharedPreferences.getInt("minute",0);
                if(minute==2){
                    editor.remove("second").commit();
                    timer.cancel();
                }else{
                    timeSecond++;
                    editor.putInt("second",timeSecond).apply();
                    if(timeSecond%60==0){
                        minute++;
                        editor.putInt("minute",minute).apply();
                        Log.e("denemee","minute: " + String.valueOf(minute));
                    }
                    Log.e("denemee","second: " + String.valueOf(timeSecond));
                }

            }
        };
        timer.schedule(timerTask,0,1000); //saniyeyi olcmus oluyor.
    }
    public void SnakeTimer(Context context){
        sharedPreferences = context.getSharedPreferences("com.aleyna.firstgame",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                editor.remove("snakeSecond").commit(); //burasi yorum satirina alinacak
                editor.remove("snakeMinute").commit(); //burasi yorum satirina alinacak
                int timeSecond = sharedPreferences.getInt("snakeSecond",0);
                int minute = sharedPreferences.getInt("snakeMinute",0);

                if(minute==2){
                    editor.remove("snakeSecond").commit();
                    timer.cancel();
                    Log.e("denemee","snakeMinute: " + String.valueOf(minute));
                    Log.e("denemee","snakeSecond: " + String.valueOf(timeSecond));
                }else{
                    timeSecond++;
                    editor.putInt("snakeSecond",timeSecond).apply();
                    if(timeSecond%60==0){
                        minute++;
                        editor.putInt("snakeMinute",minute).apply();
                        Log.e("denemee","snakeMinute: " + String.valueOf(minute));
                    }
                    Log.e("denemee","snakeSecond: " + String.valueOf(timeSecond));
                }
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

