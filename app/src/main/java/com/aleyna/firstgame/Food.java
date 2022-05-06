package com.aleyna.firstgame;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Food {
    int x, y, scale;
    public Food(int x, int y, int scale){
        this.x = x;
        this.y = y;
        this.scale = scale;
    }
    void show(Canvas canvas, Paint paint){
        canvas.drawRect(x,y,x+scale,y+scale,paint);
    }
}
