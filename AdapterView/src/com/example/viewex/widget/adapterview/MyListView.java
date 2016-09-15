package com.example.viewex.widget.adapterview;

import com.example.adapterview.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
/**
 * 继承控件侧滑删除listView 
 * @author lf2596352
 *
 */
public class MyListView extends ListView implements android.view.View.OnTouchListener,OnGestureListener{
	//实例化手势对象
	private GestureDetector mGestureDetector;
	private OnDeleteListener mListener;
	private boolean mIsDeleteShown;
	private ViewGroup mItemLayout;
	private View mDeleteButton;
	private int mSelectedItem;

	@SuppressLint("ClickableViewAccessibility") public MyListView(Context context,AttributeSet attrs) {
		super(context,attrs);
		mGestureDetector = new GestureDetector(getContext(), this);
		setOnTouchListener(this);
	}
	
	public void setOnDeleteListener(OnDeleteListener l){
		mListener = l;
	}
	
	public interface OnDeleteListener{
		void onDelete(int index);
	}

	@SuppressLint("ClickableViewAccessibility") @Override
	public boolean onTouch(View v, MotionEvent event) {
		//如果删除按钮已经显示了，就将它移除掉，如果删除按钮没有显示，就使用GestureDetector来处理当前手势。
		if(mIsDeleteShown){
			mItemLayout.removeView(mDeleteButton);
			mDeleteButton = null;
			mIsDeleteShown = false;
			return false;
		}else{
			return mGestureDetector.onTouchEvent(event);
		}
	}

	/**
	 * 按下
	 */
	@Override
	public boolean onDown(MotionEvent e) {
		if(!mIsDeleteShown){
			mSelectedItem = pointToPosition((int)e.getX(), (int)e.getY());
		}
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		
	}

	/**
	 * 快速滑动
	 */
	@SuppressLint("InflateParams") @Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if(!mIsDeleteShown&&Math.abs(velocityX)>Math.abs(velocityY)){
			mDeleteButton = LayoutInflater.from(getContext()).inflate(R.layout.delete_btn, null);
			mDeleteButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mItemLayout.removeView(mDeleteButton);
					mDeleteButton = null;
					mIsDeleteShown = false;
					mListener.onDelete(mSelectedItem);
				}
			});
			mItemLayout = (ViewGroup) getChildAt(mSelectedItem-getFirstVisiblePosition());
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			params.addRule(RelativeLayout.CENTER_VERTICAL);
			mItemLayout.addView(mDeleteButton,params);
			mIsDeleteShown = true;
		}
		return false;
	}
}
