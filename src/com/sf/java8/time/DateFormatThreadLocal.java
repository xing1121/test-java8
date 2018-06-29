package com.sf.java8.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//原来的线程安全的时间转换（使用ThreadLocal）
public class DateFormatThreadLocal {

	private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>(){
	    protected DateFormat initialValue() {
	        return new SimpleDateFormat("yyyyMMdd");
	    }
	};
	
	public static Date convert(String source){
		try {
			return df.get().parse(source);
		} catch (Exception e) {
			return null;
		}
	}
	
}
