package com.aleyna.firstgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SnakeGame extends View implements Snake.SnakeStatusUpdate {
    GameUpdateListener gameUpdateListener = null;
    Snake snake = null;

    int screenWidth = 0;
    int screenHeight = 0;
void setGameUpdateListener(GameUpdateListener listener){
    this.gameUpdateListener = listener;
}
    @SuppressLint("ClickableViewAccessibility")
    void setup(){
        this.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            @Override
            public void OnSwipeUp() {
                super.OnSwipeUp();
                snake.setDirection(0,1);
            }

            @Override
            public void OnSwipeDown() {
                super.OnSwipeDown();
                snake.setDirection(0,-1);
            }

            @Override
            public void OnSwipeRight() {
                super.OnSwipeRight();
                snake.setDirection(1,0);
            }

            @Override
            public void OnSwipeLeft() {
                super.OnSwipeLeft();
                snake.setDirection(-1,0);
            }
        });
    }
    public SnakeGame(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.rgb(110,158,124));
        if(snake.isGameOver){
            //oyun bittiginde ne olmasi gerektigi
        }else{
            snake.update();
            snake.show(canvas);
        }

    }
    void updateScreen(){
        invalidate();
        //is akisi bittikten sonra goruntuyu tazelemeye yarayan metottur.
        //yani yilan hareket ediyor aslinda fakat goruntuyu tazelemezsek
        //ilk andaki goruntusu kalmaya devam eder ne kadar hareket etse de
    }

    @Override //telefonun width height degerlerini alir.
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth = w;
        screenHeight = h;
        restart();
    }

    @Override
    public void onFoodEaten(int total) {
    if(gameUpdateListener !=null){
        gameUpdateListener.onScoreUpdate(total);
    }
    }

    @Override
    public void onGameOver(int total) {
        if(gameUpdateListener !=null){
            gameUpdateListener.onGameOver(total);
        }
    }

    public void restart() {
        snake = new Snake(screenWidth,screenHeight);
        snake.setSnakeStatusUpdateListener((Snake.SnakeStatusUpdate) this);
    }

    interface GameUpdateListener{
        void onScoreUpdate(int score);
        void onGameOver(int score);
    }
}
