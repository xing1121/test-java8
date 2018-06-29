package com.sf.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import com.sf.java8.domain.Employee;

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
public class TestStreamAPI2 {
	List<Employee> employees = Arrays.asList(
			new Employee("张三", 19, 9999.99),
			new Employee("李四", 29, 6999.99),
			new Employee("王五", 39, 2333.99),
			new Employee("赵六", 49, 1999.99),
			new Employee("田七", 9, 8888.88),
			new Employee("田七", 9, 8888.88),
			new Employee("田七", 9, 8888.88)
	);
	
	//中间操作
	
	/*
	 * 排序
	 * 
	 * sorted()-自然排序(Comparable)
	 * sorted(Comparator com)-定制排序
	 * 
	 */
	@Test
	public void test7(){
		List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");
		list.stream()
			.sorted()					
			.forEach(System.out::println);
		
		System.out.println("--------------------------------");
		
		employees.stream()
				 .sorted((e1,e2)->{
					 if (e1.getAge().equals(e2.getAge())) {
						 return e1.getName().compareTo(e2.getName());
					 } else {
						 return e1.getAge().compareTo(e2.getAge());
					 }
				 })
				 .forEach(System.out::println);
	}
	
	/*
	 * 映射
	 * 
	 * map-接收Lambda，将元素转换成其他形式或提取信息，接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
	 * faltMap-接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有的流连接成一个流
	 */
	@Test
	public void test6(){
		List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");
		list.stream()
			.map((str)->str.toUpperCase())
			.forEach(System.out::println);
		
		System.out.println("--------------------------------");
		
		employees.stream()
				 .map((emp)->emp.getName())
				 .forEach(System.out::println);
		
		System.out.println("--------------------------------");
		
		Stream<Stream<Character>> stream = list.stream()
			.map(TestStreamAPI2::filterCharacter);
		
		stream.forEach((s)->
			s.forEach(System.out::println)
		);
		
		System.out.println("--------------------------------");
		
		Stream<Character> stream2 = list.stream()
			.flatMap(TestStreamAPI2::filterCharacter);		//map--flatMap	类似		add(Object obj)--addAll(Collection coll)
		
		stream2.forEach(System.out::println);
	}
	
	public static Stream<Character> filterCharacter(String str){
		List<Character> list = new ArrayList<>();
		for (Character c : str.toCharArray()) {
			list.add(c);
		}
		return list.stream();
	}
	
	/*
	 * 筛选与切片
	 * 
	 * filter-接收Lambda，从流中排除某些元素
	 * limit-截断流，使元素不超过给定数量
	 * skip-跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流，与limit互补
	 * distinct-筛选，通过流所生成元素的hashCode()和equals()去除重复元素
	 */
	@Test
	public void test5(){
		employees.stream()
		 .filter((e)->e.getSalary()>5000)
		 .skip(2)
		 .distinct()		//根据hashCode()和equals()去除重复元素
		 .forEach(System.out::println);
	}
	
	@Test
	public void test4(){
		employees.stream()
				 .filter((e)->e.getSalary()>5000)
				 .skip(2)
				 .forEach(System.out::println);
	}
	
	@Test
	public void test3(){
		employees.stream()
				 .filter((x)->{
					 System.out.println("短路！");		//找到两条就不再继续迭代  智能  类似 && ||
					 return x.getSalary()>5000;
		 		})
				 .limit(2)
				 .forEach(System.out::println);
	}
	
	//内部迭代：迭代操作有StreamAPI完成
	@Test
	public void test1(){
		//中间操作：不会执行任何操作
		Stream<Employee> stream = employees.stream().filter((x)->{
			System.out.println("StreamAPI的中间操作不会有任何结果");
			return x.getAge()>35;
		});						
		//终止操作：一次性执行全部内容，即“惰性求值”
		stream.forEach(System.out::println);
	}
	
	//外部迭代
	@Test
	public void test2(){
		Iterator<Employee> it = employees.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
}

