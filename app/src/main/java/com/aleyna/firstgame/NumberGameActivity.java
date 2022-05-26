package com.aleyna.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.Random;

public class NumberGameActivity extends AppCompatActivity {
    Main main = new Main();
    ImageButton numberButton,nextButton;
    ImageButton backGameScene;
    Random random = new Random();
    MediaPlayer mediaPlayer;


    ArrayList<Integer> numberRes = new ArrayList<>();
    ArrayList<Integer> numberSounds = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_game);

        backGameScene = findViewById(R.id.backButton);
        numberButton = findViewById(R.id.numberButton);
        nextButton = findViewById(R.id.next_button);
        mediaPlayer = MediaPlayer.create(this,R.raw.one_sound);

        final LottieAnimationView background = findViewById(R.id.background);
        main.ChangeBackground(background);
        AddNumberRes();
        AddNumberSounds();

        nextButton.setOnClickListener(new View.OnClickListener() { //next butona bastiginda olmasi gerekenleri yazacagim.
            @Override
            public void onClick(View view) { //onClick metotlari tikladiginda olmasi gerekenlerin gerceklesmesi gereken butonlar.
                int randNumber = random.nextInt(numberRes.size()); // butona her tiklandiginde 0 ile 11 arasinda sayi urettiriyorum
                RandomCards(randNumber,numberButton); // olusturdugum metoda sayiyi ve tıkladigim colorButtonu gonderiyorum
            }
        });
        numberButton.setOnClickListener(new View.OnClickListener() { //renk butonuna tikladiginda
            @Override
            public void onClick(View view) { mediaPlayer.start(); }
        });
        backGameScene.setOnClickListener(new View.OnClickListener() { //game menusune donus butonu
            @Override
            public void onClick(View view) {
                Intent gameScene = new Intent(NumberGameActivity.this,GameActivity.class); //yeni bir sahne gecisi olusturuyorum, bu sahneden gameActivity sahnesine gececek
                startActivity(gameScene); //sonrada olusturdugum bu ıntenti baslatiyorum
            }
        });
    }
    public void RandomCards(int number, ImageButton numberButtonButton){
        numberButton.setImageResource(numberRes.get(number));
        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(this,numberSounds.get(number));
    }

    public void AddNumberRes(){
        numberRes.add(R.drawable.one_number);
        numberRes.add(R.drawable.two_number);
        numberRes.add(R.drawable.three_number);
        numberRes.add(R.drawable.four_number);
        numberRes.add(R.drawable.five_number);
        numberRes.add(R.drawable.six_number);
        numberRes.add(R.drawable.seven_number);
        numberRes.add(R.drawable.eight_number);
        numberRes.add(R.drawable.nine_number);
    }
    public void AddNumberSounds(){
        numberSounds.add(R.raw.one_sound);
        numberSounds.add(R.raw.two_sound);
        numberSounds.add(R.raw.three_sound);
        numberSounds.add(R.raw.four_sound);
        numberSounds.add(R.raw.five_sound);
        numberSounds.add(R.raw.six_sound);
        numberSounds.add(R.raw.seven_sound);
        numberSounds.add(R.raw.eight_sound);
        numberSounds.add(R.raw.nine_sound);
    }

}