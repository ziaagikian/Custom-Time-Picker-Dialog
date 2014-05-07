package com.zia.customui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class DemoActivity extends Activity {

	private TextView mCurrentTime;
	DateFormat mTimeFormat;
	private int mPickerTheme;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo);
		mTimeFormat = new SimpleDateFormat("HH:mm");
		mPickerTheme = R.style.AppTheme;
		initView();

	}

	private void initView() {
		mCurrentTime = (TextView) findViewById(R.id.current_time);
		String curTime = getCurrentTime();
		mCurrentTime.setText(curTime);
	}

	private String getCurrentTime() {

		Calendar curDate = Calendar.getInstance();
		int setMinute = 0;
		int curMint = curDate.get(Calendar.MINUTE);
		if (curMint > 0 && curMint < 15) {
			setMinute = 0;
		} else if (curMint >= 15 && curMint < 30) {
			setMinute = 15;
		} else if (curMint >= 30 && curMint < 45) {
			setMinute = 30;
		} else {
			setMinute = 45;
		}
		curDate.set(curDate.get(Calendar.YEAR), curDate.get(Calendar.MONTH),
				curDate.get(Calendar.DATE), curDate.get(Calendar.HOUR_OF_DAY),
				setMinute);
		return mTimeFormat.format(curDate.getTime());
	}

	public void onClickDialogOption(View v) {
		int id = v.getId();
		boolean is24HrView = false;
		switch (id) {
		case R.id.button1:
			is24HrView = true;
			break;

		// case R.id.button2:
		// is24HrView = false;
		// break;
		}
		try {
			showPickerDialog(is24HrView);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	private void showPickerDialog(boolean is24HrView) throws ParseException {

		String time = mCurrentTime.getText().toString();
		Date dialogDate = mTimeFormat.parse(time);
		int hourOfDay = dialogDate.getHours();
		int minute = dialogDate.getMinutes();
		CustomTimePickerDialog dialog = new CustomTimePickerDialog(this,/*
																		 * mPickerTheme
																		 * ,
																		 */
		mTimePickerListener, hourOfDay, minute, is24HrView);
		dialog.show();
	}

	private OnTimeSetListener mTimePickerListener = new OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mCurrentTime.setText(hourOfDay + ":" + minute);
		}
	};

}
