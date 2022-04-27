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

