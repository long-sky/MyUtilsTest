package com.example.viewex.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自绘控件计数器
 * @author lf2596352
 *
 */
public class CounterView extends View implements android.view.View.OnClickListener{
	private Paint mPaint;
	private Rect mBounds;
	private int mCount;
	
	public CounterView(Context context,AttributeSet attrs) {
		super(context,attrs);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mBounds = new Rect();
		setOnClickListener(this);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mPaint.setColor(Color.BLUE);
		canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
		mPaint.setColor(Color.YELLOW);
		mPaint.setTextSize(30);
		String count = String.valueOf(mCount);
		mPaint.getTextBounds(count, 0, count.length(), mBounds);
		float width = mBounds.width();
		float heigh = mBounds.height();
		canvas.drawText(count, getWidth()/2-width/2, getHeight()/2+heigh/2, mPaint);
	}

	@Override
	public void onClick(View v) {
		mCount++;
		invalidate();
	}
}
