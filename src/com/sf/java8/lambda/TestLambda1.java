package com.sf.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.junit.Test;

import com.sf.java8.domain.Employee;

public class TestLambda1 {

	// 原来的匿名内部类
	@Test
	public void test1(){
		Comparator<Integer> com = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		};
		
		TreeSet<Integer> ts = new TreeSet<>(com);
	}
	
	
	// Lambda表达式
	@Test
	public void test2(){
		Comparator<Integer> com = (x,y)-> Integer.compare(x, y);
		TreeSet<Integer> ts = new TreeSet<>(com);
	}
	
	List<Employee> employees = Arrays.asList(
			new Employee("张三", 19, 9999.99),
			new Employee("李四", 29, 6999.99),
			new Employee("王五", 39, 5999.99),
			new Employee("赵六", 49, 1999.99),
			new Employee("田七", 9, 2333.99)
	);
	
	// 需求：获取公司中年龄大于 35 的员工信息
	public List<Employee> filterEmployees(List<Employee> list){
		List<Employee> emps = new ArrayList<>();
		
		for (Employee employee : list) {
			if (employee.getAge() >= 35) {
				emps.add(employee);
			}
		}
		
		return emps;
	}
	
	@Test
	public void test3(){
		List<Employee> list = filterEmployees(employees);
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}
	
	// 需求：获取公司中员工工资大于 5000 的员工信息
	public List<Employee> filterEmployees2(List<Employee> list){
		List<Employee> emps = new ArrayList<>();
		
		for (Employee employee : list) {
			if (employee.getSalary() >= 5000) {
				emps.add(employee);
			}
		}
		
		return emps;
	}
	
	
	public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> mp){
		List<Employee> emps = new ArrayList<>();
		
		for (Employee employee : list) {
			if (mp.test(employee)) {
				emps.add(employee);
			}
		}
		
		return emps;
	}
	
	// 优化方式一：
	@Test
	public void test4(){
		List<Employee> list = filterEmployee(employees, new FilterEmployeeByAge());
		for (Employee employee : list) {
			System.out.println(employee);
		}
		System.out.println("--------------------------------");
		List<Employee> list2 = filterEmployee(employees, new FilterEmployeeBySalary());
		for (Employee employee : list2) {
			System.out.println(employee);
		}
	}
	
	// 优化方式二：匿名内部类
	@Test
	public void test5(){
		List<Employee> list = filterEmployee(employees,new MyPredicate<Employee>() {
			@Override
			public boolean test(Employee t) {
				return t.getSalary()<=5000;
			}
		});
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}
	
	// 优化方式三：Lambda表达式
	@Test
	public void test6(){
		List<Employee> list = filterEmployee(employees, (x) -> x.getSalary()<=5000);
		list.forEach(System.out::println);
		System.out.println("--------------------------------");
		list.forEach(emp->{
			System.out.println(emp);
			System.out.println(emp);
			}
		);
	}
	
	// 优化方式四：Stream API
	@Test
	public void test7(){
		employees.stream()
				 .filter(e -> e.getSalary()>=5000)
				 .limit(1)
				 .forEach(System.out::println);
		
		System.out.println("-------------------------");
		
		employees.stream()
				 .forEach(e->System.out.println(e.getName()));
		
		System.out.println("-------------------------");
		
		employees.stream()
				 .map(Employee::getName)
				 .forEach(System.out::println);
	}
	
}


class FilterEmployeeByAge implements MyPredicate<Employee>{

	@Override
	public boolean test(Employee t) {
		return t.getAge() >= 35;
	}

}


class FilterEmployeeBySalary implements MyPredicate<Employee>{

	@Override
	public boolean test(Employee t) {
		return t.getSalary() >= 5000;
	}

}




