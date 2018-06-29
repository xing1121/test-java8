package com.sf.java8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.sf.java8.domain.Employee;

public class TestLambda3 {
	
	List<Employee> employees = Arrays.asList(
			new Employee("张三", 19, 9999.99),
			new Employee("李四", 29, 6999.99),
			new Employee("王五", 39, 5999.99),
			new Employee("赵六", 39, 1999.99),
			new Employee("田七", 9, 2333.99)
	);
	
	// 需求：使用Collections.sort()定制排序，先按员工年龄排，年龄相同按姓名排
	@Test
	public void test1(){
		Collections.sort(employees, (e1,e2)->{
			if (e1.getAge() == e2.getAge()){
				return e1.getName().compareTo(e2.getName());
			} else {
				return Integer.compare(e1.getAge(), e2.getAge());
			}
		});
		employees.forEach(System.out::println);
	}
	
	// 需求：编写方法，使用MyStringInterface作为参数，将一个字符串转为大写，并作为方法的返回值
	@Test
	public void test2(){
		String str = oprStr("abcd", (x)->x.toUpperCase());
		System.out.println(str);
		
		String str2 = oprStr("\t\t\t 哈哈哈     ", (x) -> x.trim());
		System.out.println(str2);
		
		String str3 = oprStr("巴巴拉拉啦", (x) -> x.substring(1,3));
		System.out.println(str3);
	}
	public String oprStr(String str,MyStringInterface msi){
		return msi.getValue(str);
	}
	
	// 需求：使用MyFun2作为参数，对于两个Long型进行处理
	@Test
	public void test3(){
		oprLong(12L, 15L, (x,y)->x+y);
		oprLong(12L, 15L, (x,y)->x*y);
	}
	public void oprLong(Long l1,Long l2,MyFun2<Long, Long> mf){
		System.out.println(mf.getValue(l1, l2));
	} 
	
	
}









