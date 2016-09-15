package com.example.viewex.widget.viewanimator;

import com.example.viewex.util.DimensionUtil;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
public class WSSwapLoading extends View {

    private Context mContext;
    private Paint mPaint;

    private float centerX;
    private float centerY;
    private float mRadius;

    private float ballRadius;

    private float mValueAnimator;

    private float ballSpacing;

    private float mRotationX;

    private float mRotationY;

    private float mRotationAngle;

    private float mNextRotationAngle;

    private int mCurrentBallIndex;

    @SuppressWarnings("unused")
	private static final int DEGREE_360 = 360;

    private static final int DEGREE_180 = 180;

    private static final int BALL_NUM = 5;

    private final static float RADIUS_RATIO = 2 / 3f;

    private final static float BALL_RADIUS_RATIO = 1 / 4f;

    private static final float BALL_SPACING_RADIUS = 0.5f;

    public WSSwapLoading(Context context) {
        this(context, null);
    }

    public WSSwapLoading(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WSSwapLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);

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

        mPaint.setStrokeWidth(DimensionUtil.dip2px(mContext, 2));

        ballSpacing = 2 * mRadius / (BALL_NUM - 1);

        ballRadius = ballSpacing * BALL_RADIUS_RATIO;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.WHITE);
        //缁樺埗灏忕悆
        for (int i = 0; i < BALL_NUM; i++) {
            if (i == mCurrentBallIndex && i != BALL_NUM - 1) {
                if (isClockwiseRotation(i)) {
                    mRotationAngle = DEGREE_180 + mValueAnimator * DEGREE_180;
                    mNextRotationAngle = mValueAnimator * DEGREE_180;
                } else {
                    mRotationAngle = DEGREE_180 - mValueAnimator * DEGREE_180;
                    mNextRotationAngle = -mValueAnimator * DEGREE_180;
                }
            }

            if (i == mCurrentBallIndex) {
                mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

                mRotationX = (float) (ballSpacing * BALL_SPACING_RADIUS * Math.cos(Math.toRadians(mRotationAngle)));
                mRotationY = (float) (ballSpacing * BALL_SPACING_RADIUS * Math.sin(Math.toRadians(mRotationAngle)));

                if (i == BALL_NUM - 1) {
                    getFirstAndLastAngle();
                    canvas.drawCircle((float) (centerX + 2 * ballSpacing * Math.cos(Math.toRadians(mNextRotationAngle))),
                            (float) (centerY + 2 * ballSpacing * Math.sin(Math.toRadians(mNextRotationAngle))),
                            ballRadius, mPaint);
                } else {
                    canvas.drawCircle(centerX - mRadius + ballSpacing * (BALL_SPACING_RADIUS + i) + mRotationX,
                            centerY + mRotationY, ballRadius, mPaint);
                }
            }

            if ((i + 1) % BALL_NUM == getNextBallIndex()) {
                mPaint.setStyle(Paint.Style.STROKE);

                mRotationX = (float) (ballSpacing * BALL_SPACING_RADIUS * Math.cos(Math.toRadians(mNextRotationAngle)));
                mRotationY = (float) (ballSpacing * BALL_SPACING_RADIUS * Math.sin(Math.toRadians(mNextRotationAngle)));

                if (i == BALL_NUM - 1) {
                    getFirstAndLastAngle();
                    canvas.drawCircle((float) (centerX + 2 * ballSpacing * Math.cos(Math.toRadians(mRotationAngle))),
                            (float) (centerY + 2 * ballSpacing * Math.sin(Math.toRadians(mRotationAngle))),
                            ballRadius, mPaint);

                } else {
                    canvas.drawCircle(centerX - mRadius + ballSpacing * (BALL_SPACING_RADIUS + i) + mRotationX,
                            centerY + mRotationY, ballRadius, mPaint);
                }
            }

            if (i != mCurrentBallIndex && i != getNextBallIndex()) {
                mPaint.setStyle(Paint.Style.STROKE);
                canvas.drawCircle(centerX - mRadius + ballSpacing * i, centerY, ballRadius, mPaint);
            }

        }
        // centerX - mRadius + ballSpacing*0.5f      centerY
    }

    public void getFirstAndLastAngle() {
        mRotationAngle = mValueAnimator * DEGREE_180;
        mNextRotationAngle = DEGREE_180 + mValueAnimator * DEGREE_180;
    }

    public boolean isClockwiseRotation(int index) {
        if (index % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }


    private int getNextBallIndex() {
        return (mCurrentBallIndex + 1) % BALL_NUM;
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
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mValueAnimator = (float) Float.parseFloat(animation.getAnimatedValue().toString());

                        postInvalidate();
                    }
                });
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        mCurrentBallIndex = 0;
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        super.onAnimationRepeat(animation);
                        mCurrentBallIndex = getNextBallIndex();

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
