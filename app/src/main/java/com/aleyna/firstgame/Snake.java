package com.aleyna.firstgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake {
    int snakeX = 0;
    int snakeY = 0;
    int xSpeed = 1;
    int ySpeed = 0;

    int snakeScale = 50;

    int total = 0;

    static final Paint snakePaint = new Paint();
    static final Paint foodPaint = new Paint();

    final int screenWidth;
    final int screenHeight;

    Food food;
    List<SnakeBlocks> tail = new ArrayList<>();
    SnakeStatusUpdate snakeStatusUpdate = null;
    boolean isGameOver = false;

    void setSnakeStatusUpdateListener(SnakeStatusUpdate snakeStatusUpdateListener){
        this.snakeStatusUpdate = snakeStatusUpdateListener;
    }

    public Snake (int width, int height){
        this.screenWidth = width;
        this.screenHeight = height;
        snakePaint.setColor(Color.rgb(4,25,12));
        foodPaint.setColor(Color.rgb(227,6,19));
        getFood();
    }
    void getFood(){
        int cols = screenWidth/snakeScale;
        int rows = screenHeight/snakeScale;
        Random random = new Random();
        int foodX = (int) Math.ceil(snakeScale*random.nextInt(cols));
        int foodY = (int)Math.ceil(snakeScale*random.nextInt(rows));
        food = new Food(foodX,foodY,snakeScale);
    }
    void setDirection(int xDir, int yDir){
        this.xSpeed = xDir;
        this.ySpeed = yDir;
    }
    public void eatFood(){
        double distance = Math.sqrt(((food.x-snakeX)*(food.x-snakeX))+((food.y-snakeY)*(food.y-snakeY)));
        if(distance<snakeScale) {
            total++;
            if(snakeStatusUpdate!=null){
                snakeStatusUpdate.onFoodEaten(total);
            }
            getFood();
        }
    }
    void GameOver(){
        isGameOver = true;
        if(snakeStatusUpdate!=null){
            snakeStatusUpdate.onGameOver(total);
        }
        return;
    }
    public boolean snakeDeath(){
        for (SnakeBlocks snakeBlock : tail){
            double distance = Math.sqrt(((snakeBlock.x-snakeX)*(snakeBlock.x-snakeX))+((snakeBlock.y-snakeY)*(snakeBlock.y-snakeY)));
            if(distance<snakeScale){
                GameOver();
                return true;
            }
        }
        return false;
    }
    public void update() {
        if(isGameOver)  return;
        snakeX += this.xSpeed*snakeScale;
        snakeY += this.ySpeed*snakeScale;
        if(snakeDeath()) {
            if(snakeStatusUpdate!=null){
                snakeStatusUpdate.onGameOver(total);
            }
            return;
        }
        eatFood();
        if(total>0 && total == tail.size()) for(int i = 0; i<tail.size()-1;i++) tail.set(i,tail.get(i+1));
        if(total>0 && tail.size()<total) tail.add(new SnakeBlocks(snakeX,snakeY));
        else if(total>0)    tail.set(tail.size()-1,new SnakeBlocks(snakeX,snakeY));
        if(snakeY<0){
            snakeY = 0;
            GameOver();
            return;
        }
        if(snakeX<0){
            snakeX = 0;
            GameOver();
            return;
        }
        if(snakeY>screenHeight-snakeScale){
            snakeY=screenHeight-snakeScale;
            GameOver();
            return;
        }
        if(snakeX>screenWidth-snakeScale){
            snakeX=screenWidth-snakeScale;
            GameOver();
            return;
        }
    }

    public void show(Canvas canvas) {
        food.show(canvas,foodPaint);
        for(int i = 0; i<tail.size();i++)
            canvas.drawRect(tail.get(i).x,tail.get(i).y,tail.get(i).x+snakeScale,tail.get(i).y+snakeScale,snakePaint);
        canvas.drawRect(snakeX,snakeY,snakeX+snakeScale,snakeY+snakeScale,snakePaint);
    }
    static class SnakeBlocks{
        int x;
        int y;

        public SnakeBlocks(int x, int y){
            this.x = x;
            this.y = y;
        }

    }
    interface SnakeStatusUpdate{
        void onFoodEaten(int total);
        void onGameOver(int total);
    }
}

