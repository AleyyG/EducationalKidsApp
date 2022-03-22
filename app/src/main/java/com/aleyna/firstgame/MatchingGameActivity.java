package com.aleyna.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;

public class MatchingGameActivity extends AppCompatActivity {
    Main main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_game);

        final LottieAnimationView background = findViewById(R.id.background);
        main.ChangeBackground(background);
    }
}