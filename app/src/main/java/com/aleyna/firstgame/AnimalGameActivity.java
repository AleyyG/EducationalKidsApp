package com.aleyna.firstgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class AnimalGameActivity extends AppCompatActivity {
    Main main = new Main();

    ImageButton backGameScene,animalButton,nextButton;
    Random random = new Random();
    MediaPlayer mediaPlayer;

    ArrayList<Integer> animalRes = new ArrayList<>();
    ArrayList<Integer > animalSounds = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_game);;

        backGameScene = findViewById(R.id.backButton);
        animalButton = findViewById(R.id.animalButton);
        nextButton = findViewById(R.id.next_button);
        mediaPlayer = MediaPlayer.create(this,R.raw.butterfly_sound);
        final  LottieAnimationView background = findViewById(R.id.background);
        main.ChangeBackground(background);

        AddAnimalRes();
        AddAnimalSounds();
        //next butona bastiginda olmasi gerekenleri yazacagim.
        nextButton.setOnClickListener(view -> { //onClick metotlari tikladiginda olmasi gerekenlerin gerceklesmesi gereken butonlar.
            int randNumber = random.nextInt(animalRes.size());
            RandomCards(randNumber,animalButton);
        });
        //game menusune donus butonu
        backGameScene.setOnClickListener(view -> {
            Intent gameScene = new Intent(AnimalGameActivity.this,GameActivity.class); //yeni bir sahne gecisi olusturuyorum, bu sahneden gameActivity sahnesine gececek
            startActivity((gameScene)); //sonrada olusturdugum bu ıntenti baslatiyorum
        });

        animalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });
    }
    public void RandomCards(int number, ImageButton animalButton){

        animalButton.setImageResource(animalRes.get(number));
        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(this,animalSounds.get(number));
    }
    public void AddAnimalRes(){
        animalRes.add(R.drawable.animal_butterfly);
        animalRes.add(R.drawable.animal_cat);
        animalRes.add(R.drawable.animal_cow);
        animalRes.add(R.drawable.animal_deer);
        animalRes.add(R.drawable.animal_dog);
        animalRes.add(R.drawable.animal_fox);
        animalRes.add(R.drawable.animal_giraffe);
        animalRes.add(R.drawable.animal_goat);
        animalRes.add(R.drawable.animal_hen);
        animalRes.add(R.drawable.animal_horse);
        animalRes.add(R.drawable.animal_koala);
        animalRes.add(R.drawable.animal_lion);
        animalRes.add(R.drawable.animal_monkey);
        animalRes.add(R.drawable.animal_mouse);
        animalRes.add(R.drawable.animal_owl);
        animalRes.add(R.drawable.animal_panda);
        animalRes.add(R.drawable.animal_penguin);
        animalRes.add(R.drawable.animal_pig);
        animalRes.add(R.drawable.animal_shark);
        animalRes.add(R.drawable.animal_sheep);
        animalRes.add(R.drawable.animal_snake);
        animalRes.add(R.drawable.animal_unicorn);
        animalRes.add(R.drawable.animal_zebra);
    }
    public void AddAnimalSounds(){
        animalSounds.add(R.raw.butterfly_sound);
        animalSounds.add(R.raw.cat_sound);
        animalSounds.add(R.raw.cow_sound);
        animalSounds.add(R.raw.deer_sound);
        animalSounds.add(R.raw.dog_sound);
        animalSounds.add(R.raw.fox_sound);
        animalSounds.add(R.raw.giraffe_sound);
        animalSounds.add(R.raw.goat_sound);
        animalSounds.add(R.raw.hen_sound);
        animalSounds.add(R.raw.horse_sound);
        animalSounds.add(R.raw.koala_sound);
        animalSounds.add(R.raw.lion_sound);
        animalSounds.add(R.raw.monkey_sound);
        animalSounds.add(R.raw.mouse_sound);
        animalSounds.add(R.raw.owl_sound);
        animalSounds.add(R.raw.panda_sound);
        animalSounds.add(R.raw.penguin_sound);
        animalSounds.add(R.raw.pig_sound);
        animalSounds.add(R.raw.shark_sound);
        animalSounds.add(R.raw.sheep_sound);
        animalSounds.add(R.raw.snake_sound);
        animalSounds.add(R.raw.unicorn_sound);
        animalSounds.add(R.raw.zebra_sound);
    }
}