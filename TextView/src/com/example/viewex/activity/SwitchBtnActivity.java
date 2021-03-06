package com.example.viewex.activity;

import com.example.textview.R;
import com.example.viewex.widget.textview.CheckSwitchButton;
import com.example.viewex.widget.textview.SlideSwitchView;
import com.example.viewex.widget.textview.SlideSwitchView.OnSwitchChangedListener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class SwitchBtnActivity extends Activity {
	private ToggleButton mTogBtn;
	private CheckSwitchButton mCheckSwithcButton;
	private CheckSwitchButton mEnableCheckSwithcButton;
	private SlideSwitchView mSlideSwitchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_switch_btn);
		initView();
	}
	
	private void initView() {
		mTogBtn = (ToggleButton) findViewById(R.id.mTogBtn); // 获取到控件
		mTogBtn.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(isChecked){
					Toast.makeText(SwitchBtnActivity.this, "选中", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(SwitchBtnActivity.this, "未选中", Toast.LENGTH_SHORT).show();
				}
			}
		});// 添加监听事件
		mCheckSwithcButton = (CheckSwitchButton)findViewById(R.id.mCheckSwithcButton);
		mEnableCheckSwithcButton = (CheckSwitchButton)findViewById(R.id.mEnableCheckSwithcButton);
		mCheckSwithcButton.setChecked(false);
		mCheckSwithcButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					mEnableCheckSwithcButton.setEnabled(false);
					mSlideSwitchView.setEnabled(false);
				}else{
					mEnableCheckSwithcButton.setEnabled(true);
					mSlideSwitchView.setEnabled(true);
				}
			}
		});
		mSlideSwitchView = (SlideSwitchView) findViewById(R.id.mSlideSwitchView);
		mSlideSwitchView.setOnChangeListener(new OnSwitchChangedListener() {
			@Override
			public void onSwitchChange(SlideSwitchView switchView, boolean isChecked) {
				if(isChecked){
					Toast.makeText(SwitchBtnActivity.this, ""+isChecked, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
