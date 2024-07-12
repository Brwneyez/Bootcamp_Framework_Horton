package com.TN.utilities;

import java.time.Duration;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class Util {

	public static final int IMPLICIT_WAIT_TIME = 10; 
	public static final int PAGELOAD_TIME_WAIT = 10; 
	public static final int SCRIPT_TIME_WAIT = 10; 
	
	public static String emailDateTimeStamp() {
		Date date = new Date();
		String timeStamp= date.toString().replace(" ","_").replace(":","_" );
		return "Sunshine17" + timeStamp + "@yahoo.com";
	}
}
