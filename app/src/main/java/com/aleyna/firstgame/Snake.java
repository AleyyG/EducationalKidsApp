package com.aleyna.firstgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Snake {
    int snakeX = 0;
    int snakeY = 0;
    int xSpeed = 1;
    int ySpeed = 0;
    int snakeScale = 50;

    static final Paint snakePaint = new Paint();

    final int screenWidth;
    final int screenHeight;
    public Snake (int width, int height){
        this.screenWidth = width;
        this.screenHeight = height;

        snakePaint.setColor(Color.rgb(4,25,12));
    }
    void setDirection(int xDir, int yDir){
        this.xSpeed = xDir;
        this.ySpeed = yDir;
    }

    public void update() {
        snakeX += this.xSpeed*snakeScale;
        snakeY += this.ySpeed*snakeScale;
        if(snakeY<0)    snakeY = 0;
        if(snakeX<0)    snakeX = 0;
        if(snakeY>screenHeight-snakeScale)  snakeY=screenHeight-snakeScale;
        if(snakeX>screenWidth-snakeScale)   snakeX=screenWidth-snakeScale;
    }

    public void show(Canvas canvas) {
        canvas.drawRect(snakeX,snakeY,snakeX+snakeScale,snakeY+snakeScale,snakePaint);
    }
}
