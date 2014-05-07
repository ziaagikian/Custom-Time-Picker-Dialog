package com.zia.customui;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;

public class CustomTimePickerDialog extends TimePickerDialog {
	public static final int TIME_PICKER_INTERVAL = 15;
	private boolean mEventIgnored = false;

	public CustomTimePickerDialog(Context context, int theme,
			OnTimeSetListener callBack, int hourOfDay, int minute,
			boolean is24HourView) {
		super(context, theme, callBack, hourOfDay, minute, is24HourView);
	}
	
	public CustomTimePickerDialog(Context context, 
			OnTimeSetListener callBack, int hourOfDay, int minute,
			boolean is24HourView) {
		super(context, callBack, hourOfDay, minute, is24HourView);
	}
	@Override
	public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
		super.onTimeChanged(timePicker, hourOfDay, minute);
		if (!mEventIgnored) {
			minute = getRoundedMinute(minute);
			mEventIgnored = true;
			timePicker.setCurrentMinute(minute);
			mEventIgnored = false;
		}
	}
	
	private int getRoundedMinute(int minute) {
		if (minute % TIME_PICKER_INTERVAL != 0) {
			int minuteFloor = minute - (minute % TIME_PICKER_INTERVAL);
			minute = minuteFloor
					+ (minute == minuteFloor + 1 ? TIME_PICKER_INTERVAL : 0);
			if (minute == 60)
				minute = 0;
		}
		return minute;
	}

}
