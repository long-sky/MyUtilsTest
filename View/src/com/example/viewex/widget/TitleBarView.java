package com.example.viewex.widget;

import com.example.view.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
/**
 * 组合控件标题栏
 * @author lf2596352
 *
 */
public class TitleBarView extends FrameLayout{
	//标题栏左边按钮
	private Button mBtn;
	//标题
	private TextView mTv;

	@SuppressLint("InflateParams") public TitleBarView(Context context,AttributeSet attrs) {
		super(context,attrs);
		LayoutInflater.from(context).inflate(R.layout.title_bar_view, this);
		mBtn = (Button) findViewById(R.id.button_left);
		mTv = (TextView) findViewById(R.id.title_text);
		mBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((android.app.Activity) getContext()).finish();
			}
		});
	}
	
	public void setTitleText(String title){
		mTv.setText(title);
	}
	
	public void setLeftBtnText(String leftBtnStr){
		mBtn.setText(leftBtnStr);
	}
	
	public void setLeftBtnListener(OnClickListener l){
		mBtn.setOnClickListener(l);
	}
}
