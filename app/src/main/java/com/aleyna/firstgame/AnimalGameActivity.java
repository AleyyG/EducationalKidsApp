package com.aleyna.firstgame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class AnimalGameActivity extends AppCompatActivity {
    Main main = new Main();
    ImageButton backGameScene,animalButton,nextButton;
    Random random = new Random();
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_game);
        backGameScene = findViewById(R.id.backButton);
        animalButton = findViewById(R.id.animalButton);
        nextButton = findViewById(R.id.next_button);
        mediaPlayer = MediaPlayer.create(this,R.raw.butterfly_sound);
        final  LottieAnimationView background = findViewById(R.id.background);
        main.ChangeBackground(background);
        //next butona bastiginda olmasi gerekenleri yazacagim.
        nextButton.setOnClickListener(view -> { //onClick metotlari tikladiginda olmasi gerekenlerin gerceklesmesi gereken butonlar.
            int randNumber = random.nextInt(23); // butona her tiklandiginde 0 ile 23 arasinda sayi urettiriyorum
            RandomCards(randNumber,animalButton); // olusturdugum metoda sayiyi ve tıkladigim animalButtonu gonderiyorum
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
        switch (number){
            case 0: animalButton.setImageResource(R.drawable.animal_butterfly); //1 oldugunda animalButtonun imageini butterfly olan image ile degistir.
                mediaPlayer.reset(); //mediaplayer i resetle
                mediaPlayer = MediaPlayer.create(this,R.raw.butterfly_sound); //mediaplyerde black sesini olustur (burasi daha duzgun yazilabilir?)
                break;
            case 1: animalButton.setImageResource(R.drawable.animal_cat);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.cat_sound);
                break;
            case 2: animalButton.setImageResource(R.drawable.animal_cow);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.cow_sound);
            break;
            case 3: animalButton.setImageResource(R.drawable.animal_deer);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.deer_sound);
                break;
            case 4: animalButton.setImageResource(R.drawable.animal_dog);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.dog_sound);
                break;
            case 5: animalButton.setImageResource(R.drawable.animal_fox);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.fox_sound);
                break;
            case 6: animalButton.setImageResource(R.drawable.animal_giraffe);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.giraffe_sound);
                break;
            case 7: animalButton.setImageResource(R.drawable.animal_goat);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.goat_sound);
                break;
            case 8: animalButton.setImageResource(R.drawable.animal_hen);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.hen_sound);
                break;
            case 9: animalButton.setImageResource(R.drawable.animal_horse);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.horse_sound);
                break;
            case 10: animalButton.setImageResource(R.drawable.animal_koala);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.koala_sound);
                break;
            case 11: animalButton.setImageResource(R.drawable.animal_lion);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.lion_sound);
                break;
            case 12: animalButton.setImageResource(R.drawable.animal_monkey);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.monkey_sound);
                break;
            case 13: animalButton.setImageResource(R.drawable.animal_mouse);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.mouse_sound);
                break;
            case 14: animalButton.setImageResource(R.drawable.animal_owl);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.owl_sound);
                break;
            case 15: animalButton.setImageResource(R.drawable.animal_panda);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.panda_sound);
                break;
            case 16: animalButton.setImageResource(R.drawable.animal_penguin);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.penguin_sound);
                break;
            case 17: animalButton.setImageResource(R.drawable.animal_pig);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.pig_sound);
                break;
            case 18: animalButton.setImageResource(R.drawable.animal_shark);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.shark_sound);
                break;
            case 19: animalButton.setImageResource(R.drawable.animal_sheep);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.sheep_sound);
                break;
            case 20: animalButton.setImageResource(R.drawable.animal_snake);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.snake_sound);
                break;
            case 21: animalButton.setImageResource(R.drawable.animal_unicorn);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.unicorn_sound);
                break;
            case 22: animalButton.setImageResource(R.drawable.animal_zebra);
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this,R.raw.zebra_sound);
                break;
        }

    }
}