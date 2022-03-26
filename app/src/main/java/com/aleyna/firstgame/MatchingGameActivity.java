package com.aleyna.firstgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Random;

public class MatchingGameActivity extends AppCompatActivity {
    Main main = new Main();
    ImageView first_shape,second_shape,third_shape,fourth_shape;
    ImageView first_match,second_match,third_match,fourth_match;
    LottieAnimationView confetti, background;
    ImageButton nextButton,backButton;
    Random random = new Random();
    MediaPlayer confettiSound,clapSound;
    int winCount = 0;
    float xPos = 0,yPos = 0;
    float[] xPositions = new float[4];
    float[] yPositions = new float[4];

    @SuppressLint({"ClickableViewAccessibility"})
    //touch mekanikleri için ekletmeyi zorunlu tuttu?
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_game);
        confetti = findViewById(R.id.confetti);
        background = findViewById(R.id.background);
        nextButton = findViewById(R.id.next_button);
        backButton = findViewById(R.id.backButton);
        confettiSound = MediaPlayer.create(this,R.raw.confetti);
        clapSound = MediaPlayer.create(this,R.raw.clap);
        first_shape = findViewById(R.id.first_shape);
        second_shape = findViewById(R.id.second_shape);
        third_shape = findViewById(R.id.third_shape);
        fourth_shape = findViewById(R.id.fourth_shape);
        first_match = findViewById(R.id.first_match);
        second_match = findViewById(R.id.second_match);
        third_match = findViewById(R.id.third_match);
        fourth_match = findViewById(R.id.fourth_match);
        nextButton.setEnabled(false);
        RandomShapes(first_shape,first_match);
        RandomShapes(second_shape,second_match);
        RandomShapes(third_shape,third_match);
        RandomShapes(fourth_shape,fourth_match);
        main.ChangeBackground(background);
        MoveObjects(first_shape,first_match);
        MoveObjects(second_shape,second_match);
        MoveObjects(third_shape,third_match);
        MoveObjects(fourth_shape,fourth_match);

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
                ResetPositions(first_shape,second_shape,third_shape,fourth_shape);
                RandomShapes(first_shape,first_match);
                RandomShapes(second_shape,second_match);
                RandomShapes(third_shape,third_match);
                RandomShapes(fourth_shape,fourth_match);
                MoveObjects(first_shape,first_match);
                MoveObjects(second_shape,second_match);
                MoveObjects(third_shape,third_match);
                MoveObjects(fourth_shape,fourth_match);
                nextButton.setAlpha(0f);

            }
        });
    }
    /*
    Asagidaki metot su ise yaramaktadir : Uygulama baslatildiginda sahneler arasi gecisler oldugunda eger bu sahneye
    giris yapilirsa yani focus artik bu sahne olursa metodun if(hasFocus) kismi calisir.
    Sahne burasi oldugunda sekillerin pozisyonlarinin yerlerini aldiriyorum. Bunu onCreate icerisinde yaptiramiyorum.
    Sebebi ise layout uzerindeki objeler daha tam olarak yerlerini almamis oluyorlar. Bu islem tamamlanamadigindan dolayi
    tum objelerin pozisyon degerleri 0,0 oluyor.
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) {
            SavePositions(first_shape,second_shape,third_shape,fourth_shape);
        }
    }
    /*
    bu metotta ekran uzerindeki 4 seklin pozisyon degerlerini kaydettiriyorum cunku yerleri oyun sonrasinda degisecek ve
    tekrar basa dondugu zaman ayni yerlerinde olmasini istiyorum.
     */
    public void SavePositions(@NonNull ImageView first, @NonNull ImageView second, @NonNull ImageView third, @NonNull ImageView fourth){
        xPositions[0] = first.getX();
        xPositions[1] = second.getX();
        xPositions[2] = third.getX();
        xPositions[3] = fourth.getX();
        yPositions[0] = first.getY();
        yPositions[1] = second.getY();
        yPositions[2] = third.getY();
        yPositions[3] = fourth.getY();
    }
    /*
    objelerin hepsi dogru yerlerine yerlestikten sonra objelerin yerlerinin degisebilmesi icin bu metodu yazdim.
    Obejeler dogru yerlestikten sonra tum objeler ilk yerlerine yerlesecekler. Ayni zamanda objeler tekrar hareket edemesinler
    diye enable false yapmistim. Yenilendiklerinde hareket edebilmeleri icin enable true yapiyorum.
     */
    public void ResetPositions(@NonNull ImageView first, @NonNull ImageView second, @NonNull ImageView third, @NonNull ImageView fourth){
        first.setX(xPositions[0]);
        first.setY(yPositions[0]);
        second.setX(xPositions[1]);
        second.setY(yPositions[1]);
        third.setX(xPositions[2]);
        third.setY(yPositions[2]);
        fourth.setX(xPositions[3]);
        fourth.setY(yPositions[3]);
        first.setEnabled(true);
        second.setEnabled(true);
        third.setEnabled(true);
        fourth.setEnabled(true);
    }

    /*
    bu metodu dokunma islemleri icin olusturdum. Dokundugum objenin tasinmasini sagliyorum ve objeden elini cektiginde
    objenin eslesmesi gereken yerde olup olmadigini kontrol ettiriyorum.
     */
    @SuppressLint("ClickableViewAccessibility") //touch mekanikleri için ekletmeyi zorunlu tuttu?
    public void MoveObjects(@NonNull ImageView imageView, @NonNull ImageView match){
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
                    case MotionEvent.ACTION_UP: //kullanici objeden parmagini cektiginde calisacak
                        if(imageView == first_shape){
                            CheckShapes(imageView,first_match);
                        }
                        else if(imageView == second_shape){
                            CheckShapes(imageView,second_match);
                        }
                        else if(imageView == third_shape){
                            CheckShapes(imageView,third_match);
                        }
                        else if(imageView == fourth_shape){
                            CheckShapes(imageView,fourth_match);
                        }
                        break;
                }
                return true;
            }
        });
    }
    /*
    bu metot sekillerin yerlerinin dogru yerde olup olmadigini kontrol ediyor. Gecebilmesi icin tam ustune gelmesin diye de
    belirli degerler araliginda o objenin ustunde kaldigini kabul etmesini sagladim. Yani hata payi koydum gibi bir sey.
     */
    public void CheckShapes(@NonNull ImageView shape, @NonNull ImageView match){
        float matchDistanceX = shape.getX() - match.getX();
        float matchDistanceY = shape.getY() - match.getY();
        int absX = Math.abs(Math.round(matchDistanceX / 10) * 10);
        int absY = Math.abs(Math.round(matchDistanceY/10)*10);
        if((absX <=10f && absX >=0f) ||(absY<=10f && absY>=0f)){
            shape.setX(match.getX());
            shape.setY(match.getY());
            shape.setEnabled(false);
            winCount++;
        }
        if(winCount == 4){
            winCount = 0;
            confetti.setAlpha(1f);
            confetti.playAnimation();
            confettiSound.start();
            clapSound.start();
            nextButton.setAlpha(1f);
            nextButton.setEnabled(true);
        }
    }
    /*
    Burada da Sekilleri ve match olmasi gereken sekilleri random olarak urettiriyorum ama bu kodu sonradan
    degistirmeyi dusunuyorum. Cunku pek icime sinmedi
     */
    public void RandomShapes(ImageView imageView,ImageView matchImage){
        int number = random.nextInt(24);
        switch (number){
            case 0: imageView.setImageResource(R.drawable.fill_halfpyramid);
            matchImage.setImageResource(R.drawable.halfpyramid);
            break;
            case 1: imageView.setImageResource(R.drawable.fill_hyperboloid);
            matchImage.setImageResource(R.drawable.hyperboloid);
            break;
            case 2:imageView.setImageResource(R.drawable.fill_circle);
            matchImage.setImageResource(R.drawable.circle);
            break;
            case 3: imageView.setImageResource(R.drawable.fill_cone);
            matchImage.setImageResource(R.drawable.cone);
            break;
            case 4: imageView.setImageResource(R.drawable.fill_cubes);
            matchImage.setImageResource(R.drawable.cubes);
            break;
            case 5:imageView.setImageResource(R.drawable.fill_diamond);
            matchImage.setImageResource(R.drawable.diamond);
            break;
            case 6: imageView.setImageResource(R.drawable.fill_halfcylinder);
            matchImage.setImageResource(R.drawable.halfcylinder);
            break;
            case 7: imageView.setImageResource(R.drawable.fill_heptagon);
            matchImage.setImageResource(R.drawable.heptagon);
            break;
            case 8: imageView.setImageResource(R.drawable.fill_hexagon);
            matchImage.setImageResource(R.drawable.hexagon);
            break;
            case 9: imageView.setImageResource(R.drawable.fill_icosahedron);
            matchImage.setImageResource(R.drawable.icosahedron);
            break;
            case 10: imageView.setImageResource(R.drawable.fill_nonagon);
            matchImage.setImageResource(R.drawable.nonagon);
            break;
            case 11: imageView.setImageResource(R.drawable.fill_octagon);
            matchImage.setImageResource(R.drawable.octagon);
            break;
            case 12: imageView.setImageResource(R.drawable.fill_octahedron);
            matchImage.setImageResource(R.drawable.octahedron);
            break;
            case 13: imageView.setImageResource(R.drawable.fill_parallelepiped);
            matchImage.setImageResource(R.drawable.parallelepiped);
            break;
            case 14: imageView.setImageResource(R.drawable.fill_pentagon);
            matchImage.setImageResource(R.drawable.pentagon);
            break;
            case 15:imageView.setImageResource(R.drawable.fill_prism);
            matchImage.setImageResource(R.drawable.prism);
            break;
            case 16: imageView.setImageResource(R.drawable.fill_quartercircle);
            matchImage.setImageResource(R.drawable.quartercircle);
            break;
            case 17: imageView.setImageResource(R.drawable.fill_rectangle);
            matchImage.setImageResource(R.drawable.rectangle);
            break;
            case 18: imageView.setImageResource(R.drawable.fill_sphere);
            matchImage.setImageResource(R.drawable.sphere);
            break;
            case 19: imageView.setImageResource(R.drawable.fill_square);
            matchImage.setImageResource(R.drawable.square);
            break;
            case 20: imageView.setImageResource(R.drawable.fill_star);
            matchImage.setImageResource(R.drawable.star);
            break;
            case 21: imageView.setImageResource(R.drawable.fill_torus);
            matchImage.setImageResource(R.drawable.torus);
            break;
            case 22: imageView.setImageResource(R.drawable.fill_trefoil);
            matchImage.setImageResource(R.drawable.trefoil);
            break;
            case 23: imageView.setImageResource(R.drawable.fill_triangle);
            matchImage.setImageResource(R.drawable.triangle);
            break;

        }
    }

}