package com.aleyna.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SnakeGameActivity extends AppCompatActivity {

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
        backButton = findViewById(R.id.back_button);
        restartButton = findViewById(R.id.restart_button);
        snakeGame = findViewById(R.id.snakeGameView);
        scoreText = findViewById(R.id.score);
        timer.scheduleAtFixedRate(timerTask,10,1000/FPS);

        snakeGame.setGameUpdateListener(new SnakeGame.GameUpdateListener() {
            @Override
            public void onScoreUpdate(int score) {
                scoreText.setText(String.valueOf(score*25));
            }

            @Override
            public void onGameOver(int score) {

            }
        });
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreText.setText(String.valueOf(0));
                snakeGame.restart();
            }
        });
    }
}