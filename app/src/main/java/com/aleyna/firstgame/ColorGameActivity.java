package com.aleyna.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;

import com.airbnb.lottie.LottieAnimationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class ColorGameActivity extends AppCompatActivity {
    Main main = new Main();
    ImageButton colorButton,nextButton; //layoutda bulunan nesnelerimi buraya tantmam gerekiyor o sebeple butonlrimi tanimliyorum
    ImageButton backGameScene; //geri gelme butonumu tanimliyorum.
    Random random = new Random(); //random classindan bir random uretiyorum
    MediaPlayer mediaPlayer; // ses ekleyecegim icin mediaplayer tanimliyorum

    ArrayList<Integer> colorRes = new ArrayList<>();
    ArrayList<Integer> colorSounds = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_game);
        backGameScene = findViewById(R.id.backButton); //geri tusuna bastiginda gameScene'e donebilmesi icin backButtonu id ile buldurdum.
        colorButton = findViewById(R.id.colorButton); //kartlarin ustune bastiginda kartlari degisebilmesi icin colorButtonu id ile buldurdum.
        nextButton = findViewById(R.id.next_button);
        mediaPlayer = MediaPlayer.create(this,R.raw.blue_sound); // seslerimin calisabilmesi icin bir mediaplayer olusturdum.

        final LottieAnimationView background = findViewById(R.id.background);
        main.ChangeBackground(background);
        AddColorRes();
        AddColorSounds();

        nextButton.setOnClickListener(new View.OnClickListener() { //next butona bastiginda olmasi gerekenleri yazacagim.
            @Override
            public void onClick(View view) { //onClick metotlari tikladiginda olmasi gerekenlerin gerceklesmesi gereken butonlar.
                int randNumber = random.nextInt(11); // butona her tiklandiginde 0 ile 11 arasinda sayi urettiriyorum
                RandomCards(randNumber,colorButton); // olusturdugum metoda sayiyi ve tıkladigim colorButtonu gonderiyorum
            }
        });
        colorButton.setOnClickListener(new View.OnClickListener() { //renk butonuna tikladiginda
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });
        backGameScene.setOnClickListener(new View.OnClickListener() { //game menusune donus butonu
            @Override
            public void onClick(View view) {
                Intent gameScene = new Intent(ColorGameActivity.this,GameActivity.class); //yeni bir sahne gecisi olusturuyorum, bu sahneden gameActivity sahnesine gececek
                startActivity(gameScene); //sonrada olusturdugum bu ıntenti baslatiyorum
            }
        });
    }
    public void RandomCards(int number, ImageButton colorButton){//bu metotta kartlarin random olarak renklerinin degismesini ve seslerini ayarliyoruz.
        colorButton.setImageResource(colorRes.get(number));
        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(this,colorSounds.get(number));
    }
    public void AddColorRes(){
        colorRes.add(R.drawable.black);
        colorRes.add(R.drawable.brown);
        colorRes.add(R.drawable.green);
        colorRes.add(R.drawable.grey);
        colorRes.add(R.drawable.orange);
        colorRes.add(R.drawable.pink);
        colorRes.add(R.drawable.purple);
        colorRes.add(R.drawable.red);
        colorRes.add(R.drawable.white);
        colorRes.add(R.drawable.yellow);
        colorRes.add(R.drawable.blue);
    }
    public void AddColorSounds(){
        colorSounds.add(R.raw.black_sound);
        colorSounds.add(R.raw.brown_sound);
        colorSounds.add(R.raw.green_sound);
        colorSounds.add(R.raw.grey_sound);
        colorSounds.add(R.raw.orange_sound);
        colorSounds.add(R.raw.pink_sound);
        colorSounds.add(R.raw.purple_sound);
        colorSounds.add(R.raw.red_sound);
        colorSounds.add(R.raw.white_sound);
        colorSounds.add(R.raw.yellow_sound);
        colorSounds.add(R.raw.blue_sound);
    }
}