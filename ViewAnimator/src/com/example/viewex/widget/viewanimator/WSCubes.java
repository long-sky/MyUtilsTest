package com.example.viewex.widget.viewanimator;

import com.example.viewex.util.DimensionUtil;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * 
 * @author lf2596352
 *
 */
public class WSCubes extends View {

    private Context mContext;

    private Paint mPaint;

    private Paint mPaintRight;

    private Paint mPaintLeft;

    private Paint mPaintShadow;

    private float centerX;

    private float mWidth;

    private float mItemWidth;

    private float mItemHeight;

    private boolean mShadow;

    private float mValueAnimator;

    private ValueAnimator valueAnimator;

    public WSCubes(Context context) {
        this(context, null);
    }

    public WSCubes(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WSCubes(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mShadow = true;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.rgb(247, 202, 42));
        mPaint.setStrokeWidth(1);

        mPaintRight = new Paint();
        mPaintRight.setAntiAlias(true);
        mPaintRight.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintRight.setColor(Color.rgb(188, 91, 26));
        mPaintRight.setStrokeWidth(1);

        mPaintLeft = new Paint();
        mPaintLeft.setAntiAlias(true);
        mPaintLeft.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintLeft.setColor(Color.rgb(227, 144, 11));
        mPaintLeft.setStrokeWidth(1);


        mPaintShadow = new Paint();
        mPaintShadow.setAntiAlias(true);
        mPaintShadow.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintShadow.setColor(Color.rgb(0, 0, 0));
        mPaintShadow.setStrokeWidth(1f);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        centerX = w / 2;

        mWidth = Math.min(w, h);

        mItemWidth = mWidth / 16 * (float) Math.sqrt(3);

        mItemHeight = mWidth / 16;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if (mValueAnimator >= 0 && mValueAnimator < (1.0f / 3)) {
            drawStage1(canvas, mValueAnimator);
            if (mShadow) {
                drawShadow1(canvas, mValueAnimator);
            }
        } else if (mValueAnimator >= (1.0f / 3) && mValueAnimator < (1.0f / 3 * 2)) {
            drawStage2(canvas, mValueAnimator);
            if (mShadow) {
                drawShadow2(canvas, mValueAnimator);
            }
        } else if (mValueAnimator >= (1.0f / 3 * 2) && mValueAnimator <= 1.0f) {
            drawStage3(canvas, mValueAnimator);
            if (mShadow) {
                drawShadow3(canvas, mValueAnimator);
            }
        }


    }


    private void drawStage3(Canvas canvas, float valueAnimator) {

        float moveX = mItemWidth / 2 * (valueAnimator - (1.0f / 3) * 2) / (1.0f / 3);
        float moveY = mItemHeight / 2 * (valueAnimator - (1.0f / 3) * 2) / (1.0f / 3);

        Path p = new Path();
        p.moveTo(centerX - 2 * mItemWidth - mItemWidth / 2 + mItemWidth + moveX, 4 * mItemHeight - mItemHeight / 2 - mItemHeight + moveY);
        p.lineTo(centerX - mItemWidth - mItemWidth / 2 + mItemWidth + moveX, 3 * mItemHeight - mItemHeight / 2 - mItemHeight + moveY);
        p.lineTo(centerX - mItemWidth / 2 + mItemWidth + moveX, 4 * mItemHeight - mItemHeight / 2 - mItemHeight + moveY);
        p.lineTo(centerX - mItemWidth / 2 - mItemWidth + mItemWidth + moveX, 5 * mItemHeight - mItemHeight / 2 - mItemHeight + moveY);
        p.close();
        canvas.drawPath(p, mPaint);

        p.reset();
        p.moveTo(centerX - 2 * mItemWidth - mItemWidth / 2 + mItemWidth + moveX, 4 * mItemHeight - mItemHeight / 2 - mItemHeight + moveY);
        p.lineTo(centerX - mItemWidth - mItemWidth / 2 + mItemWidth + moveX, 5 * mItemHeight - mItemHeight / 2 - mItemHeight + moveY);
        p.lineTo(centerX - mItemWidth - mItemWidth / 2 + mItemWidth + moveX, 7 * mItemHeight - mItemHeight / 2 - mItemHeight + moveY);
        p.lineTo(centerX - 2 * mItemWidth - mItemWidth / 2 + mItemWidth + moveX, 6 * mItemHeight - mItemHeight / 2 - mItemHeight + moveY);
        p.close();
        canvas.drawPath(p, mPaintLeft);

        p.reset();
        p.moveTo(centerX + mItemWidth / 2 + moveX, 4 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX - mItemWidth + mItemWidth / 2 + moveX, 3 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX + mItemWidth / 2 + moveX, 2 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX + mItemWidth + mItemWidth / 2 + moveX, 3 * mItemHeight + mItemHeight / 2 + moveY);
        p.close();
        canvas.drawPath(p, mPaint);


        p.reset();
        p.moveTo(centerX + mItemWidth / 2 + moveX, 4 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX + mItemWidth + mItemWidth / 2 + moveX, 3 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX + 1 * mItemWidth + mItemWidth / 2 + moveX, 5 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX + mItemWidth / 2 + moveX, 6 * mItemHeight + mItemHeight / 2 + moveY);
        p.close();
        canvas.drawPath(p, mPaintRight);


        p.reset();
        p.moveTo(centerX - mItemWidth - mItemWidth / 2 - moveX, 5 * mItemHeight - mItemHeight / 2 - moveY);
        p.lineTo(centerX - mItemWidth / 2 - moveX, 4 * mItemHeight - mItemHeight / 2 - moveY);
        p.lineTo(centerX + mItemWidth - mItemWidth / 2 - moveX, 5 * mItemHeight - mItemHeight / 2 - moveY);
        p.lineTo(centerX - mItemWidth / 2 - moveX, 6 * mItemHeight - mItemHeight / 2 - moveY);
        p.close();
        canvas.drawPath(p, mPaint);


        p.reset();
        p.moveTo(centerX - mItemWidth - mItemWidth / 2 - moveX, 5 * mItemHeight - mItemHeight / 2 - moveY);
        p.lineTo(centerX - mItemWidth / 2 - moveX, 6 * mItemHeight - mItemHeight / 2 - moveY);
        p.lineTo(centerX - mItemWidth / 2 - moveX, 8 * mItemHeight - mItemHeight / 2 - moveY);
        p.lineTo(centerX - mItemWidth - mItemWidth / 2 - moveX, 7 * mItemHeight - mItemHeight / 2 - moveY);
        p.close();
        canvas.drawPath(p, mPaintLeft);


        p.reset();
        p.moveTo(centerX + mItemWidth / 2 - mItemWidth - moveX, 4 * mItemHeight + mItemHeight / 2 + mItemHeight - moveY);
        p.lineTo(centerX + mItemWidth + mItemWidth / 2 - mItemWidth - moveX, 3 * mItemHeight + mItemHeight / 2 + mItemHeight - moveY);
        p.lineTo(centerX + 2 * mItemWidth + mItemWidth / 2 - mItemWidth - moveX, 4 * mItemHeight + mItemHeight / 2 + mItemHeight - moveY);
        p.lineTo(centerX + mItemWidth + mItemWidth / 2 - mItemWidth - moveX, 5 * mItemHeight + mItemHeight / 2 + mItemHeight - moveY);
        p.close();
        canvas.drawPath(p, mPaint);

        p.reset();
        p.moveTo(centerX + mItemWidth / 2 - mItemWidth - moveX, 4 * mItemHeight + mItemHeight / 2 + mItemHeight - moveY);
        p.lineTo(centerX + mItemWidth + mItemWidth / 2 - mItemWidth - moveX, 5 * mItemHeight + mItemHeight / 2 + mItemHeight - moveY);
        p.lineTo(centerX + mItemWidth + mItemWidth / 2 - mItemWidth - moveX, 7 * mItemHeight + mItemHeight / 2 + mItemHeight - moveY);
        p.lineTo(centerX + mItemWidth / 2 - mItemWidth - moveX, 6 * mItemHeight + mItemHeight / 2 + mItemHeight - moveY);
        p.close();
        canvas.drawPath(p, mPaintLeft);


        p.reset();
        p.moveTo(centerX + mItemWidth + mItemWidth / 2 - mItemWidth - moveX, 5 * mItemHeight + mItemHeight / 2 + mItemHeight - moveY);
        p.lineTo(centerX + 2 * mItemWidth + mItemWidth / 2 - mItemWidth - moveX, 4 * mItemHeight + mItemHeight / 2 + mItemHeight - moveY);
        p.lineTo(centerX + 2 * mItemWidth + mItemWidth / 2 - mItemWidth - moveX, 6 * mItemHeight + mItemHeight / 2 + mItemHeight - moveY);
        p.lineTo(centerX + mItemWidth + mItemWidth / 2 - mItemWidth - moveX, 7 * mItemHeight + mItemHeight / 2 + mItemHeight - moveY);
        p.close();
        canvas.drawPath(p, mPaintRight);


    }

    private void drawStage2(Canvas canvas, float valueAnimator) {

        float moveX = mItemWidth * (valueAnimator - 1.0f / 3) / (1.0f / 3);
        float moveY = mItemHeight * (valueAnimator - 1.0f / 3) / (1.0f / 3);

        Path p = new Path();
        p.moveTo(centerX - 2 * mItemWidth - mItemWidth / 2 + moveX, 4 * mItemHeight - mItemHeight / 2 - moveY);
        p.lineTo(centerX - mItemWidth - mItemWidth / 2 + moveX, 3 * mItemHeight - mItemHeight / 2 - moveY);
        p.lineTo(centerX - mItemWidth / 2 + moveX, 4 * mItemHeight - mItemHeight / 2 - moveY);
        p.lineTo(centerX - mItemWidth / 2 - mItemWidth + moveX, 5 * mItemHeight - mItemHeight / 2 - moveY);
        p.close();
        canvas.drawPath(p, mPaint);

        p.reset();
        p.moveTo(centerX - 2 * mItemWidth - mItemWidth / 2 + moveX, 4 * mItemHeight - mItemHeight / 2 - moveY);
        p.lineTo(centerX - mItemWidth - mItemWidth / 2 + moveX, 5 * mItemHeight - mItemHeight / 2 - moveY);
        p.lineTo(centerX - mItemWidth - mItemWidth / 2 + moveX, 7 * mItemHeight - mItemHeight / 2 - moveY);
        p.lineTo(centerX - 2 * mItemWidth - mItemWidth / 2 + moveX, 6 * mItemHeight - mItemHeight / 2 - moveY);
        p.close();
        canvas.drawPath(p, mPaintLeft);

        p.reset();
        p.moveTo(centerX + mItemWidth / 2, 4 * mItemHeight + mItemHeight / 2);
        p.lineTo(centerX - mItemWidth + mItemWidth / 2, 3 * mItemHeight + mItemHeight / 2);
        p.lineTo(centerX + mItemWidth / 2, 2 * mItemHeight + mItemHeight / 2);
        p.lineTo(centerX + mItemWidth + mItemWidth / 2, 3 * mItemHeight + mItemHeight / 2);
        p.close();
        canvas.drawPath(p, mPaint);


        p.reset();
        p.moveTo(centerX + mItemWidth / 2, 4 * mItemHeight + mItemHeight / 2);
        p.lineTo(centerX + mItemWidth + mItemWidth / 2, 3 * mItemHeight + mItemHeight / 2);
        p.lineTo(centerX + 1 * mItemWidth + mItemWidth / 2, 5 * mItemHeight + mItemHeight / 2);
        p.lineTo(centerX + mItemWidth / 2, 6 * mItemHeight + mItemHeight / 2);
        p.close();
        canvas.drawPath(p, mPaintRight);


        p.reset();
        p.moveTo(centerX - mItemWidth - mItemWidth / 2, 5 * mItemHeight - mItemHeight / 2);
        p.lineTo(centerX - mItemWidth / 2, 4 * mItemHeight - mItemHeight / 2);
        p.lineTo(centerX + mItemWidth - mItemWidth / 2, 5 * mItemHeight - mItemHeight / 2);
        p.lineTo(centerX - mItemWidth / 2, 6 * mItemHeight - mItemHeight / 2);
        p.close();
        canvas.drawPath(p, mPaint);

        p.reset();
        p.moveTo(centerX - mItemWidth - mItemWidth / 2, 5 * mItemHeight - mItemHeight / 2);
        p.lineTo(centerX - mItemWidth / 2, 6 * mItemHeight - mItemHeight / 2);
        p.lineTo(centerX - mItemWidth / 2, 8 * mItemHeight - mItemHeight / 2);
        p.lineTo(centerX - mItemWidth - mItemWidth / 2, 7 * mItemHeight - mItemHeight / 2);
        p.close();
        canvas.drawPath(p, mPaintLeft);

        p.reset();
        p.moveTo(centerX - mItemWidth / 2, 6 * mItemHeight - mItemHeight / 2);
        p.lineTo(centerX + mItemWidth - mItemWidth / 2, 5 * mItemHeight - mItemHeight / 2);
        p.lineTo(centerX + mItemWidth - mItemWidth / 2, 7 * mItemHeight - mItemHeight / 2);
        p.lineTo(centerX - mItemWidth / 2, 8 * mItemHeight - mItemHeight / 2);
        p.close();
        canvas.drawPath(p, mPaintRight);


        p.reset();
        p.moveTo(centerX + mItemWidth / 2 - moveX, 4 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX + mItemWidth + mItemWidth / 2 - moveX, 3 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX + 2 * mItemWidth + mItemWidth / 2 - moveX, 4 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX + mItemWidth + mItemWidth / 2 - moveX, 5 * mItemHeight + mItemHeight / 2 + moveY);
        p.close();
        canvas.drawPath(p, mPaint);

        p.reset();
        p.moveTo(centerX + mItemWidth / 2 - moveX, 4 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX + mItemWidth + mItemWidth / 2 - moveX, 5 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX + mItemWidth + mItemWidth / 2 - moveX, 7 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX + mItemWidth / 2 - moveX, 6 * mItemHeight + mItemHeight / 2 + moveY);
        p.close();
        canvas.drawPath(p, mPaintLeft);


        p.reset();
        p.moveTo(centerX + mItemWidth + mItemWidth / 2 - moveX, 5 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX + 2 * mItemWidth + mItemWidth / 2 - moveX, 4 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX + 2 * mItemWidth + mItemWidth / 2 - moveX, 6 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX + mItemWidth + mItemWidth / 2 - moveX, 7 * mItemHeight + mItemHeight / 2 + moveY);
        p.close();
        canvas.drawPath(p, mPaintRight);

    }

    private void drawStage1(Canvas canvas, float valueAnimator) {

        float moveX = mItemWidth / 2.0f * valueAnimator / (1.0f / 3);
        float moveY = mItemHeight / 2.0f * valueAnimator / (1.0f / 3);

        Path p = new Path();
        p.moveTo(centerX - 2 * mItemWidth - moveX, 4 * mItemHeight - moveY);
        p.lineTo(centerX - mItemWidth - moveX, 3 * mItemHeight - moveY);
        p.lineTo(centerX - moveX, 4 * mItemHeight - moveY);
        p.lineTo(centerX - mItemWidth - moveX, 5 * mItemHeight - moveY);
        p.close();
        canvas.drawPath(p, mPaint);

        p.reset();
        p.moveTo(centerX - 2 * mItemWidth - moveX, 4 * mItemHeight - moveY);
        p.lineTo(centerX - mItemWidth - moveX, 5 * mItemHeight - moveY);
        p.lineTo(centerX - mItemWidth - moveX, 7 * mItemHeight - moveY);
        p.lineTo(centerX - 2 * mItemWidth - moveX, 6 * mItemHeight - moveY);
        p.close();
        canvas.drawPath(p, mPaintLeft);

        p.reset();
        p.moveTo(centerX + moveX, 4 * mItemHeight + moveY);
        p.lineTo(centerX - mItemWidth + moveX, 3 * mItemHeight + moveY);
        p.lineTo(centerX + moveX, 2 * mItemHeight + moveY);
        p.lineTo(centerX + mItemWidth + moveX, 3 * mItemHeight + moveY);
        p.close();
        canvas.drawPath(p, mPaint);

        p.reset();
        p.moveTo(centerX + moveX, 4 * mItemHeight + moveY);
        p.lineTo(centerX + mItemWidth + moveX, 3 * mItemHeight + moveY);
        p.lineTo(centerX + 2 * mItemWidth + moveX, 4 * mItemHeight + moveY);
        p.lineTo(centerX + mItemWidth + moveX, 5 * mItemHeight + moveY);
        p.close();
        canvas.drawPath(p, mPaint);

        p.reset();
        p.moveTo(centerX + moveX, 4 * mItemHeight + moveY);
        p.lineTo(centerX + mItemWidth + moveX, 5 * mItemHeight + moveY);
        p.lineTo(centerX + mItemWidth + moveX, 7 * mItemHeight + moveY);
        p.lineTo(centerX + moveX, 6 * mItemHeight + moveY);
        p.close();
        canvas.drawPath(p, mPaintLeft);


        p.reset();
        p.moveTo(centerX + mItemWidth + moveX, 5 * mItemHeight + moveY);
        p.lineTo(centerX + 2 * mItemWidth + moveX, 4 * mItemHeight + moveY);
        p.lineTo(centerX + 2 * mItemWidth + moveX, 6 * mItemHeight + moveY);
        p.lineTo(centerX + mItemWidth + moveX, 7 * mItemHeight + moveY);
        p.close();
        canvas.drawPath(p, mPaintRight);

        p.reset();
        p.moveTo(centerX - mItemWidth - moveX, 5 * mItemHeight - moveY);
        p.lineTo(centerX - moveX, 4 * mItemHeight - moveY);
        p.lineTo(centerX + mItemWidth - moveX, 5 * mItemHeight - moveY);
        p.lineTo(centerX - moveX, 6 * mItemHeight - moveY);
        p.close();
        canvas.drawPath(p, mPaint);

        p.reset();
        p.moveTo(centerX - mItemWidth - moveX, 5 * mItemHeight - moveY);
        p.lineTo(centerX - moveX, 6 * mItemHeight - moveY);
        p.lineTo(centerX - moveX, 8 * mItemHeight - moveY);
        p.lineTo(centerX - mItemWidth - moveX, 7 * mItemHeight - moveY);
        p.close();
        canvas.drawPath(p, mPaintLeft);

        p.reset();
        p.moveTo(centerX - moveX, 6 * mItemHeight - moveY);
        p.lineTo(centerX + mItemWidth - moveX, 5 * mItemHeight - moveY);
        p.lineTo(centerX + mItemWidth - moveX, 7 * mItemHeight - moveY);
        p.lineTo(centerX - moveX, 8 * mItemHeight - moveY);
        p.close();
        canvas.drawPath(p, mPaintRight);
    }

    private void drawShadow1(Canvas canvas, float valueAnimator) {

        float moveX = mItemWidth / 2.0f * valueAnimator / (1.0f / 3);
        float moveY = mItemHeight / 2.0f * valueAnimator / (1.0f / 3);

        Path p = new Path();
        p.moveTo(centerX - 2 * mItemWidth - moveX, 12 * mItemHeight - moveY);
        p.lineTo(centerX - mItemWidth - moveX, 11 * mItemHeight - moveY);
        p.lineTo(centerX - moveX, 12 * mItemHeight - moveY);
        p.lineTo(centerX - mItemWidth - moveX, 13 * mItemHeight - moveY);
        p.close();
        canvas.drawPath(p, mPaintShadow);

        p.reset();
        p.moveTo(centerX - mItemWidth + moveX, 11 * mItemHeight + moveY);
        p.lineTo(centerX + moveX, 10 * mItemHeight + moveY);
        p.lineTo(centerX + mItemWidth + moveX, 11 * mItemHeight + moveY);
        p.lineTo(centerX + moveX, 12 * mItemHeight + moveY);
        p.close();
        canvas.drawPath(p, mPaintShadow);

        p.reset();
        p.moveTo(centerX + moveX, 12 * mItemHeight + moveY);
        p.lineTo(centerX + mItemWidth + moveX, 11 * mItemHeight + moveY);
        p.lineTo(centerX + 2 * mItemWidth + moveX, 12 * mItemHeight + moveY);
        p.lineTo(centerX + mItemWidth + moveX, 13 * mItemHeight + moveY);
        p.close();
        canvas.drawPath(p, mPaintShadow);

        p.reset();
        p.moveTo(centerX - mItemWidth - moveX, 13 * mItemHeight - moveY);
        p.lineTo(centerX - moveX, 12 * mItemHeight - moveY);
        p.lineTo(centerX + mItemWidth - moveX, 13 * mItemHeight - moveY);
        p.lineTo(centerX - moveX, 14 * mItemHeight - moveY);
        p.close();
        canvas.drawPath(p, mPaintShadow);

    }

    private void drawShadow2(Canvas canvas, float valueAnimator) {

        float moveX = mItemWidth * (valueAnimator - 1.0f / 3) / (1.0f / 3);
        float moveY = mItemHeight * (valueAnimator - 1.0f / 3) / (1.0f / 3);

        Path p = new Path();
        p.moveTo(centerX - 2 * mItemWidth - mItemWidth / 2 + moveX, 12 * mItemHeight - mItemHeight / 2 - moveY);
        p.lineTo(centerX - mItemWidth - mItemWidth / 2 + moveX, 11 * mItemHeight - mItemHeight / 2 - moveY);
        p.lineTo(centerX - mItemWidth / 2 + moveX, 12 * mItemHeight - mItemHeight / 2 - moveY);
        p.lineTo(centerX - mItemWidth - mItemWidth / 2 + moveX, 13 * mItemHeight - mItemHeight / 2 - moveY);
        p.close();
        canvas.drawPath(p, mPaintShadow);


        p.reset();
        p.moveTo(centerX - mItemWidth + mItemWidth / 2, 11 * mItemHeight + mItemHeight / 2);
        p.lineTo(centerX + mItemWidth / 2, 10 * mItemHeight + mItemHeight / 2);
        p.lineTo(centerX + mItemWidth + mItemWidth / 2, 11 * mItemHeight + mItemHeight / 2);
        p.lineTo(centerX + mItemWidth / 2, 12 * mItemHeight + mItemHeight / 2);
        p.close();
        canvas.drawPath(p, mPaintShadow);

        p.reset();
        p.moveTo(centerX + mItemWidth / 2 - moveX, 12 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX + mItemWidth + mItemWidth / 2 - moveX, 11 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX + 2 * mItemWidth + mItemWidth / 2 - moveX, 12 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX + mItemWidth + mItemWidth / 2 - moveX, 13 * mItemHeight + mItemHeight / 2 + moveY);
        p.close();
        canvas.drawPath(p, mPaintShadow);


        p.reset();
        p.moveTo(centerX - mItemWidth - mItemWidth / 2, 13 * mItemHeight - mItemHeight / 2);
        p.lineTo(centerX - mItemWidth / 2, 12 * mItemHeight - mItemHeight / 2);
        p.lineTo(centerX + mItemWidth - mItemWidth / 2, 13 * mItemHeight - mItemHeight / 2);
        p.lineTo(centerX - mItemWidth / 2, 14 * mItemHeight - mItemHeight / 2);
        p.close();
        canvas.drawPath(p, mPaintShadow);

    }

    private void drawShadow3(Canvas canvas, float valueAnimator) {

        float moveX = mItemWidth / 2 * (valueAnimator - (1.0f / 3) * 2) / (1.0f / 3);
        float moveY = mItemHeight / 2 * (valueAnimator - (1.0f / 3) * 2) / (1.0f / 3);

        Path p = new Path();
        p.moveTo(centerX - 2 * mItemWidth - mItemWidth / 2 + mItemWidth + moveX, 12 * mItemHeight - mItemHeight / 2 - mItemHeight + moveY);
        p.lineTo(centerX - mItemWidth - mItemWidth / 2 + mItemWidth + moveX, 11 * mItemHeight - mItemHeight / 2 - mItemHeight + moveY);
        p.lineTo(centerX - mItemWidth / 2 + mItemWidth + moveX, 12 * mItemHeight - mItemHeight / 2 - mItemHeight + moveY);
        p.lineTo(centerX - mItemWidth - mItemWidth / 2 + mItemWidth + moveX, 13 * mItemHeight - mItemHeight / 2 - mItemHeight + moveY);
        p.close();
        canvas.drawPath(p, mPaintShadow);

        p.reset();
        p.moveTo(centerX - mItemWidth + mItemWidth / 2 + moveX, 11 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX + mItemWidth / 2 + moveX, 10 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX + mItemWidth + mItemWidth / 2 + moveX, 11 * mItemHeight + mItemHeight / 2 + moveY);
        p.lineTo(centerX + mItemWidth / 2 + moveX, 12 * mItemHeight + mItemHeight / 2 + moveY);
        p.close();
        canvas.drawPath(p, mPaintShadow);

        p.reset();
        p.moveTo(centerX + mItemWidth / 2 - mItemWidth - moveX, 12 * mItemHeight + mItemHeight / 2 + mItemHeight - moveY);
        p.lineTo(centerX + mItemWidth + mItemWidth / 2 - mItemWidth - moveX, 11 * mItemHeight + mItemHeight / 2 + mItemHeight - moveY);
        p.lineTo(centerX + 2 * mItemWidth + mItemWidth / 2 - mItemWidth - moveX, 12 * mItemHeight + mItemHeight / 2 + mItemHeight - moveY);
        p.lineTo(centerX + mItemWidth + mItemWidth / 2 - mItemWidth - moveX, 13 * mItemHeight + mItemHeight / 2 + mItemHeight - moveY);
        p.close();
        canvas.drawPath(p, mPaintShadow);

        p.reset();
        p.moveTo(centerX - mItemWidth - mItemWidth / 2 - moveX, 13 * mItemHeight - mItemHeight / 2 - moveY);
        p.lineTo(centerX - mItemWidth / 2 - moveX, 12 * mItemHeight - mItemHeight / 2 - moveY);
        p.lineTo(centerX + mItemWidth - mItemWidth / 2 - moveX, 13 * mItemHeight - mItemHeight / 2 - moveY);
        p.lineTo(centerX - mItemWidth / 2 - moveX, 14 * mItemHeight - mItemHeight / 2 - moveY);
        p.close();
        canvas.drawPath(p, mPaintShadow);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        int defaultDimension = DimensionUtil.dip2px(mContext, 100);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultDimension, defaultDimension);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultDimension, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, defaultDimension);
        }

    }


    public void startAnimator() {
        post(new Runnable() {
            @Override
            public void run() {
                stopAnimator();
                startAnimator(0f, 1f, 1000);
            }
        });
    }


    public void stopAnimator() {
        if (valueAnimator != null) {
            clearAnimation();
            valueAnimator.setRepeatCount(0);
            valueAnimator.cancel();
            valueAnimator.end();
            mValueAnimator = 0f;
            postInvalidate();
        }
    }

    private ValueAnimator startAnimator(float startValue, float endValue, long duration) {
        valueAnimator = ValueAnimator.ofFloat(startValue, endValue);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mValueAnimator = (float) Float.parseFloat(valueAnimator.getAnimatedValue().toString());
                postInvalidate();
            }
        });
        if (!valueAnimator.isRunning()) {
            valueAnimator.start();
        }
        return valueAnimator;
    }

    public void isShadow(boolean show) {
        this.mShadow = show;
        postInvalidate();
    }
}
