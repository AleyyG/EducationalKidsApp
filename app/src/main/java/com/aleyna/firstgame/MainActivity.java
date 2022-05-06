package com.aleyna.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton playButton,snakeButton; //design ekraninda olan buttonlarimi java uzerinde tanimliyorum
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = findViewById(R.id.playButton); //butonumun yerini kod uzerinden acilis metodunda bulduruyorum
        snakeButton = findViewById(R.id.snakeGame);

        snakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent snakeGame = new Intent(MainActivity.this,SnakeGameActivity.class);
                startActivity(snakeGame);
            }
        });

    }

    public void playClick(View view) { //butonlarimda onClick oldugundan dolayi click metodlarini olusturuyorum.
        Intent gameScene = new Intent(this,GameActivity.class);
        startActivity(gameScene);
    }

}