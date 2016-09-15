package com.example.viewex.activity;

import com.example.imageview.R;
import com.example.viewex.adapter.SvgImagesAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class SVGImageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_svgimage);
		
		GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new SvgImagesAdapter(this));
	}
}
