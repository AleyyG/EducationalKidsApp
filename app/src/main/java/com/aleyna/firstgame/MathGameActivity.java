package com.aleyna.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MathGameActivity extends AppCompatActivity {
    Main main = new Main();
    private boolean isFront = false;
    private ImageView card_front;
    private ImageView card_back;
    private ImageButton backGameScene,flipButton,checkButton; //bu ksıımlar düzelecek !
    private TextView first_number,second_number,process,processName,equals,question;
    private EditText guess;
    private Handler handler = new Handler(); //bunu animasyonla uyumlu olabilmesi icin biraz bekletmeme gerektiginden olusturdum
    private Random random = new Random();
    int result;
    MediaPlayer confettiSound,clapSound,wrongAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_game);

        findOnScene();

        AnimatorSet front_anim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.front_animator);
        AnimatorSet back_anim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.back_animator);
        final LottieAnimationView background = findViewById(R.id.background);
        main.ChangeBackground(background);

        flipButton = findViewById(R.id.flip_button);

        final LottieAnimationView confetti = findViewById(R.id.confetti);
        confettiSound = MediaPlayer.create(this,R.raw.confetti);
        clapSound = MediaPlayer.create(this,R.raw.clap);
        wrongAnswer = MediaPlayer.create(this,R.raw.wrong_answer);

      backGameScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameScene = new Intent(MathGameActivity.this,GameActivity.class); //yeni bir sahne gecisi olusturuyorum, bu sahneden gameActivity sahnesine gececek
                startActivity((gameScene)); //sonrada olusturdugum bu ıntenti baslatiyorum
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentResult = Integer.parseInt(guess.getText().toString());

                if(result == currentResult){
                    confetti.setAlpha(1f);
                    confetti.playAnimation();
                    confettiSound.start();
                    clapSound.start();
                    checkButton.setEnabled(false);

                }else{
                  wrongAnswer.start();
                }
            }
        }) ;

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
                    guess.setEnabled(false);
                    guess.setText("");

                 //   numberGenerator();
                    closeVisible(); //onu donukse arkaya cevirecegemizdden dolayı gorunurlukleri kapatmam gerekiyor
                    confetti.setAlpha(0f);
                    isFront = false; //onu donuk mu boolean degerini artik arkasi donuk olacagindan false yapiyorum
                }else{
                    float scale = getApplicationContext().getResources().getDisplayMetrics().density;
                    card_front.setCameraDistance(8000*scale);
                    card_back.setCameraDistance(8000*scale);
                    front_anim.setTarget(card_back); //on animasyonu arkasi donuk oldugundan dolayı bu sefer arka kartta oynuyor
                    back_anim.setTarget(card_front);
                    front_anim.start();
                    back_anim.start();
                    guess.setEnabled(true);
                    guess.setText("");
                    checkButton.setEnabled(true);
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
        int processNumber = random.nextInt(4);
        switch(processNumber){
            case 0: process.setText("+");
            result = numberFirst + numberSecond;
            break;
            case 1: process.setText("-");
            while(numberFirst<numberSecond){
                numberFirst = random.nextInt(21);
                numberSecond = random.nextInt(21);
                first_number.setText(Integer.toString(numberFirst));
                second_number.setText(Integer.toString(numberSecond));
                processName.setText("SUBSTRACTION");
            }
            result = numberFirst - numberSecond;
            break;
            case 2: process.setText("/");
            while(numberFirst == 0 || numberSecond == 0 || numberFirst%numberSecond!=0){
                numberFirst = random.nextInt(21);
                numberSecond = random.nextInt(21);
                first_number.setText(Integer.toString(numberFirst));
                second_number.setText(Integer.toString(numberSecond));
                processName.setText("DIVISION");
            }
            result = numberFirst / numberSecond;
            break;
            case 3: process.setText("x");
            numberFirst = random.nextInt(11);
            numberSecond = random.nextInt(11);
            first_number.setText(Integer.toString(numberFirst));
            second_number.setText(Integer.toString(numberSecond));
            processName.setText("MULTIPLICATION");
            result = numberFirst*numberSecond;
            break;
        }
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
        card_back = findViewById(R.id.back_card);
        card_front = findViewById(R.id.front_card);
        guess = findViewById(R.id.guess);
        flipButton = findViewById(R.id.flip_button);
        checkButton = findViewById(R.id.checkButton);
        backGameScene = findViewById(R.id.backButton);
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