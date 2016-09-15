package com.example.viewex.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义简单视图
 * @author lf2596352
 *
 */
public class RectHelloView extends View{
	private Paint mPanit;
	
	public RectHelloView(Context context,AttributeSet attrs) {
		super(context,attrs);
		mPanit = new Paint(Paint.ANTI_ALIAS_FLAG);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		mPanit.setColor(Color.YELLOW);
		canvas.drawRect(0,0,getWidth(),getHeight(),mPanit);
		mPanit.setColor(Color.BLUE);
		mPanit.setTextSize(20);
		String text = "Hello View";
		canvas.drawText(text, 0, getHeight()/2, mPanit);
	}
}
