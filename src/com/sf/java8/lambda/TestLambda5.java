package com.sf.java8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.Test;

import com.sf.java8.domain.Employee;

public class TestLambda5 {
	List<Employee> employees = Arrays.asList(
			new Employee("张三", 19, 9999.99),
			new Employee("李四", 29, 6999.99),
			new Employee("王五", 39, 5999.99),
			new Employee("赵六", 49, 1999.99),
			new Employee("田七", 9, 2333.99)
	);
	
	@Test
	public void test3(){
		Long l1 = 12L;
		Long l2 = 15L;
		
		// 计算两个long的和
		Long res1 = this.longHandler(l1,l2,Long::sum);
		System.out.println(res1);
		
		// 计算两个long的积
		Long res2 = this.longHandler(l1,l2,Math::multiplyExact);
		System.out.println(res2);
	}
	public Long longHandler(Long l1,Long l2,BiFunction<Long, Long, Long> fun){
		return fun.apply(l1,l2);
	}
	
	@Test
	public void test2(){
		// String类型转大写
		String str = this.getValue("asdadsda", String::toUpperCase);
		System.out.println(str);
		
		// String类型截取索引为2-4的字符
		str = this.getValue(str, (x)->x.substring(2, 5));
		System.out.println(str);
	}
	public String getValue(String str,Function<String, String> fun){
		return fun.apply(str);
	}
	
	@Test
	public void test1(){
		Collections.sort(employees, (e1,e2)->{
			if (e1.getAge().equals(e2.getAge())) {
				return e1.getName().compareTo(e2.getName());
			} else {
				return e1.getAge().compareTo(e2.getAge());
			}
		});
		employees.forEach(System.out::println);
	}
	
}
