package com.karthik.slashtogglebutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by karthik on 31/7/18.
 */

public class SlashToggleButton extends View {

    private static final int FPS = 60;

    private Handler handler;
    private Runnable r;
    private Bitmap iconBitmap;
    private boolean toggleState = false;
    private Paint mPaint, linePaint, clearPaint,whitePaint;
    private int thickness = 12;
    private float currX, currY, margin = 6;

    public SlashToggleButton(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(thickness/2);
        linePaint = new Paint();
        linePaint.setDither(true);
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(thickness/2);
        linePaint.setColor(Color.BLACK);
        linePaint.setAlpha(120);
        whitePaint = new Paint();
        whitePaint.setDither(true);
        whitePaint.setColor(Color.WHITE);
        whitePaint.setAntiAlias(true);
        whitePaint.setStrokeWidth(thickness);
        initHandler();
    }

    public SlashToggleButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(thickness/2);
        linePaint = new Paint();
        linePaint.setDither(true);
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(thickness/2);
        linePaint.setColor(Color.BLACK);
        linePaint.setAlpha(120);
        whitePaint = new Paint();
        whitePaint.setDither(true);
        whitePaint.setColor(Color.WHITE);
        whitePaint.setAntiAlias(true);
        whitePaint.setStrokeWidth(thickness);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, com.example.slashtogglebutton.R.styleable.SlashToggleButton, 0, 0);
        iconBitmap = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(com.example.slashtogglebutton.R.styleable.SlashToggleButton_icon, 0));
        initHandler();
    }

    public SlashToggleButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initHandler() {

        handler = new Handler();

        r = new Runnable() {
            @Override
            public void run() {

                invalidate();

                handler.postDelayed(r, 1000 / FPS);

            }
        };

        currX = currY = margin;

        clearPaint = new Paint();
        clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!toggleState) {
            //true
            canvas.drawBitmap(iconBitmap, 0, 0, mPaint);

            if (currX > margin) {

                currX -= 5;
                currY -= 5;
            } else {
                handler.removeCallbacksAndMessages(null);
            }
            canvas.drawLine(margin,margin,currX,currY,whitePaint);
            canvas.drawLine(margin + margin/2, margin + margin/2, currX + margin/2, currY + margin/2, mPaint);

        } else {
            //false
            canvas.drawBitmap(iconBitmap, 0, 0, linePaint);
            if (currX <= iconBitmap.getWidth() - margin) {

                currX += 5;
                currY += 5;
            } else {
                handler.removeCallbacksAndMessages(null);
            }
            canvas.drawLine(margin,margin,currX,currY,whitePaint);
            canvas.drawLine(margin + margin/2, margin + margin/2, currX + margin/2, currY + margin/2, linePaint);
        }

    }

    public void toggle() {

        toggleState = !toggleState;

        r.run();

    }

    public void setIconBitmap(Bitmap bitmap){

        iconBitmap = bitmap;
        initHandler();

    }

    public void setToggleState(boolean bool){

        toggleState = bool;

    }

    public void setMargin(int m){

        margin = m;

    }


}
