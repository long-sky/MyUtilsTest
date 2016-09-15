package com.example.viewex.activity;

import java.util.Date;

import com.example.dialog.R;
import com.example.viewex.util.DateUtil;
import com.example.viewex.widget.dialog.DateTimePickDialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class DateTimeActivity extends Activity {
	private EditText mDateTime;
	private String mInitDateTime = DateUtil.SdfDate("yyyy年MM月dd日 HH:mm", new Date());
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_time);
		
		mDateTime = (EditText) findViewById(R.id.time_et);
		mDateTime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DateTimePickDialog dateTimePicKDialog = new DateTimePickDialog(
						DateTimeActivity.this, mInitDateTime);
				dateTimePicKDialog.showDialog(mDateTime,false);
				dateTimePicKDialog.setIcon(R.drawable.ic_launcher);
				dateTimePicKDialog.setTitle("自定义时间选择控件");
			}
		});
	}
}
