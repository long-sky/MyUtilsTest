package com.example.viewex.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * �Զ��岼�֣����onLayout()����
 * ֻ�ܰ���һ������ͼ�����������ͼ�ᱻ������
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
		//�ж�Simple���Ƿ����һ������ͼ���еĻ�����measureChild()��������������ͼ�Ĵ�С
		if(getChildCount()>0){
			View childView = getChildAt(0);
			measureChild(childView, widthMeasureSpec, heightMeasureSpec);
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		//�ж�Simple���Ƿ����һ������ͼ���еĻ�����layout()ȷ������SimpleLayout�е�λ��
		if(getChildCount()>0){
			View childView = getChildAt(0);
			childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
		}
	}

}
