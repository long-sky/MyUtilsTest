package com.example.viewex.widget.viewanimator;

import com.example.viewex.util.DimensionUtil;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
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
public class WSJump extends View {

    private Paint mPaint;
    private Context mContext;

    private int centerX, centerY;

    private int mRadius;

    private float mJumpY;

    private float quadMoveY;

    private final static float RADIUS_RATIO = 2 / 3f;


    public WSJump(Context context) {
        this(context, null);
    }

    public WSJump(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WSJump(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
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

        mRadius = (int) (Math.min(Math.min(centerY - getPaddingTop(), centerY - getPaddingBottom()),
                Math.min(centerX - getPaddingLeft(), centerX - getPaddingRight())) * RADIUS_RATIO);

    }

    @SuppressLint("DrawAllocation") @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(DimensionUtil.dip2px(mContext, 2));
        Path mPath = new Path();
        mPath.moveTo(centerX - mRadius, centerY);
        mPath.quadTo(centerX, centerY + quadMoveY, centerX + mRadius, centerY);
        canvas.drawPath(mPath, mPaint);

        canvas.drawCircle(centerX - mRadius - DimensionUtil.dip2px(mContext, 4), centerY, DimensionUtil.dip2px(mContext, 4), mPaint);
        canvas.drawCircle(centerX + mRadius + DimensionUtil.dip2px(mContext, 4), centerY, DimensionUtil.dip2px(mContext, 4), mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY - DimensionUtil.dip2px(mContext, 4 + 3) - mJumpY, DimensionUtil.dip2px(mContext, 6), mPaint);


    }

    public void startAnimator() {
        post(new Runnable() {
            @Override
            public void run() {
                ValueAnimator animator = ValueAnimator.ofFloat(0f, 1.0f);
                animator.setDuration(500);
                animator.setInterpolator(new LinearInterpolator());
                animator.setRepeatMode(ValueAnimator.RESTART);
                animator.setRepeatCount(ValueAnimator.INFINITE);

                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float value = (float) Float.parseFloat(valueAnimator.getAnimatedValue().toString());
                        if (value > 0.75f) {  // 0.75  =0.25*3
                            quadMoveY = mRadius * (1 - value) * 3;
                        } else {
                            quadMoveY = value * mRadius;
                        }
                        if (value > 0.35f) {// 0.7  =0.35*2
                            mJumpY = (1 - value) * mRadius;
                        } else {
                            mJumpY = value * mRadius * 2;
                        }
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
