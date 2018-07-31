package com.example.slashtogglebutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by karthik on 31/7/18.
 */

public class SlashBtn2 extends View {

    private static final int FPS = 60;

    private int a = 0, b = 0, initial = 1;
    private Handler handler;
    private Runnable r;
    private Bitmap iconBitmap;
    private boolean toggleState = false;
    private Bitmap mBitmap;
    private Paint mPaint, linePaint, clearPaint;
    private Path linePath;
    private Integer w, h;
    private float length;

    private float currX, currY,margin = 5;

    public SlashBtn2(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(12);
        linePaint = new Paint();
        linePaint.setStrokeWidth(12);
        linePaint.setAlpha(120);
        initHandler();
    }

    public SlashBtn2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(12);
        linePaint = new Paint();
        linePaint.setStrokeWidth(12);
        linePaint.setColor(Color.BLACK);
        linePaint.setAlpha(120);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SlashToggleButton, 0, 0);
        iconBitmap = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(R.styleable.SlashToggleButton_icon, 0));
        initial = 1;
        initHandler();
    }

    public SlashBtn2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        currX = currY = 0;

        clearPaint = new Paint();
        clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!toggleState) {

//            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), clearPaint);

            canvas.drawBitmap(iconBitmap, 0, 0, mPaint);

            canvas.drawLine(margin, margin, currX, currY, mPaint);

            if (currX > margin) {
                currX -= 5;
                currY -= 5;
            } else {
                handler.removeCallbacksAndMessages(null);
            }


        } else {

            canvas.drawBitmap(iconBitmap, 0, 0, linePaint);

            canvas.drawLine(margin, margin, currX-margin, currY-margin, linePaint);

            if (currX <= canvas.getWidth()) {
                currX += 5;
                currY += 5;
            } else {
                handler.removeCallbacksAndMessages(null);
            }

        }

    }

    public void toggle() {

        toggleState = !toggleState;

        r.run();

    }


}
