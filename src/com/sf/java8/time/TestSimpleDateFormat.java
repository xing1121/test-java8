package com.sf.java8.time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class TestSimpleDateFormat {

	//java8线程安全的时间转换
	@Test
	public void test3() throws Exception {
		//创建一个时间格式转换器
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		//创建一个任务
		Callable<LocalDate> task = ()->LocalDate.parse("20161218", formatter);
		//新建容量为10的线程池
		ExecutorService pool = Executors.newFixedThreadPool(10);
		//存线程池执行任务的结果的集合
		List<Future<LocalDate>> results = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			results.add(pool.submit(task));//使用线程池执行任务
		}
		//打印输出
		for (Future<LocalDate> future : results) {
			System.out.println(future.get());
		}
		//关闭线程池
		pool.shutdown();
		
	}
	
	//原来的线程安全的时间转换（使用ThreadLocal）
	@Test
	public void test2() throws Exception {
		Callable<Date> task = new Callable<Date>() {
			@Override
			public Date call() throws Exception {
				return DateFormatThreadLocal.convert("20161218");
			}
		};
		
		ExecutorService pool = Executors.newFixedThreadPool(10);
		
		List<Future<Date>> results = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			results.add(pool.submit(task));
		}
		
		for (Future<Date> future : results) {
			System.out.println(future.get());
		}
		
		pool.shutdown();
	}
	
	//原来的线程不安全的时间转换
	@Test
	public void test1() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		Callable<Date> task = new Callable<Date>() {
			@Override
			public Date call() throws Exception {
				return sdf.parse("20161218");
			}
		};
		
		ExecutorService pool = Executors.newFixedThreadPool(10);
		
		List<Future<Date>> results = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			results.add(pool.submit(task));
		}
		
		for (Future<Date> future : results) {
			System.out.println(future.get());
		}
		
		pool.shutdown();
	}
	
}
