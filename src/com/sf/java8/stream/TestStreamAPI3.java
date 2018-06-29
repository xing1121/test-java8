package com.sf.java8.stream;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import com.sf.java8.domain.Employee;
import com.sf.java8.domain.Employee.Status;

/**
 * 一、Stream 的三个操作步骤
 * 
 *  1.创建 Stream
 *  
 *  2.一系列流水线式的中间操作
 *  
 *  3.终止操作（终端操作）
 *  
 */
public class TestStreamAPI3 {
	
	List<Employee> employees = Arrays.asList(
			new Employee("张三", 19, 9999.99,Status.FREE),
			new Employee("李四", 29, 6999.99,Status.BUZY),
			new Employee("王五", 39, 2333.99,Status.VOCATION),
			new Employee("赵六", 49, 1999.99,Status.FREE),
			new Employee("田七", 9, 8888.88,Status.BUZY)
	);
	
	//终止操作
	
	/*
	 * 收集
	 * 
	 * collect-将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
	 */
	@Test
	public void test10(){
		String string = employees.stream()
				 .map(Employee::getName)
				 .collect(Collectors.joining(",","---","---"));
		System.out.println(string);
	}
	
	@Test
	public void test9(){
		DoubleSummaryStatistics dss = employees.stream()
				 .collect(Collectors.summarizingDouble(Employee::getSalary));
		
		System.out.println(dss.getMax());
		System.out.println(dss.getAverage());
		System.out.println(dss.getSum());
	}
	
	//分区（分片）
	@Test
	public void test8(){
		//按照true、false分区
		Map<Boolean, List<Employee>> map = employees.stream()
				 .collect(Collectors.partitioningBy((e)->e.getSalary()>8000));
		System.out.println(map);
	}
	
	//多级分组
	@Test
	public void test7(){
		//先按状态分组，再按年龄分组
		Map<Status, Map<String, List<Employee>>> map = employees.stream()
				 .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e)->{
							if (e.getAge()<=35) {
								return "青年";
							} else if (e.getAge()<=50) {
								return "中年";
							} else {
								return "老年";
							}
						  })));
		System.out.println(map);
	}
	
	//分组
	@Test
	public void test6(){
		//按照状态分组
		Map<Status, List<Employee>> map = employees.stream()
				 .collect(Collectors.groupingBy(Employee::getStatus));
		System.out.println(map);
	}
	
	@Test
	public void test5(){
		//员工总数
		long count = employees.stream()
				 .count();
		System.out.println(count);		
		
		//员工总数
		Long total = employees.stream()
				 .collect(Collectors.counting());	
		System.out.println(total);
		
		//工资平均值
		Double aver = employees.stream()
				 .collect(Collectors.averagingDouble(Employee::getSalary));	
		System.out.println(aver);
		
		//工资总和
		Double salarySum = employees.stream()
				 .collect(Collectors.summingDouble(Employee::getSalary));
		System.out.println(salarySum);
		
		//返回工资最大值的员工
		Optional<Employee> collect = employees.stream()
				.collect(Collectors.maxBy((e1,e2)->Double.compare(e1.getSalary(), e2.getSalary())));
		System.out.println(collect.get());
		
		//返回工资的最大值
		Optional<Double> collect2 = employees.stream()
				 .map(Employee::getSalary)
				 .collect(Collectors.maxBy(Double::compareTo));
		System.out.println(collect2.get());
	}
	
	@Test
	public void test4(){
		//把所有员工中工资大于5000的名字提取到一个集合中
		List<String> list = employees.stream()
				 .filter((emp)->emp.getSalary()>5000)
				 .map(Employee::getName)
				 .collect(Collectors.toList());		//Collectors：Collector的工具类
		list.forEach(System.out::println);
		
		System.out.println("--------------------------");
		
		Set<String> set = employees.stream()
				 .filter((emp)->emp.getSalary()>5000)
				 .map(Employee::getName)
				 .collect(Collectors.toSet());		//Collectors：Collector的工具类
		set.forEach(System.out::println);

		System.out.println("--------------------------");
		
		HashSet<String> hashSet = employees.stream()
		 .filter((emp)->emp.getSalary()>5000)
		 .map(Employee::getName)
		 .collect(Collectors.toCollection(HashSet::new));
		hashSet.forEach(System.out::println);
	}
	
	/*
	 * 归约
	 * 
	 * reduce(T identity,BinaryOperator) / reduce(BinaryOperator)	--可以将流中元素反复结合起来，得到一个值
	 */
	@Test
	public void test3(){
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		Integer sum = list.stream()
						  .reduce(0, (x,y)->x+y);	//将0作为x,1作为y,0+1=1		1作为x,2作为y,1+2=3		3作为x,3作为y......
		System.out.println(sum);
		
		System.out.println("-----------------------------------------");
		
		Optional<Double> optional = employees.stream()		//可能为空的结果，才封装到Optional中去
				 .map((emp)->emp.getSalary())
				 .reduce(Double::sum);						//获取所有员工的工资总和
		System.out.println(optional.get());
	}
	
	/*
	 * 查找与匹配
	 * 
	 * allMatch-检查是否匹配所有元素
	 * anyMatch-检查是否匹配至少一个元素
	 * noneMatch-检查是否没有匹配所有元素			与allMatch相反
	 * findFirst-返回第一个元素
	 * findAny-返回当前流中的任意元素
	 * 
	 * count-返回流中元素的总个数
	 * max-返回流中最大值
	 * min-返回流中最小值
	 */
	@Test
	public void test2(){
		long count = employees.stream()
				 .count();
		System.out.println(count);
		
		Optional<Employee> optional = employees.stream()
				 .max((e1,e2)->e1.getSalary().compareTo(e2.getSalary()));
		System.out.println(optional.get());
		
		Optional<Double> optional2 = employees.stream()
		 		 .map((e)->e.getSalary())
				 .min(Double::compare);
		System.out.println(optional2.get());
		
	}
	
	@Test
	public void test1(){
		boolean b1 = employees.stream()
				 .allMatch((e)->e.getStatus().equals(Status.FREE));
		System.out.println(b1);
		
		boolean b2 = employees.stream()
				 .anyMatch((e)->e.getStatus().equals(Status.FREE));
		System.out.println(b2);
		
		boolean b3 = employees.stream()
				 .anyMatch((e)->e.getStatus().equals(Status.FREE));
		System.out.println(b3);		//与allMatch相反
		
		Optional<Employee> optional = employees.stream()
				 .sorted((e1,e2)->Double.compare(e1.getSalary(), e2.getSalary()))
				 .findFirst();		//Optional中有一个方法 orElse，作用是如果为空则用另一个替代。Java8 尽可能的避免空指针
		System.out.println(optional.get());
		
		Optional<Employee> optional2 = employees.parallelStream()			//parallelStream并行流，多个线程同时去处理流，结果可能会不一样
				 .filter((emp)->emp.getStatus().equals(Status.FREE))		//stream串行流，一个线程去处理流，结果一样。
				 .findAny();
		System.out.println(optional2.get());
	}
	
}


