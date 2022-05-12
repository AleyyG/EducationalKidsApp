package com.aleyna.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SnakeGameActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ImageButton backButton,restartButton;
    TextView scoreText;
    SnakeGame snakeGame;
    final int FPS = 5;

    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() { //burada goruntuyu surekli guncelleyen bir timer olusturduk aslinda
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                /*
                 bu thread su ise yaramaktadir. Uygulama calisirken bir mainThread bulunmaktadir. Bu thread calisirken ben bir drawi
                 surekli olarak calistirdigimdan(timer ile) main threadi kitlemektedir. Bu kitlemenin onune gecebilmek icin bu islemi
                 yeni bir thread olusturarak main thread uzerinden farkli bir thread de yapmami sagliyor.
                 */
                @Override
                public void run() {
                    snakeGame.updateScreen();
                }
            });
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake_game);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        backButton = findViewById(R.id.back_button);
        restartButton = findViewById(R.id.restart_button);
        snakeGame = findViewById(R.id.snakeGameView);
        scoreText = findViewById(R.id.score);
        timer.scheduleAtFixedRate(timerTask,10,1000/FPS);

        snakeGame.setGameUpdateListener(new SnakeGame.GameUpdateListener() {
            @Override
            public void onScoreUpdate(int score) {
                scoreText.setText(String.valueOf(score*25));
               // sharedPreferences.edit().remove("score").commit();
                int highScore = sharedPreferences.getInt("score",0);
                if(score*25>=highScore){
                    editor.putInt("score",score*25);
                    editor.apply();
                }
                Log.e("denemee",String.valueOf(highScore));

            }

            @Override
            public void onGameOver(int score) {
                //oyun bittiginde olacaklar
            }
        });
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreText.setText(String.valueOf(0));
                snakeGame.restart();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(SnakeGameActivity.this,SnakeGameActivity.class);
                startActivity(main);
            }
        });
    }
}