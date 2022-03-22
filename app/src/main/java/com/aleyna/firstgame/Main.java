package com.aleyna.firstgame;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageButton;

import com.airbnb.lottie.LottieAnimationView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

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
 /*   public void BackGameScene(ImageButton backButton){
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    } buraya butona basılınca geri gitme kodunu eklemem gerek çünkü bu her sahnede olacak
    */

}

