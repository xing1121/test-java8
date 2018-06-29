package com.sf.java8.forkjoin;

import java.util.concurrent.RecursiveTask;

/*
 * Fork-Join框架
 * 大任务拆分为直到临界值的无数个小任务，小任务运算结果合成为一个。
 * 
 * RecursiveAction 	compute();无返回值
 * RecursiveTask	compute();有返回值
 */
public class ForkJoinCalculate extends RecursiveTask<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3521026986838365927L;
	
	private Long start;
	private Long end;

	private static final long THRESHOLD = 10000;		//拆分的临界值
	
	public ForkJoinCalculate(Long start,Long end) {
		this.start = start;
		this.end = end;
	}
	
	//累加
	@Override
	protected Long compute() {
		long length = end - start;
		
		if (length<=THRESHOLD) {
			
			long sum = 0;
			
			for (long i = start; i <= end; i++) {
				sum += i;
			}
			
			return sum;
		} else {
			
			long middle = (start + end)/2;
			
			ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
			left.fork();		//拆分（创建）子任务，同时压入线程队列，让pool调度
			
			ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
			right.fork();		//拆分（创建）子任务，同时压入线程队列，让pool调度
			
			return left.join() + right.join();
		}
	}

	
}
