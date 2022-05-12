package com.aleyna.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AlphabetGameActivity extends AppCompatActivity {
    Main main = new Main();
    ImageButton backButton,nextButton,letterButton;
    MediaPlayer mediaPlayer;
    ArrayList<Integer> letterRes = new ArrayList<>();
    ArrayList<Integer> letterSoundsRes = new ArrayList<>();
    int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_game);

        backButton = findViewById(R.id.backButton);
        nextButton = findViewById(R.id.next_button);
        letterButton = findViewById(R.id.letterButton);

        final LottieAnimationView background = findViewById(R.id.background);
        main.ChangeBackground(background);

        mediaPlayer = MediaPlayer.create(this,R.raw.letter_a);
        AddLetterSounds();
        AddLetterResources();
        main.StudyTimer(getBaseContext());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameScene = new Intent(AlphabetGameActivity.this,GameActivity.class);
                startActivity(gameScene);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeLetterCard();
            }
        });
        letterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });
    }
    public void ChangeLetterCard(){
        if(counter == letterRes.size()){
            counter = 0;
        }
        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(this,letterSoundsRes.get(counter));
        letterButton.setImageResource(letterRes.get(counter));
        counter++;
    }
   /* public void StudyTimer(){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                int timeSecond = sharedPreferences.getInt("alphabetSecond",0);
                timeSecond++;
                editor.putInt("alphabetSecond",timeSecond).apply();
                Log.e("denemee","second: " + String.valueOf(timeSecond));
            }
        };
        timer.schedule(timerTask,0,1000); //saniyeyi olcmus oluyor.
    }

    */
    public void AddLetterSounds(){
        letterSoundsRes.add(R.raw.letter_a);
        letterSoundsRes.add(R.raw.letter_b);
        letterSoundsRes.add(R.raw.letter_c);
        letterSoundsRes.add(R.raw.letter_d);
        letterSoundsRes.add(R.raw.letter_e);
        letterSoundsRes.add(R.raw.letter_f);
        letterSoundsRes.add(R.raw.letter_g);
        letterSoundsRes.add(R.raw.letter_h);
        letterSoundsRes.add(R.raw.letter_i);
        letterSoundsRes.add(R.raw.letter_j);
        letterSoundsRes.add(R.raw.letter_k);
        letterSoundsRes.add(R.raw.letter_l);
        letterSoundsRes.add(R.raw.letter_m);
        letterSoundsRes.add(R.raw.letter_n);
        letterSoundsRes.add(R.raw.letter_o);
        letterSoundsRes.add(R.raw.letter_p);
        letterSoundsRes.add(R.raw.letter_q);
        letterSoundsRes.add(R.raw.letter_r);
        letterSoundsRes.add(R.raw.letter_s);
        letterSoundsRes.add(R.raw.letter_t);
        letterSoundsRes.add(R.raw.letter_u);
        letterSoundsRes.add(R.raw.letter_v);
        letterSoundsRes.add(R.raw.letter_w);
        letterSoundsRes.add(R.raw.letter_x);
        letterSoundsRes.add(R.raw.letter_y);
        letterSoundsRes.add(R.raw.letter_z);
    }
    public void AddLetterResources(){
        letterRes.add(R.drawable.letter_a);
        letterRes.add(R.drawable.letter_b);
        letterRes.add(R.drawable.letter_c);
        letterRes.add(R.drawable.letter_d);
        letterRes.add(R.drawable.letter_e);
        letterRes.add(R.drawable.letter_f);
        letterRes.add(R.drawable.letter_g);
        letterRes.add(R.drawable.letter_h);
        letterRes.add(R.drawable.letter_i);
        letterRes.add(R.drawable.letter_j);
        letterRes.add(R.drawable.letter_k);
        letterRes.add(R.drawable.letter_l);
        letterRes.add(R.drawable.letter_m);
        letterRes.add(R.drawable.letter_n);
        letterRes.add(R.drawable.letter_o);
        letterRes.add(R.drawable.letter_p);
        letterRes.add(R.drawable.letter_q);
        letterRes.add(R.drawable.letter_r);
        letterRes.add(R.drawable.letter_s);
        letterRes.add(R.drawable.letter_t);
        letterRes.add(R.drawable.letter_u);
        letterRes.add(R.drawable.letter_v);
        letterRes.add(R.drawable.letter_w);
        letterRes.add(R.drawable.letter_x);
        letterRes.add(R.drawable.letter_y);
        letterRes.add(R.drawable.letter_z);
    }
}