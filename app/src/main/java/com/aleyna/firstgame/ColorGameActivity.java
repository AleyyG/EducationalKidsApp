package com.aleyna.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Random;

public class ColorGameActivity extends AppCompatActivity {
    ImageButton colorButton;
    ImageButton backGameScene;
    int controlNumber;
    Random random = new Random();
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_game);
        backGameScene = findViewById(R.id.backButton);
        colorButton = findViewById(R.id.colorButton);
        final LottieAnimationView nextButton = findViewById(R.id.nextButton);
        mediaPlayer = MediaPlayer.create(this,R.raw.blue_sound);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextButton.playAnimation();
                int randNumber = random.nextInt(11);
                RandomCards(randNumber,colorButton);
            }
        });
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });
        backGameScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameScene = new Intent(ColorGameActivity.this,GameActivity.class);
                startActivity((gameScene));
            }
        });
    }
    public void RandomCards(int number, ImageButton colorButton){
        switch (number){
            case 1: colorButton.setImageResource(R.drawable.black);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.black_sound);
                    break;
            case 2: colorButton.setImageResource(R.drawable.brown);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.brown_sound);
                    break;
            case 3: colorButton.setImageResource(R.drawable.green);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.green_sound);
                    break;
            case 4: colorButton.setImageResource(R.drawable.grey);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.grey_sound);
                    break;
            case 5: colorButton.setImageResource(R.drawable.orange);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.orange_sound);
                    break;
            case 6: colorButton.setImageResource(R.drawable.pink);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.pink_sound);
                    break;
            case 7: colorButton.setImageResource(R.drawable.purple);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.purple_sound);
                    break;
            case 8: colorButton.setImageResource(R.drawable.red);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.red_sound);
                    break;
            case 9: colorButton.setImageResource(R.drawable.white);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.white_sound);
                    break;
            case 10: colorButton.setImageResource(R.drawable.yellow);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.yellow_sound);
                    break;
            case 0:
            default: colorButton.setImageResource(R.drawable.blue);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.blue_sound);
                    break;
        }
    }
}