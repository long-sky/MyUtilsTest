package com.example.viewex.widget.dialog;

import java.util.Calendar;

import com.example.dialog.R;
import com.example.viewex.util.DateUtil;
import com.example.viewex.util.StringUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
/**
 * 时间日期选择控件
 * @author lf2596352
 *
 */
@SuppressLint("InflateParams") public class DateTimePickDialog implements OnDateChangedListener, OnTimeChangedListener{
	private DatePicker mDatePicker;
	private TimePicker mTimePicker;
	private AlertDialog mAlertDialog;
	private String mDateTime;
	private String mInitDateTime;
	private Activity mActivity;
	private boolean mIsShowTime = true;
	
	/**
	 * @param activity
	 * 调用的父activity
	 * @param initDateTime
	 * 初始日期时间值，作为弹出窗口的标题和日期时间初始值
	 */
	public DateTimePickDialog(Activity activity, String initDateTime) {
		this.mActivity = activity;
		this.mInitDateTime = initDateTime;
	}
	
	public void init(DatePicker datePicker, TimePicker timePicker){
		Calendar calendar = Calendar.getInstance();
		if (!(null == mInitDateTime || "".equals(mInitDateTime))) {
			calendar = this.getCalendarByInintData(mInitDateTime);
		} else {
			mInitDateTime = calendar.get(Calendar.YEAR) + "年"
					+ calendar.get(Calendar.MONTH) + "月"
					+ calendar.get(Calendar.DAY_OF_MONTH) + "日 "
					+ calendar.get(Calendar.HOUR_OF_DAY) + ":"
					+ calendar.get(Calendar.MINUTE);
		}
		datePicker.init(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), this);
		timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
		timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
	}
	
	/**
	 * 实现将初始日期时间xxxx年xx月xx日 xx:xx 拆分成年 月 日 时 分 秒,并赋值给calendar
	 * 
	 * @param initDateTime
	 *            初始日期时间值 字符串型
	 * @return Calendar
	 */
	private Calendar getCalendarByInintData(String initDateTime) {
		Calendar calendar = Calendar.getInstance();

		// 将初始日期时间xxxx年xx月xx日 xx:xx 拆分成年 月 日 时 分 秒
		String date = StringUtil.spliteString(initDateTime, "日", "index", "front"); // 日期
		String time = StringUtil.spliteString(initDateTime, "日", "index", "back"); // 时间

		String yearStr = StringUtil.spliteString(date, "年", "index", "front"); // 年份
		String monthAndDay = StringUtil.spliteString(date, "年", "index", "back"); // 月日

		String monthStr = StringUtil.spliteString(monthAndDay, "月", "index", "front"); // 月
		String dayStr = StringUtil.spliteString(monthAndDay, "月", "index", "back"); // 日

		String hourStr = StringUtil.spliteString(time, ":", "index", "front"); // 时
		String minuteStr = StringUtil.spliteString(time, ":", "index", "back"); // 分

		int currentYear = Integer.valueOf(yearStr.trim()).intValue();
		int currentMonth = Integer.valueOf(monthStr.trim()).intValue() - 1;
		int currentDay = Integer.valueOf(dayStr.trim()).intValue();
		int currentHour = Integer.valueOf(hourStr.trim()).intValue();
		int currentMinute = Integer.valueOf(minuteStr.trim()).intValue();

		calendar.set(currentYear, currentMonth, currentDay, currentHour,
				currentMinute);
		return calendar;
	}
	
	public AlertDialog showDialog(final EditText inputDate,boolean isShowTime){
		LinearLayout linearLayout = (LinearLayout) mActivity.getLayoutInflater().inflate(R.layout.datetime_dialog, null);
		mDatePicker = (DatePicker) linearLayout.findViewById(R.id.datepicker);
		mTimePicker = (TimePicker) linearLayout.findViewById(R.id.timepicker);
		mIsShowTime = isShowTime;
		
		if(!mIsShowTime){
			mTimePicker.setVisibility(View.GONE);
		}else{
			mTimePicker.setVisibility(View.VISIBLE);
		}
		init(mDatePicker, mTimePicker);
		mTimePicker.setIs24HourView(true);
		mTimePicker.setOnTimeChangedListener(this);
		
		mAlertDialog = new AlertDialog.Builder(mActivity)
				.setTitle(mInitDateTime)
				.setIcon(R.drawable.ic_launcher)
				.setView(linearLayout)
				.setPositiveButton("设置", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						inputDate.setText(mDateTime);
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
//						inputDate.setText("");
					}
				}).show();
		onDateChanged(null, 0, 0, 0);
		return mAlertDialog;
	}

	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		Calendar calendar = Calendar.getInstance();
		if(mIsShowTime){
			calendar.set(mDatePicker.getYear(), mDatePicker.getMonth(),
					mDatePicker.getDayOfMonth(), mTimePicker.getCurrentHour(),
					mTimePicker.getCurrentMinute());
			mDateTime = DateUtil.SdfDate("yyyy年MM月dd日 HH:mm", calendar.getTime());
		}else{
			calendar.set(mDatePicker.getYear(), mDatePicker.getMonth(),
					mDatePicker.getDayOfMonth());
			mDateTime = DateUtil.SdfDate("yyyy年MM月dd日", calendar.getTime());
		}
		mAlertDialog.setTitle(mDateTime);
	}

	@Override
	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		onDateChanged(null, 0, 0, 0);
	}
	
	public void setIcon(int drawable){
		mAlertDialog.setIcon(drawable);
	}
	
	public void setTitle(String str){
		mAlertDialog.setTitle(str);
	}
}
