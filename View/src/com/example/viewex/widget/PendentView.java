package com.example.viewex.widget;

import com.example.view.R;
import com.example.viewex.util.ToastUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * 前台挂件
 * 
 * @author lf2596352
 * 
 */
@SuppressLint("InflateParams")
public class PendentView extends View {
	// 如果是想显示歌词则继承TextView并复写ondraw方法。
	// 开启一个线程不断的调用ondraw方法去更改你所写的继承自TextView的内容
	// 这里随便写了个集成自view的= =这个不是重点
	private Context c;
	private WindowManager mWM; // WindowManager
	private WindowManager.LayoutParams mWMParams; // WindowManager参数
	private View win;
	private int tag = 0;
	private int oldOffsetX;
	private int oldOffsetY;

	public PendentView(Context context) {
		super(context);
		c = context;
	}

	@SuppressLint({ "ClickableViewAccessibility", "ShowToast" })
	public void fun() {
		// 设置载入view WindowManager参数
		mWM = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
		win = LayoutInflater.from(c).inflate(R.layout.pendent_view, null);
		win.setBackgroundColor(Color.TRANSPARENT);
		// 这里是随便载入的一个布局文件

		win.setOnTouchListener(new OnTouchListener() {
			// 触屏监听
			float lastX, lastY;

			public boolean onTouch(View v, MotionEvent event) {
				final int action = event.getAction();

				float x = event.getX();
				float y = event.getY();

				if (tag == 0) {
					oldOffsetX = mWMParams.x; // 偏移量
					oldOffsetY = mWMParams.y; // 偏移量
				}

				if (action == MotionEvent.ACTION_DOWN) {
					lastX = x;
					lastY = y;

				} else if (action == MotionEvent.ACTION_MOVE) {
					mWMParams.x += (int) (x - lastX); // 偏移量
					mWMParams.y += (int) (y - lastY); // 偏移量

					tag = 1;
					mWM.updateViewLayout(win, mWMParams);
				}

				else if (action == MotionEvent.ACTION_UP) {
					int newOffsetX = mWMParams.x;
					int newOffsetY = mWMParams.y;
					if (oldOffsetX == newOffsetX && oldOffsetY == newOffsetY) {
						ToastUtil.tip(c, "鬼知道什么用");
					} else {
						tag = 0;
					}
				}
				return true;
			}
		});

		WindowManager wm = mWM;
		WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
		mWMParams = wmParams;
		wmParams.type = 2002; // type是关键，这里的2002表示系统级窗口，你也可以试试2003。
		wmParams.flags = 40;// 这句设置桌面可控

		wmParams.width = 50;
		wmParams.height = 50;
		wmParams.format = -3; // 透明

		wm.addView(win, wmParams);// 这句是重点 给WindowManager中丢入刚才设置的值
									// 只有addview后才能显示到页面上去。
		// 注册到WindowManager win是要刚才随便载入的layout，wmParams是刚才设置的WindowManager参数集
		// 效果是将win注册到WindowManager中并且它的参数是wmParams中设置
	}
}
