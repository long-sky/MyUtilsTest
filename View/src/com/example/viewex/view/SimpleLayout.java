package com.example.viewex.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 自定义布局，理解onLayout()过程
 * 只能包含一个子视图，多余的子视图会被舍弃掉
 * @author lf2596352
 *
 */
public class SimpleLayout extends ViewGroup{

	public SimpleLayout(Context context,AttributeSet attributeSet) {
		super(context,attributeSet);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//判断Simple中是否包含一个子视图，有的话调用measureChild()方法测量出子视图的大小
		if(getChildCount()>0){
			View childView = getChildAt(0);
			measureChild(childView, widthMeasureSpec, heightMeasureSpec);
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		//判断Simple中是否包含一个子视图，有的话调用layout()确定他在SimpleLayout中的位置
		if(getChildCount()>0){
			View childView = getChildAt(0);
			childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
		}
	}

}
