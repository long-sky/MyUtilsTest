package com.example.main;

import com.example.viewex.activity.AnimatorLoadingActivity;
import com.example.viewex.activity.DateTimeActivity;
import com.example.viewex.activity.MagnetIvActivity;
import com.example.viewex.activity.MyListViewActivity;
import com.example.viewex.activity.RulerTestActivity;
import com.example.viewex.activity.SVGImageActivity;
import com.example.viewex.activity.SwitchBtnActivity;
import com.example.viewex.activity.TitleBarTestActivity;
import com.example.viewex.service.PendentServes;
import com.example.viewex.widget.dialog.RuningDialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn1 = (Button) findViewById(R.id.simple_hello_view);
		btn2 = (Button) findViewById(R.id.count_view);
		btn3 = (Button) findViewById(R.id.title_bar_view);
		btn4 = (Button) findViewById(R.id.delete_list_view);
		btn5 = (Button) findViewById(R.id.switch_btn);
		btn6 = (Button) findViewById(R.id.svg_btn);
		btn7 = (Button) findViewById(R.id.run_btn);
		btn8 = (Button) findViewById(R.id.loading_btn);
		btn9 = (Button) findViewById(R.id.ruler_btn);
		btn10 = (Button) findViewById(R.id.date_time_btn);
		btn11 = (Button) findViewById(R.id.magnet_imageview_btn);
		btn12 = (Button) findViewById(R.id.proscenium_pendent_btn);

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn8.setOnClickListener(this);
		btn9.setOnClickListener(this);
		btn10.setOnClickListener(this);
		btn11.setOnClickListener(this);
		btn12.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.simple_hello_view:
			setContentView(R.layout.simple_layout_test);
			break;
		case R.id.count_view:
			setContentView(R.layout.count_view);
			break;
		case R.id.title_bar_view:
			intent = new Intent(MainActivity.this, TitleBarTestActivity.class);
			startActivity(intent);
			break;
		case R.id.delete_list_view:
			intent = new Intent(MainActivity.this, MyListViewActivity.class);
			startActivity(intent);
			break;
		case R.id.switch_btn:
			intent = new Intent(MainActivity.this, SwitchBtnActivity.class);
			startActivity(intent);
			break;
		case R.id.svg_btn:
			intent = new Intent(MainActivity.this, SVGImageActivity.class);
			startActivity(intent);
			break;
		case R.id.run_btn:
			RuningDialog dialog = new RuningDialog(this, "正在加载中", R.anim.frame);
			dialog.show();
			break;
		case R.id.loading_btn:
			intent = new Intent(MainActivity.this, AnimatorLoadingActivity.class);
			startActivity(intent);
			break;
		case R.id.ruler_btn:
			intent = new Intent(MainActivity.this, RulerTestActivity.class);
			startActivity(intent);
			break;
		case R.id.date_time_btn:
			intent = new Intent(MainActivity.this, DateTimeActivity.class);
			startActivity(intent);
			break;
		case R.id.magnet_imageview_btn:
			intent = new Intent(MainActivity.this, MagnetIvActivity.class);
			startActivity(intent);
			break;
		case R.id.proscenium_pendent_btn:
			// 开启一个服务使挂件可以一直显示
			this.startService(new Intent(getApplicationContext(), PendentServes.class));
//			new PendentView(this).fun(); // 只显示在当前activity
			break;
		default:
			break;
		}
	}
}
