package com.sf.java8.forkjoin;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/*
 * Fork-Join框架
 * 大任务拆分为直到临界值的无数个小任务，小任务运算结果合成为一个。
 * 
 * RecursiveAction 	compute();无返回值
 * RecursiveTask	compute();有返回值
 */
public class ForkJoinCal2 extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3521026986838365927L;
	
	private Long start;
	private Long end;

	private static final long THRESHOLD = 10000;		//拆分的临界值
	
	
	public ForkJoinCal2(Long start, Long end) {
		this.start = start;
		this.end = end;
	}


	//累加
	@Override
	protected void compute() {
		try {
			long length = end - start;
			
			if (length<=THRESHOLD) {
				for (long i = start; i <= end; i++) {
					System.out.println(Thread.currentThread().getName()+"----"+i);
				}
				
			} else {
				
				long middle = (start + end)/2;
				
				ForkJoinTask<Void> left = new ForkJoinCal2(start, middle);
				left.fork();		//拆分（创建）子任务，同时压入线程队列，让pool调度
				
				ForkJoinTask<Void> right = new ForkJoinCal2(middle + 1, end);
				right.fork();		//拆分（创建）子任务，同时压入线程队列，让pool调度
				
//				left.join();
//				right.join();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
