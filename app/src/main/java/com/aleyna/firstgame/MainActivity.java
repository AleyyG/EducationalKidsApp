package com.aleyna.firstgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Main main = new Main();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    TextView info,settingsText,highScoreText;
    ImageButton playButton,snakeButton,settingsOk,settingsButton; //design ekraninda olan buttonlarimi java uzerinde tanimliyorum
    ImageView settingsPanel;
    SeekBar musicSeek;
    AudioManager audioManager;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("com.aleyna.firstgame",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        musicSeek = findViewById(R.id.music_seek);
        musicSeek.setMax(maxVolume);
        musicSeek.setProgress(currentVolume);

        highScoreText = findViewById(R.id.highScore_text);
        playButton = findViewById(R.id.playButton); //butonumun yerini kod uzerinden acilis metodunda bulduruyorum
        snakeButton = findViewById(R.id.snakeGame);
        settingsButton = findViewById(R.id.settings);
        info = findViewById(R.id.info);
        settingsText = findViewById(R.id.settings_text);
        settingsOk = findViewById(R.id.settings_ok);
        settingsPanel = findViewById(R.id.settings_panel);

        context = this;
        main.StudyTimer(context); //arka planda gecirilen sureyi olcmek icin

        //editor.remove("score").commit(); //burasi yorum satirina alinacak

        int highScore = sharedPreferences.getInt("score",0);
        if(highScore==0) highScoreText.setVisibility(View.INVISIBLE);
        else{
            highScoreText.setText("High Score: " + highScore);
            highScoreText.setVisibility(View.VISIBLE);
        }

        Intent service = new Intent(MainActivity.this,BackgroundSoundService.class);
        startService(service); //arka plan muzigi icin

        InvisibleSettings(); //settingPanel kapalı

        if(main.getMinute(context)==2) snakeButton.setImageResource(R.drawable.snake_noti);
        else snakeButton.setImageResource(R.drawable.snake);
        snakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(main.getMinute(context) == 2){
                    Intent snakeGame = new Intent(MainActivity.this,SnakeGameActivity.class);
                    startActivity(snakeGame);
                    main.SnakeTimer(context);
                }else{
                    info.setVisibility(View.VISIBLE);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            info.setVisibility(View.INVISIBLE);
                        }
                    },2000);
                }
            }
        });

        musicSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VisibleSettings();
            }
        });

        settingsOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InvisibleSettings();
            }
        });


    }

    public void VisibleSettings(){
        settingsPanel.setVisibility(View.VISIBLE);
        settingsText.setVisibility(View.VISIBLE);
        settingsOk.setVisibility(View.VISIBLE);
        settingsOk.setEnabled(true);
        musicSeek.setVisibility(View.VISIBLE);
        musicSeek.setEnabled(true);
        playButton.setEnabled(false);
        playButton.setVisibility(View.INVISIBLE);
        snakeButton.setEnabled(false);
        snakeButton.setVisibility(View.INVISIBLE);
        settingsButton.setEnabled(false);
        settingsButton.setVisibility(View.INVISIBLE);
    }
    public void InvisibleSettings(){
        settingsPanel.setVisibility(View.INVISIBLE);
        settingsText.setVisibility(View.INVISIBLE);
        settingsOk.setVisibility(View.INVISIBLE);
        settingsOk.setEnabled(false);
        musicSeek.setVisibility(View.INVISIBLE);
        musicSeek.setEnabled(false);
        playButton.setEnabled(true);
        playButton.setVisibility(View.VISIBLE);
        snakeButton.setEnabled(true);
        snakeButton.setVisibility(View.VISIBLE);
        settingsButton.setEnabled(true);
        settingsButton.setVisibility(View.VISIBLE);
    }

    public void playClick(View view) { //butonlarimda onClick oldugundan dolayi click metodlarini olusturuyorum.
        Intent gameScene = new Intent(this,GameActivity.class);
        startActivity(gameScene);
    }


    @Override
    protected void onStart() {
        super.onStart();

        Intent service = new Intent(MainActivity.this,BackgroundSoundService.class);
        startService(service);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent service = new Intent(MainActivity.this,BackgroundSoundService.class);
        stopService(service);
    }


}