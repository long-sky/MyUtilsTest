package com.example.viewex.activity;

import com.example.imageview.R;
import com.example.viewex.util.ToastUtil;
import com.example.viewex.widget.imageview.MagnetImageView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MagnetIvActivity extends Activity implements OnClickListener {
	private MagnetImageView mJoke, mIdea, mConstellation, mRecommend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_magnet_iv);

		mJoke = (MagnetImageView) findViewById(R.id.c_joke);
		mIdea = (MagnetImageView) findViewById(R.id.c_idea);
		mConstellation = (MagnetImageView) findViewById(R.id.c_constellation);
		mRecommend = (MagnetImageView) findViewById(R.id.c_recommend);

		mJoke.setOnClickListener(this);
		mIdea.setOnClickListener(this);
		mConstellation.setOnClickListener(this);
		mRecommend.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.c_joke) {
			ToastUtil.tip(MagnetIvActivity.this, "joke");
		} else if (id == R.id.c_idea) {
			ToastUtil.tip(MagnetIvActivity.this, "idea");
		} else if (id == R.id.c_constellation) {
			ToastUtil.tip(MagnetIvActivity.this, "constellation");
		} else if (id == R.id.c_recommend) {
			ToastUtil.tip(MagnetIvActivity.this, "recommend");
		}
	}
}
