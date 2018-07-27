package com.example.slashtogglebutton;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Karthik Iyer on 27-07-2018.
 */

public class SlashToggleButton extends View {

    private Bitmap iconBitmap;
    private boolean toggleState = false;
    private Bitmap mBitmap;
    private Paint mPaint;
    private Integer w,h;
    int a = 0, b = 0,init = 1;
    Handler handler = new Handler();

    SlashToggleButton(Context context, AttributeSet attrs){
        super(context,attrs);
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
    }
    SlashToggleButton(Context context){
        super(context);
        mPaint = new Paint();
    }

    protected void onDraw(Canvas canvas){

        if((w == null && h == null) || (w == 0 && h == 0)){
//         w = this.getMeasuredWidth();
//         h = this.getMeasuredHeight();
//            w = this.getWidth();
//            h = this.getHeight();
//         if(w == 0 && h == 0){
             w = iconBitmap.getWidth();
             h = iconBitmap.getHeight();
            Log.d("Width Height: ",w+" "+h);
//         }
        }

        if(true){
            if(!toggleState){
                if(iconBitmap != null)
                    drawFalseToggleState();
            }else {
                if(iconBitmap != null)
                    drawTrueToggleState();
            }
        }

        if(mBitmap!=null)
        canvas.drawBitmap(mBitmap,0,0,mPaint);

        super.onDraw(canvas);

    }

    private void refresh(){

        if(!toggleState){
            if(iconBitmap != null)
                drawFalseToggleState();
        }else {
            if(iconBitmap != null)
                drawTrueToggleState();
        }

    }

    private void toggle(){
        toggleState = !toggleState;
        invalidate();
    }

    public boolean onTouchEvent(MotionEvent event){

        float x = event.getX();
        float y = event.getY();
        int c[] = new int[2];
        getLocationOnScreen(c);
//        x-=c[0];
//        y-=c[1];
        Log.d("center",c[0]+" "+c[1]);

        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                if(x>0 && x<w && y>0 && y<h) {
                    toggle();
                    refresh();
                    invalidate();
                }
                break;
        }
        return true;
    }

    public void setIconBitmap(Bitmap bitmap){
        iconBitmap = bitmap;
        init = 0;
        invalidate();
    }

    private void drawTrueToggleState(){
        mBitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mBitmap);
        int centerX = (canvas.getWidth() - iconBitmap.getWidth())/2;
        int centerY = (canvas.getHeight() - iconBitmap.getHeight())/2;
        canvas.drawBitmap(iconBitmap,centerX,centerY,mPaint);
        invalidate();
    }

    private void drawFalseToggleState(){
        mBitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mBitmap);
        Paint paintAlpha = new Paint();
        paintAlpha.setAlpha(120);
        int centerX = (canvas.getWidth() - iconBitmap.getWidth())/2;
        int centerY = (canvas.getHeight() - iconBitmap.getHeight())/2;
        canvas.drawBitmap(iconBitmap,centerX,centerY,paintAlpha);
        paintAlpha.setColor(Color.BLACK);
        invalidate();
    }

    public boolean getToggleState(){
        return toggleState;
    }



}
