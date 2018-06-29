package com.sf.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.sf.java8.domain.Employee;

public class TestStreamAPI {
	List<Employee> employees = Arrays.asList(
			new Employee("张三", 19, 9999.99),
			new Employee("李四", 29, 6999.99),
			new Employee("王五", 39, 2333.99),
			new Employee("赵六", 49, 1999.99),
			new Employee("田七", 9, 8888.88),
			new Employee("田七", 9, 8888.88),
			new Employee("田七", 9, 8888.88)
	);

	@Test
	public void test3(){
		long count = employees.stream().filter((e)->e.getAge()>50).distinct().count();
		System.out.println(count);
	}
	
	/*
	 * 1.给定一个数字列表，返回一个由每个数平方构成的列表
	 * 如：给定【1,2,3,4,5】，返回【1,4,9,16,25】
	 */
	@Test
	public void test1(){
		Integer[] nums = new Integer[]{1,2,3,4,5};
		Arrays.stream(nums)
			  .map((x)->x*x)
			  .forEach(System.out::println);
	}
	
	/*
	 * 2.使用map和reduce方法数一数流中有多少个Employee
	 */
	@Test
	public void test2(){
		Optional<Integer> sum = employees.stream()
				 .map((e)->1)
				 .reduce(Integer::sum);
		
		System.out.println(sum.get());
	}
	
	
}
