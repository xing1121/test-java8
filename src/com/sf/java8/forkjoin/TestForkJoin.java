package com.sf.java8.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

import org.junit.Test;

public class TestForkJoin {
	
	/*
	 * 打印1-1000000
	 */
	@Test
	public void test4() throws Exception {
		Instant start = Instant.now();
		
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinTask<Void> task = new ForkJoinCal2(1L,100000L);		//打印1-10万
		pool.invoke(task);		//返回任务运行结果
		
		Instant end = Instant.now();
		
		System.out.println("当前线程："+Thread.currentThread().getName()+"，用时："+Duration.between(start, end).toMillis()+"毫秒");
	}
	
	/*
	 * ForkJoin框架计算1-100亿
	 */
	@Test
	public void test1() throws Exception{
		Instant start = Instant.now();
		
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinTask<Long> task = new ForkJoinCalculate(0L, 10000000000L);		//计算1-100亿
		pool.submit(task);		//返回任务运行结果
		System.out.println("1");
		 Long sum = task.join();
		 
		Instant end = Instant.now();
		
		System.out.println(Thread.currentThread().getName()+"总和："+sum+"，用时："+Duration.between(start, end).toMillis()+"毫秒");
	}

	/*
	 * 普通for单线程计算1-100亿
	 */
	@Test
	public void test2(){
		long sum = 0L;
		
		Instant start = Instant.now();
		
		for (long i = 1; i <= 10000000000L; i++) {
			sum +=i;
		}
		
		Instant end = Instant.now();
		
		System.out.println("总和："+sum+"，用时："+Duration.between(start, end).toMillis()+"毫秒");
		System.out.println("总和："+sum+"，用时："+Duration.between(start, end).get(ChronoUnit.SECONDS)+"秒");
	}
	
	/*
	 * java8 并行流
	 * 		.sequential() 转换为串行流
	 * 		.parallel() 转换为并行流
	 */
	@Test
	public void test3(){
		Instant start = Instant.now();
		
		long sum = LongStream.rangeClosed(0, 10000000000L)
				  .parallel()
				  .reduce(0, Long::sum);
		Instant end = Instant.now();
		
		System.out.println("总和："+sum+"，用时："+Duration.between(start, end).toMillis()+"毫秒");
	}
	
}
