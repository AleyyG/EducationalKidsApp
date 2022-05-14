package com.aleyna.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Main main = new Main();
    Context context;
    TextView info;
    ImageButton playButton,snakeButton; //design ekraninda olan buttonlarimi java uzerinde tanimliyorum
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = findViewById(R.id.playButton); //butonumun yerini kod uzerinden acilis metodunda bulduruyorum
        snakeButton = findViewById(R.id.snakeGame);
        info = findViewById(R.id.info);
        context = this;

        main.StudyTimer(context);

        snakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(main.getMinute(context) == 2){
                    Intent snakeGame = new Intent(MainActivity.this,SnakeGameActivity.class);
                    startActivity(snakeGame);
                    main.SnakeTimer(context);
                    snakeButton.setImageResource(R.drawable.snake_noti);
                }else{
                    info.setVisibility(View.VISIBLE);
                    snakeButton.setImageResource(R.drawable.snake);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            info.setVisibility(View.INVISIBLE);
                        }
                    },2000);
                }
            }
        });

    }

    public void playClick(View view) { //butonlarimda onClick oldugundan dolayi click metodlarini olusturuyorum.
        Intent gameScene = new Intent(this,GameActivity.class);
        startActivity(gameScene);
    }

}