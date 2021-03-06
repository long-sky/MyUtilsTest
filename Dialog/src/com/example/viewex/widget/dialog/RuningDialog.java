package com.example.viewex.widget.dialog;

import com.example.dialog.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 人物奔跑loading dialog
 * @author lf2596352
 *
 */
public class RuningDialog extends ProgressDialog{
	@SuppressWarnings("unused")
	private Context mContext;
	//加载文字
	private String mTipText;
	//动画资源id
	private int mResId;
	private ImageView mImageView;
	private TextView mLoadingTv;
	private AnimationDrawable mAnimation;

	public RuningDialog(Context context,String str,int id) {
		super(context);
		this.mContext = context;
		this.mTipText = str;
		this.mResId = id;
		setCanceledOnTouchOutside(true);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initData();
	}
	
	private void initView(){
		setContentView(R.layout.running_dialog);
		mLoadingTv = (TextView) findViewById(R.id.loadingTv);
		mImageView = (ImageView) findViewById(R.id.loadingIv);
	}
	
	private void initData(){
		mImageView.setBackgroundResource(mResId);
		// 通过ImageView对象拿到背景显示的AnimationDrawable
		mAnimation = (AnimationDrawable) mImageView.getBackground();
		// 为了防止在onCreate方法中只显示第一帧的解决方案之一
		mImageView.post(new Runnable() {
			@Override
			public void run() {
				mAnimation.start();
			}
		});
		mLoadingTv.setText(mTipText);
	}
	
	public void setContent(String str) {
		mLoadingTv.setText(str);
	}
}
