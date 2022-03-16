package com.aleyna.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MathGameActivity extends AppCompatActivity {
    boolean isFront = false;
    ImageView card_front;
    ImageView card_back;
    Button flipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_game);

        flipButton = findViewById(R.id.flip_button);
        card_back = findViewById(R.id.back_card);
        card_front = findViewById(R.id.front_card);
        AnimatorSet front_anim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.front_animator);
        AnimatorSet back_anim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.back_animator);

        flipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFront){
                    float scale = getApplicationContext().getResources().getDisplayMetrics().density;
                    card_front.setCameraDistance(8000*scale);
                    card_back.setCameraDistance(8000*scale);
                    front_anim.setTarget(card_front);
                    back_anim.setTarget(card_back);
                    front_anim.start();
                    back_anim.start();
                    isFront = false;
                }else{
                    float scale = getApplicationContext().getResources().getDisplayMetrics().density;
                    card_front.setCameraDistance(8000*scale);
                    card_back.setCameraDistance(8000*scale);
                    front_anim.setTarget(card_back);
                    back_anim.setTarget(card_front);
                    front_anim.start();
                    back_anim.start();
                    isFront = true;
                }
            }
        });

    }

}