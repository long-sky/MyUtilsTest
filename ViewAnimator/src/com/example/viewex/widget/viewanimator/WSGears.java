package com.example.viewex.widget.viewanimator;

import com.example.viewex.util.DimensionUtil;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * 
 * @author lf2596352
 *
 */
public class WSGears extends View {

    private Paint mPaint;
    private Context mContext;

    private int centerX, centerY;

    private int outCircleRadius; 

    private int inCircleRadius;  

    private int moveAngle; 

    float mAnimatedValue;

    private final static float RADIUS_RATIO = 2 / 3f;

    private final static float IN_CIRCLE_RATIO = 1 / 2f;

    private final static float GEAR_RADIUS_RATIO = 1 / 16f;

    public WSGears(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public WSGears(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WSGears(Context context) {
        this(context, null);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mContext = context;
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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        centerX = w / 2;
        centerY = h / 2;

        outCircleRadius = (int) (Math.min(Math.min(centerY - getPaddingTop(), centerY - getPaddingBottom()),
                Math.min(centerX - getPaddingLeft(), centerX - getPaddingRight())) * RADIUS_RATIO);

        inCircleRadius = (int) (outCircleRadius * IN_CIRCLE_RATIO);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStrokeWidth(outCircleRadius * GEAR_RADIUS_RATIO);

        for (int i = 0; i < 360 / 120; i++) {
            canvas.drawLine(centerX, centerY, (float) (centerX + outCircleRadius * Math.sin(Math.toRadians(i * 120 + 60))),
                    (float) (centerY + outCircleRadius * Math.cos(Math.toRadians(i * 120 + 60))), mPaint);
        }

        for (int i = 0; i < 360 / 8; i++) {
            canvas.drawLine((float) (centerX + outCircleRadius * Math.sin(Math.toRadians(i * 8 + moveAngle))),
                    (float) (centerY + outCircleRadius * Math.cos(Math.toRadians(i * 8 + moveAngle))),
                    (float) (centerX + (outCircleRadius + DimensionUtil.dip2px(mContext, 4)) * Math.sin(Math.toRadians(i * 8 + moveAngle))),
                    (float) (centerY + (outCircleRadius + DimensionUtil.dip2px(mContext, 4)) * Math.cos(Math.toRadians(i * 8 + moveAngle))), mPaint);
        }

        mPaint.setStrokeWidth(inCircleRadius * GEAR_RADIUS_RATIO);
        for (int i = 0; i < 360 / 8; i++) {
            canvas.drawLine((float) (centerX + inCircleRadius * Math.sin(Math.toRadians(i * 8 - moveAngle))),
                    (float) (centerY + inCircleRadius * Math.cos(Math.toRadians(i * 8 - moveAngle))),
                    (float) (centerX + (inCircleRadius + DimensionUtil.dip2px(mContext, 4)) * Math.sin(Math.toRadians(i * 8 - moveAngle))),
                    (float) (centerY + (inCircleRadius + DimensionUtil.dip2px(mContext, 4)) * Math.cos(Math.toRadians(i * 8 - moveAngle))), mPaint);

        }

        mPaint.setStrokeWidth(mPaint.getStrokeWidth() * 2);
        canvas.drawCircle(centerX, centerY, outCircleRadius, mPaint);

        canvas.drawCircle(centerX, centerY, inCircleRadius, mPaint);


    }

    public void startAnimator() {
        post(new Runnable() {
            @Override
            public void run() {

                ValueAnimator animator = ValueAnimator.ofFloat(0f, 1.0f);
                animator.setDuration(5000);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setRepeatMode(ValueAnimator.RESTART);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        mAnimatedValue = (float) Float.parseFloat(valueAnimator.getAnimatedValue().toString());
                        moveAngle = (int) (mAnimatedValue * 360);

                        postInvalidate();
                    }
                });

                animator.start();
            }
        });
    }


    public void setPaintColor(int color) {
        mPaint.setColor(color);
    }
}
