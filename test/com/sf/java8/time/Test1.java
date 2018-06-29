package com.sf.java8.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.junit.Test;

public class Test1 {

	private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(){
		protected SimpleDateFormat initialValue(){
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};
	
	public static void main(String[] args) {
			
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(15);
		
		for (int i = 0; i < 50; i++) {
			pool.submit(()->{
				
				Date date = str22Date("2018-06-28");
				System.out.println(date);
				
			});
		}
		
		pool.shutdown();
	}
	
	@Test
	public void test1(){
		
//		String str = date2Str(new Date(), "yyyy-MM-dd HH:mm:ss");
//		System.out.println(str);
		
//		Date date2 = str2Date("2018-06-22", "yyyy-MM-dd");	//异常
//		System.out.println(date2);
		
//		Date date1 = str2Date("2018-06-22 15:30:08", "yyyy-MM-dd HH:mm:ss");
//		System.out.println(date1);
	}
	
	// Date转String，随便转
	public static String date2Str(Date date, String pattern){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
		String str = dtf.format(date.toInstant().atZone(ZoneId.systemDefault()));
		return str;
	}
	
	// String转Date，LocalDateTime只能转时间+日期
	public static Date str2Date(String str, String pattern){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
		TemporalAccessor temp = dtf.parse(str);
		
		return Date.from(LocalDateTime.from(temp).atZone(ZoneId.systemDefault()).toInstant());
	}
	
	// String转Date，随便转
	public static Date str22Date(String str){
		try {
			return threadLocal.get().parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
