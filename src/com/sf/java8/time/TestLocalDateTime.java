package com.sf.java8.time;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

import org.junit.Test;

public class TestLocalDateTime {
	
	//6.时区的处理ZoneDate、ZoneTime、ZoneDateTime
	@Test
	public void test8(){
		LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Amman"));
		System.out.println(ldt);
		
		LocalDateTime ldt2 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
		ZonedDateTime zdt = ldt2.atZone(ZoneId.of("Asia/Shanghai"));	//设置这个时间的时区	2018-01-16T15:31:24.889+08:00[Asia/Shanghai]，+8时区
		System.out.println(zdt);	
	}
	@Test
	public void test7(){
		Set<String> set = ZoneId.getAvailableZoneIds();
		set.forEach(System.out::println);
	}
	
	//5.DateTimeFormatter：格式化时间/日期
	@Test
	public void test6(){
		DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
		LocalDateTime ldt = LocalDateTime.now();
		String dateStr = dtf.format(ldt);
		System.out.println(dateStr);
		
		System.out.println("---------------------------");
		
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String dateStr2 = ldt.format(dtf2);
		System.out.println(dateStr2);
		
		System.out.println("---------------------------");
		
		LocalDateTime newDate = LocalDateTime.parse("2019-12-05 12:12:12", dtf2);
		System.out.println(newDate);
		
		System.out.println("---------------------------");
		
		TemporalAccessor ta = dtf2.parse("2029-11-15 12:12:12");
		System.out.println(LocalDateTime.from(ta));
	}

	//4.TemporalAdjuster：时间校正器
	@Test
	public void test5(){
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		
		LocalDateTime ldt2 = ldt.withDayOfMonth(10);		//直接修改日期，将月中的日指定为10，生成新实例
		System.out.println(ldt2);
		
		LocalDateTime ldt3 = ldt.with(TemporalAdjusters.lastDayOfYear());				//TemporalAdjusters：时间校正器工具类
		System.out.println(ldt3);
		
		//自定义：下一个工作日
		LocalDateTime ldt4 = ldt.with((t)->{
			LocalDateTime l = (LocalDateTime)t;
			DayOfWeek dow = l.getDayOfWeek();
			if (dow.equals(DayOfWeek.FRIDAY)) {
				return l.plusDays(3);
			} else if (dow.equals(DayOfWeek.SATURDAY)) {
				return l.plusDays(2);
			} else {
				return l.plusDays(1);
			}
		});
		System.out.println(ldt4);
		
	}
	
	//3.Duration：计算两个时间之间间隔的，Period计算两个日期之间间隔的
	@Test
	public void test4(){
		LocalDate ld1 = LocalDate.of(2017, 11, 15);
		LocalDate ld2 = LocalDate.now();
		Period period = Period.between(ld1, ld2);
		System.out.println(period.getYears());
		System.out.println(period.getMonths());
		System.out.println(period.getDays());
	}
	@Test
	public void test3() throws Exception{
		Instant ins1 = Instant.now();
		Thread.sleep(2000);
		Instant ins2 = Instant.now();
		Duration d = Duration.between(ins1, ins2);
		System.out.println(d.toMillis());
		
		System.out.println("-----------------------------------");
		
		LocalDateTime ldt1 = LocalDateTime.now();
		Thread.sleep(2000);
		LocalDateTime ldt2 = LocalDateTime.now();
		Duration d2 = Duration.between(ldt1, ldt2);
		System.out.println(d2.toMillis());
	}
	
	//2.Instatnt：时间戳（以Unix元年 1970年1月1日0时0分0秒 到某个时间之间的毫秒值）
	@Test
	public void test2(){
		Instant ins1 = Instant.now();	//默认获取的是UTC时区（格林威治时间GMT）
		System.out.println(ins1);
		
		OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(8));		//现在时间等于 UTC时间+8
		System.out.println(odt);
		
		System.out.println(ins1.toEpochMilli());	//1970到现在的毫秒
		
		Instant ins2 = Instant.ofEpochSecond(1);	//1970加上1秒
		System.out.println(ins2);
	}
	
	//1.LocalDate	LocalTime	LocalDateTime，使用方式一样，给人读的
	@Test
	public void test1(){
		LocalDateTime ldt = LocalDateTime.now();	//现在系统时间
		System.out.println(ldt);		//2018-01-16T13:44:45.363		ISO8601格式标准
		
		LocalDateTime ldt2 = ldt.plusYears(2);	//加两年
		System.out.println(ldt2);
		
		LocalDateTime ldt3 = ldt.minusMonths(2);	//减两月
		System.out.println(ldt3);
		
		System.out.println(ldt.getYear());
		System.out.println(ldt.getMonthValue());
		System.out.println(ldt.getDayOfMonth());
		System.out.println(ldt.getHour());
		System.out.println(ldt.getMinute());
		System.out.println(ldt.getSecond());
		
		LocalDateTime ldt4 = LocalDateTime.of(2018, 1, 16, 13, 45, 30);
		System.out.println(ldt4);
	}
	
}
