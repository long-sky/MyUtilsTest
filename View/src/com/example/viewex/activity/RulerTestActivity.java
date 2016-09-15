package com.example.viewex.activity;

import com.example.view.R;
import com.example.viewex.widget.Ruler;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

public class RulerTestActivity extends Activity {
	private Ruler mRuler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ruler_test);
		
		mRuler = (Ruler) findViewById(R.id.ruler);
		Bitmap barScale = BitmapFactory.decodeResource(getResources(), R.drawable.a003);
		Bitmap bgscale = BitmapFactory.decodeResource(getResources(), R.drawable.a001);
		Bitmap cover = BitmapFactory.decodeResource(getResources(), R.drawable.a003);
		Bitmap point = BitmapFactory.decodeResource(getResources(), R.drawable.a002);
		mRuler.initalSrc(barScale, bgscale, cover, point);
	}
}
