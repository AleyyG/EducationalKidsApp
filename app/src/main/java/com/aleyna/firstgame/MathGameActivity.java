package com.aleyna.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MathGameActivity extends AppCompatActivity {
    private boolean isFront = false;
    private ImageView card_front;
    private ImageView card_back;
    private Button flipButton;
    private TextView first_number,second_number,process,processName,equals,question;
    private EditText guess;
    private Handler handler = new Handler(); //bunu animasyonla uyumlu olabilmesi icin biraz bekletmeme gerektiginden olusturdum
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_game);

        findOnScene();
        AnimatorSet front_anim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.front_animator);
        AnimatorSet back_anim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.back_animator);

        flipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFront){ //eger onu donukse
                    float scale = getApplicationContext().getResources().getDisplayMetrics().density;
                    card_front.setCameraDistance(8000*scale); //goruntunun daha guzel olabilmesi icin kamerayi uzaklastiriyorum
                    card_back.setCameraDistance(8000*scale); //burada da ayarladigim deger ile kamerayi uzaklastiriyorum
                    front_anim.setTarget(card_front); //on animasyonunun hangi kartta oynamasi gerektigini tanimliyorum
                    back_anim.setTarget(card_back); //arka animasyonunun hangi kartta oynamasi gerektigini tanimliyorum
                    front_anim.start(); //animasyonlari baslatiyorum
                    back_anim.start();
                    closeVisible(); //onu donukse arkaya cevirecegemizdden dolayı gorunurlukleri kapatmam gerekiyor
                    isFront = false; //onu donuk mu boolean degerini artik arkasi donuk olacagindan false yapiyorum
                }else{
                    float scale = getApplicationContext().getResources().getDisplayMetrics().density;
                    card_front.setCameraDistance(8000*scale);
                    card_back.setCameraDistance(8000*scale);
                    front_anim.setTarget(card_back); //on animasyonu arkasi donuk oldugundan dolayı bu sefer arka kartta oynuyor
                    back_anim.setTarget(card_front);
                    front_anim.start();
                    back_anim.start();
                    numberGenerator();
                    openVisible(); //gorunurlugu aciyorum
                    isFront = true; //artik onu donuk olacagindan dolayi isFront true olmalidir.
                }
            }
        });

    }
    public void numberGenerator(){ //sayilari random olarak urettik
        int numberFirst = random.nextInt(21);
        int numberSecond = random.nextInt(21);
        first_number.setText(Integer.toString(numberFirst)); //textlerde bu random sayilari gosterdik
        second_number.setText(Integer.toString(numberSecond));
        /* sonrasinda yapmam gerekenler
        * islemi random olarak atayacagim
        * kullanicinin girdigi degeri aldiktan sonra kontrol ettirecegim
        * eger islem dogru olursa particle efect cikacak ve alkis sesi cikabilir
        * kontrol etmeesi icin de bir buton koydurmam gerekiyor
        * eger yanlissa yanlis oldugunu belirten bir ses koyabilirim.
        * */
    }
    public void findOnScene() //daha duzenli dursunlar diye buldurma islemlerini burada yaptirdim.
    {
        first_number = findViewById(R.id.first_number);
        second_number=findViewById(R.id.second_number);
        process =findViewById(R.id.process);
        processName = findViewById(R.id.processName);
        equals = findViewById(R.id.equals);
        question = findViewById(R.id.question);
        flipButton = findViewById(R.id.flip_button);
        card_back = findViewById(R.id.back_card);
        card_front = findViewById(R.id.front_card);
        guess = findViewById(R.id.guess);
    }
    public void closeVisible() //kartin arka kisminda iken bu yazilarin gozukmemesi gerekiyor
    {
        first_number.setAlpha(0f);
        second_number.setAlpha(0f);
        processName.setAlpha(0f);
        process.setAlpha(0f);
        equals.setAlpha(0f);
        question.setAlpha(0f);
        guess.setAlpha(0f);
    }
    public void openVisible(){ //kartin on kismi acik iken bu yazilarin animasyondan sonra gozukmesi gerekiyor
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {  //bu kisimda kart on yuze dondugunden dolayi gorunurluklerini ayarliyorum
                first_number.setAlpha(1f);
                second_number.setAlpha(1f);
                process.setAlpha(1f);
                processName.setAlpha(1f);
                equals.setAlpha(1f);
                question.setAlpha(1f);
                guess.setAlpha(1f);
            }
        },750);//bu kadar bekledikten sonra run kısmını calistiriyor.

    }
}