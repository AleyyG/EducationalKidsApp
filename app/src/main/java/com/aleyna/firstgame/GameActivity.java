package com.aleyna.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class GameActivity extends AppCompatActivity {
    Main main = new Main();
    Context context;
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final LottieAnimationView colorGame = findViewById(R.id.colorGame); //burada oynayan animasyonlarimi id ile bulduruyorum
        final LottieAnimationView numbersGame = findViewById(R.id.numbersGame);
        final LottieAnimationView mathGame = findViewById(R.id.mathGame);
        final LottieAnimationView animalGame = findViewById(R.id.animalGame);
        final LottieAnimationView matchingGame = findViewById(R.id.matchingGame);
        final LottieAnimationView alphabetGame = findViewById(R.id.alphabetGame);
        backButton = findViewById(R.id.backButton);
        context = this;

        if(main.getMinute(context)==2)
            backButton.setImageResource(R.drawable.reply_noti);
        else
            backButton.setImageResource(R.drawable.reply);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainScene = new Intent(GameActivity.this,MainActivity.class);
                startActivity(mainScene);
            }
        });

        alphabetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alphabetGameScene = new Intent(GameActivity.this,AlphabetGameActivity.class);
                startActivity(alphabetGameScene);
            }
        });
        matchingGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent matchingGameScene = new Intent(GameActivity.this,MatchingGameActivity.class);
                startActivity(matchingGameScene);
            }
        });

        animalGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent animalGameScene = new Intent(GameActivity.this,AnimalGameActivity.class);
                startActivity(animalGameScene);
            }
        });

        //sonrasinda hepsine tikladigim zaman gerekli activitylere gecmeleri icin onClick metodlarinda gecislerini yaziyorum.
        mathGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mathGameScene = new Intent(GameActivity.this,MathGameActivity.class);
                startActivity(mathGameScene);
            }
        });

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
    }
}