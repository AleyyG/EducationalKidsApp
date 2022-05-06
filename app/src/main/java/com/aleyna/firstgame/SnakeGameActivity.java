package com.aleyna.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.Timer;
import java.util.TimerTask;

public class SnakeGameActivity extends AppCompatActivity {

    ImageButton backButton,restartButton;
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
        timer.scheduleAtFixedRate(timerTask,10,1000/FPS);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}