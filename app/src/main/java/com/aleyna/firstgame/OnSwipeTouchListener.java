package com.aleyna.firstgame;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnSwipeTouchListener implements View.OnTouchListener {
    GestureDetector gestureDetector;

    OnSwipeTouchListener(Context context){
        int threshold = 100;
        int velocity_threshold=100;

        GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onDown(MotionEvent e) {

                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float xDiff = e2.getX()-e1.getX();
                float yDiff = e2.getY()-e1.getY();
                try {
                    if(Math.abs(xDiff)>Math.abs(yDiff)){
                        if(Math.abs(xDiff)>threshold && Math.abs(velocityX)>velocity_threshold){
                            if(xDiff > 0){
                                OnSwipeRight();
                            }else{
                                OnSwipeLeft();
                            }
                            return true;
                        }
                    }else{
                        if(Math.abs(yDiff)>threshold && Math.abs(velocityY)>velocity_threshold){
                            if(yDiff > 0){
                                OnSwipeUp();
                            }else{
                                OnSwipeDown();
                            }
                            return true;
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }
        };
        gestureDetector = new GestureDetector(context,listener);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }
    public void OnSwipeRight(){};
    public void OnSwipeLeft(){};
    public void OnSwipeDown(){};
    public void OnSwipeUp(){};
}
