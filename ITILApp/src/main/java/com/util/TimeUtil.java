package com.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	public static Timestamp getTimeStamp(){
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		Timestamp currentTimeStamp = new Timestamp(now.getTime());
		return currentTimeStamp;
	}
}
