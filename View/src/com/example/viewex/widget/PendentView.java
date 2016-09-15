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
 * ǰ̨�Ҽ�
 * 
 * @author lf2596352
 * 
 */
@SuppressLint("InflateParams")
public class PendentView extends View {
	// ���������ʾ�����̳�TextView����дondraw������
	// ����һ���̲߳��ϵĵ���ondraw����ȥ��������д�ļ̳���TextView������
	// �������д�˸�������view��= =��������ص�
	private Context c;
	private WindowManager mWM; // WindowManager
	private WindowManager.LayoutParams mWMParams; // WindowManager����
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
		// ��������view WindowManager����
		mWM = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
		win = LayoutInflater.from(c).inflate(R.layout.pendent_view, null);
		win.setBackgroundColor(Color.TRANSPARENT);
		// ��������������һ�������ļ�

		win.setOnTouchListener(new OnTouchListener() {
			// ��������
			float lastX, lastY;

			public boolean onTouch(View v, MotionEvent event) {
				final int action = event.getAction();

				float x = event.getX();
				float y = event.getY();

				if (tag == 0) {
					oldOffsetX = mWMParams.x; // ƫ����
					oldOffsetY = mWMParams.y; // ƫ����
				}

				if (action == MotionEvent.ACTION_DOWN) {
					lastX = x;
					lastY = y;

				} else if (action == MotionEvent.ACTION_MOVE) {
					mWMParams.x += (int) (x - lastX); // ƫ����
					mWMParams.y += (int) (y - lastY); // ƫ����

					tag = 1;
					mWM.updateViewLayout(win, mWMParams);
				}

				else if (action == MotionEvent.ACTION_UP) {
					int newOffsetX = mWMParams.x;
					int newOffsetY = mWMParams.y;
					if (oldOffsetX == newOffsetX && oldOffsetY == newOffsetY) {
						ToastUtil.tip(c, "��֪��ʲô��");
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
		wmParams.type = 2002; // type�ǹؼ��������2002��ʾϵͳ�����ڣ���Ҳ��������2003��
		wmParams.flags = 40;// �����������ɿ�

		wmParams.width = 50;
		wmParams.height = 50;
		wmParams.format = -3; // ͸��

		wm.addView(win, wmParams);// ������ص� ��WindowManager�ж���ղ����õ�ֵ
									// ֻ��addview�������ʾ��ҳ����ȥ��
		// ע�ᵽWindowManager win��Ҫ�ղ���������layout��wmParams�Ǹղ����õ�WindowManager������
		// Ч���ǽ�winע�ᵽWindowManager�в������Ĳ�����wmParams������
	}
}