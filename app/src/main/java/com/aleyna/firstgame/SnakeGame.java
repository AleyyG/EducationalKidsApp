package com.aleyna.firstgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.aleyna.firstgame.OnSwipeTouchListener;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SnakeGame extends View {
    static final TextPaint textPaint = new TextPaint();

    Snake snake = null ;

    int screenWidth = 0;
    int screenHeight = 0;

    @SuppressLint("ClickableViewAccessibility")
    void setup(){
        textPaint.setColor(Color.RED);
        textPaint.setStrokeWidth(2f);

        this.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            @Override
            public void OnSwipeUp() {
                super.OnSwipeUp();
                snake.setDirection(0,-1);
            }

            @Override
            public void OnSwipeDown() {
                super.OnSwipeDown();
                snake.setDirection(0,1);
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
    public SnakeGame(Context context) {
        super(context);
        setup();
    }

    public SnakeGame(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.rgb(110,158,124));
        snake.update();
        snake.show(canvas);
    }
    void updateScreen(){
        invalidate();
        //is akisi bittikten sonra goruntuyu tazelemeye yarayan metottur.
        //yani yilan hareket ediyor aslinda fakat goruntuyu tazelemezsek
        //ilk andaki goruntusu kalmaya devam eder ne kadar hareket etse de
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth = w;
        screenHeight = h;
        snake = new Snake(w,h);
    }
}
