package com.example.slashtogglebutton;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
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
    private Paint mPaint,linePaint;
    private Path linePath;
    private Integer w,h;
    private float length;
    int a = 0, b = 0,initial = 1;
    Handler handler = new Handler();

    SlashToggleButton(Context context, AttributeSet attrs){
        super(context,attrs);
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        linePaint = new Paint();
        linePaint.setStrokeWidth(12);
        linePaint.setColor(Color.BLACK);
        linePaint.setAlpha(120);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,R.styleable.SlashToggleButton,0,0);
        iconBitmap = BitmapFactory.decodeResource(getResources(),typedArray.getResourceId(R.styleable.SlashToggleButton_icon,0));
        initial = 1;
        toggle();
    }
    SlashToggleButton(Context context){
        super(context);
        mPaint = new Paint();
        linePaint = new Paint();
        linePaint.setStrokeWidth(12);
        linePaint.setAlpha(120);
    }

    protected void onDraw(Canvas canvas){

        super.onDraw(canvas);

        if(iconBitmap != null && (initial == 1)) {

            if (mBitmap != null)
                canvas.drawBitmap(mBitmap, 0, 0, mPaint);

            initial = 0;

        }

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
        initial = 1;
        if (!toggleState) {
            if (iconBitmap != null)
                drawFalseToggleState();
        } else {
            if (iconBitmap != null)
                drawTrueToggleState();
        }
    }

    public boolean onTouchEvent(MotionEvent event){

        float x = event.getX();
        float y = event.getY();

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
        toggle();
    }

    private void drawTrueToggleState(){

        if ((w == null && h == null) || (w == 0 && h == 0)) {

            w = iconBitmap.getWidth();
            h = iconBitmap.getHeight();
            Log.d("Width Height: ", w + " " + h);

        }

        mBitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mBitmap);
        int centerX = (canvas.getWidth() - iconBitmap.getWidth())/2;
        int centerY = (canvas.getHeight() - iconBitmap.getHeight())/2;
        canvas.drawBitmap(iconBitmap,centerX,centerY,mPaint);
        invalidate();
    }

    private void drawFalseToggleState(){

        if ((w == null && h == null) || (w == 0 && h == 0)) {

            w = iconBitmap.getWidth();
            h = iconBitmap.getHeight();
            Log.d("Width Height: ", w + " " + h);

        }

        mBitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mBitmap);
        Paint paintAlpha = new Paint();
        paintAlpha.setAlpha(120);
        int centerX = (canvas.getWidth() - iconBitmap.getWidth())/2;
        int centerY = (canvas.getHeight() - iconBitmap.getHeight())/2;
        canvas.drawBitmap(iconBitmap,centerX,centerY,paintAlpha);
        paintAlpha.setStrokeWidth(12);
        paintAlpha.setStrokeCap(Paint.Cap.ROUND);
//        if(a<=w && b<=h){
//            canvas.drawLine(0,0,w,h,paintAlpha);
//            a++;b+=(h/w);
//            initial = 1;
//            postInvalidateDelayed(15);
//        }

        linePath = new Path();
        linePath.moveTo(0,0);
        linePath.lineTo(w,h);
        drawLine();
        PathMeasure measure = new PathMeasure(linePath,false);
        length = measure.getLength();
        float[] intervals = new float[]{length,length};
        ObjectAnimator animator = ObjectAnimator.ofFloat(SlashToggleButton.this,"phase",1.0f,0.0f);
        animator.setDuration(300);
        animator.start();
    }

    private void drawLine(){
        Canvas canvas = new Canvas(mBitmap);
        canvas.drawPath(linePath,new Paint());
        initial = 1;
        invalidate();
    }

    public void setPhase(float phase){
        linePaint.setPathEffect(createPathEffect(length,phase,0.0f));
        Log.d("TAG","HERE");
        drawLine();
    }

    private static PathEffect createPathEffect(float pathLength, float phase, float offset)
    {
        return new DashPathEffect(new float[] { pathLength, pathLength },
                Math.max(phase * pathLength, offset));
    }

    public boolean getToggleState(){
        return toggleState;
    }



}
