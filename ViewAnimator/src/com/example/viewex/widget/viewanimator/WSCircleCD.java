package com.example.viewex.widget.viewanimator;

import com.example.viewex.util.DimensionUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * �״�loading
 * @author lf2596352
 *
 */
public class WSCircleCD extends View{
	private Context mContext;
	private Paint mPaint;
	private int circleCenterX, circleCenterY;
    private int circleRadius;
    private final static float RADIUS_RATIO = 2 / 3f;

	public WSCircleCD(Context context) {
		this(context,null);
	}
	
	public WSCircleCD(Context context, AttributeSet attrs) {
		this(context,attrs,0);
	}
	
	public WSCircleCD(Context context, AttributeSet attrs, int defStyle) {
		super(context,attrs,defStyle);
		init(context);
	}
	
	private void init(Context context){
		this.mContext = context;
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(Color.WHITE);
		mPaint.setStyle(Paint.Style.STROKE);
	}
	
	/**
	 * ��ʼ����
	 */
	public void startAnimator(){
		post(new Runnable() {
			@Override
			public void run() {
				/**
				 * ��ת����
				 * ��ת��ʼ�ĽǶ�
				 * ��ת�����ĽǶ�
				 * X�������ģʽ,����ȡֵΪABSOLUTE��RELATIVE_TO_SELF��RELATIVE_TO_PARENT
				 * X���������ֵ
				 * Y�������ģʽ,����ȡֵΪABSOLUTE��RELATIVE_TO_SELF��RELATIVE_TO_PARENT
				 * Y���������ֵ
				 */
				RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
				/**
				 * ���ò�ֵ��
				 * AccelerateDecelerateInterpolator���ȼ����ټ���
				 * AccelerateInterpolator������
				 * AnticipateInterpolator���Ȼ���һС���ټ���ǰ��
				 * AnticipateOvershootInterpolator������һ�������ϳ����յ�һС���ٻص��յ�
				 * BounceInterpolator�����׶ε���Ч��
				 * CycleInterpolator�������˶�
				 * DecelerateInterpolator������
				 * LinearInterpolator������
				 * OvershootInterpolator�����ٵ����յ㲢�����յ�һС����ص��յ�
				 */
				rotateAnimation.setInterpolator(new LinearInterpolator());
				//�����ظ�����
				rotateAnimation.setRepeatCount(-1);
				//������������
				rotateAnimation.setDuration(1000);
				//����ִ������Ƿ�ͣ����ִ�����״̬ 
				rotateAnimation.setFillAfter(true);
                startAnimation(rotateAnimation);
			}
		});
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
		
		int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
		
		//���� wrap_content����
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

        //����padding���,Math.min:���ز����н�С��һ��
        circleRadius = (int) (Math.min(Math.min(circleCenterY - getPaddingTop(), circleCenterY - getPaddingBottom()),
                Math.min(circleCenterX - getPaddingLeft(), circleCenterX - getPaddingRight())) * RADIUS_RATIO);
	}
	
	public void setPaintColor(int color){
		mPaint.setColor(color);
	}
	
	@SuppressLint("DrawAllocation") @Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mPaint.setStrokeWidth(DimensionUtil.dip2px(mContext, 2));
		//����Բ
		canvas.drawCircle(circleCenterX, circleCenterY, circleRadius, mPaint);
		//������СԲ
		canvas.drawCircle(circleCenterX, circleCenterY, DimensionUtil.dip2px(mContext, 4), mPaint);
		//��������
		RectF rectF = new RectF(circleCenterX - circleRadius * RADIUS_RATIO, circleCenterY - circleRadius * RADIUS_RATIO,
                circleCenterX + circleRadius * RADIUS_RATIO, circleCenterY + circleRadius * RADIUS_RATIO);
		//�Ծ������Ļ�����
		canvas.drawArc(rectF, 0, 80, false, mPaint);
        canvas.drawArc(rectF, 180, 80, false, mPaint);
        //��������
      	rectF = new RectF(circleCenterX - circleRadius / 2, circleCenterY - circleRadius / 2,
                      circleCenterX + circleRadius / 2, circleCenterY + circleRadius / 2);
      	//�Ծ������Ļ�����
      	canvas.drawArc(rectF, 0, 80, false, mPaint);
        canvas.drawArc(rectF, 180, 80, false, mPaint);
	}
}
