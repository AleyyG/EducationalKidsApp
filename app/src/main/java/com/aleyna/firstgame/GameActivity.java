package com.aleyna.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final LottieAnimationView colorGame = findViewById(R.id.nextButton);
        final LottieAnimationView numbersGame = findViewById(R.id.numbersGame);
        final LottieAnimationView puzzleGame = findViewById(R.id.puzzleGame);
        final LottieAnimationView foodGame = findViewById(R.id.foodGame);

        colorGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent colorGameScene = new Intent(GameActivity.this,ColorGameActivity.class);
                startActivity(colorGameScene);
            }
        });

        numbersGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numberGameScene = new Intent(GameActivity.this,NumberGameActivity.class);
                startActivity(numberGameScene);
            }
        });

        puzzleGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent puzzleGameScene = new Intent(GameActivity.this,PuzzleGameActivity.class);
                startActivity(puzzleGameScene);
            }
        });

        foodGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent foodGameScene = new Intent(GameActivity.this,FoodGameActivity.class);
                startActivity(foodGameScene);
            }
        });


    }
}