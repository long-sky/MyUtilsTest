package com.example.viewex.widget.viewanimator;

import com.example.viewex.util.DimensionUtil;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * 
 * @author lf2596352
 *
 */
public class WSCircleSun extends View {

    private Paint mPaint;
    private Context mContext;

    private int circleCenterX, circleCenterY;

    private int circleRadius;

    private int moveAngle;

    private final static float RADIUS_RATIO = 2 / 3f;
    private final static float SMALL_RADIUS_RATIO = 1 / 12f;
    private final static float LARGE_RADIUS_RATIO = 2 / 3f;

    private final static int SMALL_CIRCLE_COUNT = 8;

    public WSCircleSun(Context context) {
        this(context, null);
    }

    public WSCircleSun(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WSCircleSun(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
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

        circleCenterX = w / 2;
        circleCenterY = h / 2;

        circleRadius = (int) (Math.min(Math.min(circleCenterY - getPaddingTop(), circleCenterY - getPaddingBottom()),
                Math.min(circleCenterX - getPaddingLeft(), circleCenterX - getPaddingRight())) * RADIUS_RATIO);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStrokeWidth(circleRadius * SMALL_RADIUS_RATIO);

        for (int i = 0; i < SMALL_CIRCLE_COUNT; i++) {
            canvas.drawCircle((float) (circleCenterX + circleRadius * Math.sin(Math.toRadians(i * 360 / SMALL_CIRCLE_COUNT + moveAngle))),
                    (float) (circleCenterY + circleRadius * Math.cos(Math.toRadians(i * 360 / SMALL_CIRCLE_COUNT + moveAngle))),
                    circleRadius * SMALL_RADIUS_RATIO,
                    mPaint);
        }

        canvas.drawCircle(circleCenterX, circleCenterY, circleRadius * LARGE_RADIUS_RATIO, mPaint);

    }

    public void startAnimator() {
        post(new Runnable() {
            @Override
            public void run() {
                ValueAnimator animator = ValueAnimator.ofInt(0, 361);
                animator.setDuration(3000);
                animator.setInterpolator(new LinearInterpolator());
                animator.setRepeatMode(ValueAnimator.RESTART);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        moveAngle = (int) Float.parseFloat(valueAnimator.getAnimatedValue().toString());
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
