package com.example.viewex.widget.viewanimator;

import com.example.viewex.util.DimensionUtil;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * 
 * @author lf2596352
 *
 */
public class WSEatBeans extends View {

    private Paint mPaint;
    private Context mContext;

    private int centerX, centerY;

    private int mRadius;

    private int eatRadius;

    private float eatStartAngle;

    private float eatSweepAngle;

    private int moveX;

    private int eatBeans;

    private int eatState;

    private final static float RADIUS_RATIO = 2 / 3f;

    public WSEatBeans(Context context) {
        this(context, null);
    }

    public WSEatBeans(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WSEatBeans(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);

        eatRadius = DimensionUtil.dip2px(mContext, 15);
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

        mRadius = (int) (Math.min(centerX - getPaddingLeft(), centerX - getPaddingRight()) * RADIUS_RATIO);

        eatStartAngle = 30;
        eatSweepAngle = 360 - 2 * eatStartAngle;
    }

    @SuppressLint("DrawAllocation") @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStrokeWidth(DimensionUtil.dip2px(mContext, 2));
        RectF rectF = new RectF(centerX - mRadius + moveX, centerY - eatRadius, centerX - mRadius + eatRadius * 2
                + moveX, centerY + eatRadius);
        canvas.drawArc(rectF, eatStartAngle, eatSweepAngle, true, mPaint);

        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(centerX - mRadius + eatRadius + moveX, centerY - eatRadius / 2, DimensionUtil.dip2px(mContext, 2), mPaint);

        mPaint.setColor(Color.WHITE);
        int count = (2 * (mRadius - eatRadius)) / eatRadius - 1;
        for (int i = eatBeans; i < count; i++) {
            canvas.drawCircle(centerX - mRadius + eatRadius * 2 +
                    eatRadius * (i + 1), centerY, DimensionUtil.dip2px(mContext, 2), mPaint);
        }


    }

    public void startAnimator() {
        post(new Runnable() {
            @Override
            public void run() {
                ValueAnimator animator = ValueAnimator.ofFloat(0f, 1.0f);
                animator.setDuration(3000);
                animator.setInterpolator(new LinearInterpolator());
                animator.setRepeatMode(ValueAnimator.RESTART);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {

                        moveX = (int) ((float) Float.parseFloat(animation.getAnimatedValue().toString()) * 2 * (mRadius - eatRadius));
                        eatBeans = moveX / eatRadius;

                        if (eatState == 0) {
                            eatStartAngle -= 2;
                            if (eatStartAngle <= 0) {
                                eatState = 1;
                            }
                        } else if (eatState == 1) {
                            eatStartAngle += 2;
                            if (eatStartAngle >= 30) {
                                eatState = 0;
                            }
                        }
                        eatSweepAngle = 360 - eatStartAngle * 2;
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

    public float getFontHeight(Paint paint, String str) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.height();

    }
}
