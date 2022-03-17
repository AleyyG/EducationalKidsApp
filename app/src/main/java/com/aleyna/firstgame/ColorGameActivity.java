package com.aleyna.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Random;

public class ColorGameActivity extends AppCompatActivity {
    ImageButton colorButton,nextButton; //layoutda bulunan nesnelerimi buraya tantmam gerekiyor o sebeple butonlrimi tanimliyorum
    ImageButton backGameScene; //geri gelme butonumu tanimliyorum.
    Random random = new Random(); //random classindan bir random uretiyorum
    MediaPlayer mediaPlayer; // ses ekleyecegim icin mediaplayer tanimliyorum

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_game);
        backGameScene = findViewById(R.id.backButton); //geri tusuna bastiginda gameScene'e donebilmesi icin backButtonu id ile buldurdum.
        colorButton = findViewById(R.id.colorButton); //kartlarin ustune bastiginda kartlari degisebilmesi icin colorButtonu id ile buldurdum.
        nextButton = findViewById(R.id.next_button);
        mediaPlayer = MediaPlayer.create(this,R.raw.blue_sound); // seslerimin calisabilmesi icin bir mediaplayer olusturdum.
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
            } //burada switch case de surekli yazmak yerine renk butonuna tikladiginda mediaPlayeri oynatmayi calistiriyorum
            //oncesinde RandomCards metodum calistigindan dolayi calistiginda oynamasi gereken track ayarlanmis oluyor.
        });
        backGameScene.setOnClickListener(new View.OnClickListener() { //game menusune donus butonu
            @Override
            public void onClick(View view) {
                Intent gameScene = new Intent(ColorGameActivity.this,GameActivity.class); //yeni bir sahne gecisi olusturuyorum, bu sahneden gameActivity sahnesine gececek
                startActivity((gameScene)); //sonrada olusturdugum bu ıntenti baslatiyorum
            }
        });
    }
    public void RandomCards(int number, ImageButton colorButton){ //bu metotta kartlarin random olarak renklerinin degismesini ve seslerini ayarliyoruz.
        switch (number){ //numarayi kontrol ediyor
            case 1: colorButton.setImageResource(R.drawable.black); //1 oldugunda colorButtonun imageini siyah olan image ile degistir.
            mediaPlayer.reset(); //mediaplayer i resetle
            mediaPlayer = MediaPlayer.create(this,R.raw.black_sound); //mediaplyerde black sesini olustur (burasi daha duzgun yazilabilir?)
                    break;
            case 2: colorButton.setImageResource(R.drawable.brown);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.brown_sound);
                    break;
            case 3: colorButton.setImageResource(R.drawable.green);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.green_sound);
                    break;
            case 4: colorButton.setImageResource(R.drawable.grey);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.grey_sound);
                    break;
            case 5: colorButton.setImageResource(R.drawable.orange);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.orange_sound);
                    break;
            case 6: colorButton.setImageResource(R.drawable.pink);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.pink_sound);
                    break;
            case 7: colorButton.setImageResource(R.drawable.purple);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.purple_sound);
                    break;
            case 8: colorButton.setImageResource(R.drawable.red);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.red_sound);
                    break;
            case 9: colorButton.setImageResource(R.drawable.white);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.white_sound);
                    break;
            case 10: colorButton.setImageResource(R.drawable.yellow);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.yellow_sound);
                    break;
            case 0:
            default: colorButton.setImageResource(R.drawable.blue);
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this,R.raw.blue_sound);
                    break;
        }
    }
}