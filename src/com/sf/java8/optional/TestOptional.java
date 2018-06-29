package com.sf.java8.optional;

import java.util.Optional;

import org.junit.Test;

import com.sf.java8.domain.Employee;
import com.sf.java8.domain.Employee.Status;

/*
 * 容器类Optional
 */
public class TestOptional {
	
	@Test
	public void test7(){
		Optional<Employee> op = Optional.ofNullable(new Employee("张三",18,8888.88,Status.FREE));
		Optional<String> optional = op.flatMap((e)->Optional.of(e.getName()));		//与map类似，要求返回值必须是Optional
		System.out.println(optional.get());
	}
	
	@Test
	public void test6(){
		Optional<Employee> op = Optional.ofNullable(new Employee("张三",18,8888.88,Status.FREE));
		Optional<String> optional = op.map((e)->e.getName());						//有值就处理并返回处理后的Optional，否则返回Optional.empty
		System.out.println(optional.get());
	}	
	
	@Test
	public void test5(){
		Optional<Employee> optional = Optional.ofNullable(null);
		System.out.println(optional.orElseGet(Employee::new));						//若是空则执行Supplier.get方法返回一个对象
	}
	
	@Test
	public void test4(){
		Optional<Employee> optional = Optional.ofNullable(null);
		System.out.println(optional.orElse(new Employee("张三")));					//若是空则使用这个对象
	}
	
	@Test
	public void test3(){
		Optional<Employee> optional = Optional.ofNullable(null);					//传入null则创建空的Optional对象，非空则创建非空的Optional对象
		if (optional.isPresent()) {													//如果非空则执行
			System.out.println(optional.get());
		}
	}
	
	@Test
	public void test2(){
		Optional<Employee> optional = Optional.empty();								//创建一个空的Optional对象
		System.out.println(optional.get());	
	}
	
	@Test
	public void test1(){
		Optional<Employee> optional = Optional.of(null);							//Optional.of	创建一个Optional对象
		Employee employee = optional.get();											//获取Optional中的值
		System.out.println(employee);
	}

}
