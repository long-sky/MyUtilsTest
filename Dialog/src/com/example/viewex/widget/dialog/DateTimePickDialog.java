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
 * ʱ������ѡ��ؼ�
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
	 * ���õĸ�activity
	 * @param initDateTime
	 * ��ʼ����ʱ��ֵ����Ϊ�������ڵı��������ʱ���ʼֵ
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
			mInitDateTime = calendar.get(Calendar.YEAR) + "��"
					+ calendar.get(Calendar.MONTH) + "��"
					+ calendar.get(Calendar.DAY_OF_MONTH) + "�� "
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
	 * ʵ�ֽ���ʼ����ʱ��xxxx��xx��xx�� xx:xx ��ֳ��� �� �� ʱ �� ��,����ֵ��calendar
	 * 
	 * @param initDateTime
	 *            ��ʼ����ʱ��ֵ �ַ�����
	 * @return Calendar
	 */
	private Calendar getCalendarByInintData(String initDateTime) {
		Calendar calendar = Calendar.getInstance();

		// ����ʼ����ʱ��xxxx��xx��xx�� xx:xx ��ֳ��� �� �� ʱ �� ��
		String date = StringUtil.spliteString(initDateTime, "��", "index", "front"); // ����
		String time = StringUtil.spliteString(initDateTime, "��", "index", "back"); // ʱ��

		String yearStr = StringUtil.spliteString(date, "��", "index", "front"); // ���
		String monthAndDay = StringUtil.spliteString(date, "��", "index", "back"); // ����

		String monthStr = StringUtil.spliteString(monthAndDay, "��", "index", "front"); // ��
		String dayStr = StringUtil.spliteString(monthAndDay, "��", "index", "back"); // ��

		String hourStr = StringUtil.spliteString(time, ":", "index", "front"); // ʱ
		String minuteStr = StringUtil.spliteString(time, ":", "index", "back"); // ��

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
				.setPositiveButton("����", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						inputDate.setText(mDateTime);
					}
				})
				.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
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
			mDateTime = DateUtil.SdfDate("yyyy��MM��dd�� HH:mm", calendar.getTime());
		}else{
			calendar.set(mDatePicker.getYear(), mDatePicker.getMonth(),
					mDatePicker.getDayOfMonth());
			mDateTime = DateUtil.SdfDate("yyyy��MM��dd��", calendar.getTime());
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