package com.sf.java8.time;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;

public class Test2 {
	
	@Test
	public void test6(){
		LocalDateTime ldt = LocalDateTime.now();
		LocalDateTime ldt2 = ldt.plusDays(3).plusHours(4);
		System.out.println(ldt2);
	}
	
	@Test
	public void test5(){
		Instant start = Instant.now();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Instant end = Instant.now();
		long millis = Duration.between(start, end).toMillis();
		System.out.println(millis);
	}
	
	// java.time.LocalDateTime --> java.util.Date
	@Test
	public void test3(){
		LocalDateTime localDateTime = LocalDateTime.now();
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		System.out.println(date);
	}
	
	// java.util.Date --> java.time.LocalDateTime
	@Test
	public void test2(){
		Date date = new Date();
		Instant instant = date.toInstant();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		System.out.println(localDateTime);
	}
	
	@Test
	public void test1(){
		//获取时间戳，默认获取的是UTC时区
		Instant now = Instant.now();		
		System.out.println(now);			//2018-06-28T03:19:02.988Z
		System.out.println(Date.from(now)); //Thu Jun 28 11:19:02 CST 2018
		// 获取当前时间相对于1970-01-01 00:00:00的毫秒数
		System.out.println(now.toEpochMilli());
		System.out.println(System.currentTimeMillis());
		// 获取时间戳
		Instant now2 = Instant.ofEpochMilli(System.currentTimeMillis());
		System.out.println(now2);
	}
	
}
