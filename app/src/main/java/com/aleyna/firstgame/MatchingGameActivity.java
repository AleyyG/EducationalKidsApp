package com.aleyna.firstgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MatchingGameActivity extends AppCompatActivity {
    Main main = new Main();

    ImageView card_one,card_two,card_three,card_four;
    ImageView match_card_one,match_card_two,match_card_three,match_card_four;
    LottieAnimationView confetti, background;
    ImageButton nextButton,backButton;

    Random random = new Random();
    MediaPlayer confettiSound,clapSound;

    float xPos = 0,yPos = 0;

    ArrayList<Integer> images = new ArrayList<>();
    ArrayList<Integer> matchImages = new ArrayList<>();
    ArrayList<ImageView> cards= new ArrayList<>();
    ArrayList<ImageView> matchingCards = new ArrayList<>();
    ArrayList<Integer> randomNumbers = new ArrayList<>();

    int winCount = 0;

    ArrayList<Float> xPositions = new ArrayList<>();
    ArrayList<Float> yPositions = new ArrayList<>();

    boolean savePos;

    @SuppressLint({"ClickableViewAccessibility"}) //touch mekanikleri için ekletmeyi zorunlu tuttu
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_game);
        confetti = findViewById(R.id.confetti);
        FindObjects();

        confettiSound = MediaPlayer.create(this,R.raw.confetti);
        clapSound = MediaPlayer.create(this,R.raw.clap);
        nextButton.setEnabled(false);
        main.ChangeBackground(background);

        AddImages();
        AddMatchImages();
        AddCards();
        AddMatchingCards();
        GetImages();
        for (ImageView x : cards) MoveObjects(x);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backGameScene = new Intent(MatchingGameActivity.this,GameActivity.class);
                startActivity(backGameScene);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetImages();
                for( ImageView x : cards) MoveObjects(x);
                nextButton.setAlpha(0f);
                ResetPositions();
            }
        });
    }

    void GetImages(){
        if(images.size() == 0){
            AddImages();
            AddMatchImages();
        }
        for ( ImageView x : cards) {
            int rnd = random.nextInt(images.size());
            x.setImageResource(images.get(rnd));
            randomNumbers.add(matchImages.get(rnd));
            x.setTag(matchImages.get(rnd));
            images.remove(rnd);
            matchImages.remove(rnd);
        }
        for( ImageView x : matchingCards){
            int rnd = random.nextInt(randomNumbers.size());
            x.setImageResource(randomNumbers.get(rnd));
            x.setTag(randomNumbers.get(rnd));
            randomNumbers.remove(rnd);
        }
    }
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            SavePositions();
        }
    }
    /*
    bu metodu dokunma islemleri icin olusturdum. Dokundugum objenin tasinmasini sagliyorum
     */
    @SuppressLint("ClickableViewAccessibility") //touch mekanikleri için ekletmeyi zorunlu tuttu?
    public void MoveObjects(@NonNull ImageView imageView){
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked()){
                    //kullanici parmagini tasinacak objenin uzerine getirdiginde calisacak
                    case MotionEvent.ACTION_DOWN:
                        xPos = event.getX(); //kullanicidan dokundugu yerdeki x degerini aldik
                        yPos = event.getY(); //kullanicidan dokundugu yerdeki y degerini aldik
                        break;

                    //kullanici objeyi hareket ettirdiginde calisacak
                    case MotionEvent.ACTION_MOVE:
                        float movedX,movedY;
                        movedX = event.getX(); //kullanicidan tasidigi yerdeki x degerini aldik
                        movedY = event.getY(); //kullanicidan tasidigi yerdeki y degerini aldik
                        float distanceX,distanceY;
                        distanceX = movedX-xPos; //objeyi x ekseninde ne kadar hareket ettirdigini bulduk
                        distanceY = movedY-yPos; // objeyi y ekseninde ne kadar hareket ettirdigini bulduk

                        imageView.setX(imageView.getX()+distanceX);//objenin ilk x konumuna tasidigi kadarlik kismi ekledik
                        imageView.setY(imageView.getY()+distanceY); //objenin ilk y konumuna tasidigi kadarlik kismi ekledik.
                        break;

                    //kullanici objeden parmagini cektiginde calisacak
                    case MotionEvent.ACTION_UP:
                        CheckMatches(imageView);
                        if(winCount == 4){
                            nextButton.setAlpha(1f);
                            nextButton.setEnabled(true);
                            confetti.setAlpha(1f);
                            confetti.playAnimation();
                            confettiSound.start();
                            clapSound.start();
                            winCount = 0;
                        }
                        break;
                }
                return true;
            }
        });
    }
    void CheckMatches(ImageView imageView){
    for(ImageView x : matchingCards){
        if(x.getTag().equals(imageView.getTag()))
        {
            float xDistance = imageView.getX()-x.getX();
            float yDistance = imageView.getY()-x.getY();
            float absX = Math.abs(Math.round(xDistance/10)*10);
            float absY = Math.abs(Math.round(yDistance/10)*10);
            if(absX<20f || absY<20f){
                imageView.setEnabled(false);
                imageView.setX(x.getX());
                imageView.setY(x.getY());
                winCount++;
            }else{
                Log.d("denemee","absX: "+ absX);
                Log.d("denemee","absY: "+ absY);
            }
        }
    }
}
    void SavePositions(){
        for (ImageView x : cards){
            xPositions.add(x.getX());
            yPositions.add(x.getY());
        }
    }
    void ResetPositions(){
        for(int i = 0; i<cards.size();i++){
            cards.get(i).setX(xPositions.get(i));
            cards.get(i).setY(yPositions.get(i));
            cards.get(i).setEnabled(true);
        }
    }
    void FindObjects(){
        background = findViewById(R.id.background);
        nextButton = findViewById(R.id.next_button);
        backButton = findViewById(R.id.backButton);
        card_one = findViewById(R.id.card_one);
        card_two = findViewById(R.id.card_two);
        card_three = findViewById(R.id.card_three);
        card_four = findViewById(R.id.card_four);
        match_card_one = findViewById(R.id.match_card_one);
        match_card_two = findViewById(R.id.match_card_two);
        match_card_three = findViewById(R.id.match_card_three);
        match_card_four = findViewById(R.id.match_card_four);
    }
    void AddMatchImages(){
        matchImages.add(R.drawable.circle);
        matchImages.add(R.drawable.cone);
        matchImages.add(R.drawable.cubes);
        matchImages.add(R.drawable.diamond);
        matchImages.add(R.drawable.halfcylinder);
        matchImages.add(R.drawable.halfpyramid);
        matchImages.add(R.drawable.heptagon);
        matchImages.add(R.drawable.hexagon);
        matchImages.add(R.drawable.hyperboloid);
        matchImages.add(R.drawable.icosahedron);
        matchImages.add(R.drawable.nonagon);
        matchImages.add(R.drawable.octagon);
        matchImages.add(R.drawable.octahedron);
        matchImages.add(R.drawable.parallelepiped);
        matchImages.add(R.drawable.pentagon);
        matchImages.add(R.drawable.prism);
        matchImages.add(R.drawable.quartercircle);
        matchImages.add(R.drawable.rectangle);
        matchImages.add(R.drawable.sphere);
        matchImages.add(R.drawable.square);
        matchImages.add(R.drawable.star);
        matchImages.add(R.drawable.torus);
        matchImages.add(R.drawable.trefoil);
        matchImages.add(R.drawable.triangle);
    }
    void AddImages(){
        images.add(R.drawable.fill_circle);
        images.add(R.drawable.fill_cone);
        images.add(R.drawable.fill_cubes);
        images.add(R.drawable.fill_diamond);
        images.add(R.drawable.fill_halfcylinder);
        images.add(R.drawable.fill_halfpyramid);
        images.add(R.drawable.fill_heptagon);
        images.add(R.drawable.fill_hexagon);
        images.add(R.drawable.fill_hyperboloid);
        images.add(R.drawable.fill_icosahedron);
        images.add(R.drawable.fill_nonagon);
        images.add(R.drawable.fill_octagon);
        images.add(R.drawable.fill_octahedron);
        images.add(R.drawable.fill_parallelepiped);
        images.add(R.drawable.fill_pentagon);
        images.add(R.drawable.fill_prism);
        images.add(R.drawable.fill_quartercircle);
        images.add(R.drawable.fill_rectangle);
        images.add(R.drawable.fill_sphere);
        images.add(R.drawable.fill_square);
        images.add(R.drawable.fill_star);
        images.add(R.drawable.fill_torus);
        images.add(R.drawable.fill_trefoil);
        images.add(R.drawable.fill_triangle);
    }
    void AddMatchingCards(){
        matchingCards.add(match_card_one);
        matchingCards.add(match_card_two);
        matchingCards.add(match_card_three);
        matchingCards.add(match_card_four);
    }
    void AddCards(){
        cards.add(card_one);
        cards.add(card_two);
        cards.add(card_three);
        cards.add(card_four);
    }
}