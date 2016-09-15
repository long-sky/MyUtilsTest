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
public class WSCircleRipple extends View {

    private Context mContext;
    private Paint mPaint;

    private float centerX;

    private float centerY;

    private float mValueAnimator;

    private float rippleRadius;

    private float rippleMaxRadius;

    private float rippleMinRadius;

    private float canvasRadius;

    private int currentAlpha = ALPHA_255;

    private static final float MIN_RIPPLE_RADIUS = 0.1f;

    private static final float MAX_RIPPLE_RADIUS = 0.67f;

    private static final int ALPHA_255 = 255;

    private static final int STROKE_WIDTH = 1;

    public WSCircleRipple(Context context) {
        this(context, null);
    }

    public WSCircleRipple(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WSCircleRipple(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(DimensionUtil.dip2px(mContext, STROKE_WIDTH));
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

        canvasRadius = (int) (Math.min(Math.min(centerY - getPaddingTop(), centerY - getPaddingBottom()),
                Math.min(centerX - getPaddingLeft(), centerX - getPaddingRight())));

        rippleRadius = rippleMinRadius = MIN_RIPPLE_RADIUS * canvasRadius;

        rippleMaxRadius = MAX_RIPPLE_RADIUS * canvasRadius;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setAlpha(currentAlpha);
        canvas.drawCircle(centerX, centerY, rippleRadius, mPaint);

    }

    public void startAnimator() {
        post(new Runnable() {
            @Override
            public void run() {
                ValueAnimator animator = ValueAnimator.ofFloat(0f, 1.0f);
                animator.setDuration(800);
                animator.setInterpolator(new LinearInterpolator());
                animator.setRepeatMode(ValueAnimator.RESTART);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mValueAnimator = (float) Float.parseFloat(animation.getAnimatedValue().toString());
                        currentAlpha = (int) (ALPHA_255 * (1 - mValueAnimator));
                        rippleRadius = rippleMinRadius + (rippleMaxRadius - rippleMinRadius) * mValueAnimator;
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
