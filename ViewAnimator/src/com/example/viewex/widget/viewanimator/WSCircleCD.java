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
 * 雷达loading
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
	 * 开始动画
	 */
	public void startAnimator(){
		post(new Runnable() {
			@Override
			public void run() {
				/**
				 * 旋转动画
				 * 旋转开始的角度
				 * 旋转结束的角度
				 * X轴的伸缩模式,可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT
				 * X坐标的伸缩值
				 * Y轴的伸缩模式,可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT
				 * Y坐标的伸缩值
				 */
				RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
				/**
				 * 设置插值器
				 * AccelerateDecelerateInterpolator：先加速再减速
				 * AccelerateInterpolator：加速
				 * AnticipateInterpolator：先回退一小步再加速前进
				 * AnticipateOvershootInterpolator：在上一个基础上超出终点一小步再回到终点
				 * BounceInterpolator：最后阶段弹球效果
				 * CycleInterpolator：周期运动
				 * DecelerateInterpolator：减速
				 * LinearInterpolator：匀速
				 * OvershootInterpolator：快速到达终点并超出终点一小步后回到终点
				 */
				rotateAnimation.setInterpolator(new LinearInterpolator());
				//设置重复次数
				rotateAnimation.setRepeatCount(-1);
				//设置运行周期
				rotateAnimation.setDuration(1000);
				//动画执行完后是否停留在执行完的状态 
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
		
		//处理 wrap_content问题
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

        //处理padding情况,Math.min:返回参数中较小的一个
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
		//画大圆
		canvas.drawCircle(circleCenterX, circleCenterY, circleRadius, mPaint);
		//画中心小圆
		canvas.drawCircle(circleCenterX, circleCenterY, DimensionUtil.dip2px(mContext, 4), mPaint);
		//创建矩形
		RectF rectF = new RectF(circleCenterX - circleRadius * RADIUS_RATIO, circleCenterY - circleRadius * RADIUS_RATIO,
                circleCenterX + circleRadius * RADIUS_RATIO, circleCenterY + circleRadius * RADIUS_RATIO);
		//以矩形中心画弧线
		canvas.drawArc(rectF, 0, 80, false, mPaint);
        canvas.drawArc(rectF, 180, 80, false, mPaint);
        //创建矩形
      	rectF = new RectF(circleCenterX - circleRadius / 2, circleCenterY - circleRadius / 2,
                      circleCenterX + circleRadius / 2, circleCenterY + circleRadius / 2);
      	//以矩形中心画弧线
      	canvas.drawArc(rectF, 0, 80, false, mPaint);
        canvas.drawArc(rectF, 180, 80, false, mPaint);
	}
}
