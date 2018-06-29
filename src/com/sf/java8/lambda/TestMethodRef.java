package com.sf.java8.lambda;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

import com.sf.java8.domain.Employee;

/**
 * 一、方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用“方法引用”
 * 			（可以理解为方法引用是Lambda 表达式的另外一种表现形式）
 * 
 * 主要有三种语法格式：
 * 
 * 	对象::实例方法名
 * 
 * 	类名::静态方法名
 * 
 * 	类名::实例方法名
 * 
 * 	注意：
 * 		①必须保证Lambda体中方法的参数列表、返回值类型 和 函数式接口方法的参数列表、返回值类型一致
 * 		②若Lambda体中第一个参数是实例方法的调用者，第二个参数是实例方法的参数时，才能用	类名::实例方法名
 * 
 * 二、构造器引用：
 * 
 * 格式：
 * 
 * 	ClassName::new
 * 
 * 	注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致！
 * 
 * 三、数组引用：
 * 
 * 	Type::new
 */
public class TestMethodRef {
	
	//数组引用
	@Test
	public void test7(){
		Function<Integer, String[]> fun = (x)->new String[x];	//一个参数Integer,一个返回值String[]
		String[] strs = fun.apply(10);
		System.out.println(strs.length);
		
		Function<Integer, String[]> fun1 = String[]::new;
		String[] ss = fun1.apply(20);
		System.out.println(ss.length);
	}
	
	@Test
	public void test6(){
		Function<String, Employee> fun = (x)->new Employee(x);
		
		Function<String, Employee> fun1 = Employee::new;	// Employee apply(String t);自动匹配一个参数的构造器
		System.out.println(fun1.apply("李四"));
	}
	
	//构造器引用
	@Test
	public void test5(){
		Supplier<Employee> sup = ()->new Employee("张三", 18, 5000.0);
		
		Supplier<Employee> sup1 = Employee::new;	// T get(); 自动匹配无参构造器
	}
	
	//类::实例方法名
	@Test
	public void test4(){
		//比较两个字符串，返回布尔
		BiPredicate<String, String> bp = (x,y)->x.equals(y);
		
		BiPredicate<String, String> bp1 = String::equals;	//Lambda体中第一个参数是实例方法的调用者，第二个参数是实例方法的参数时，才能这么用
	}
	
	//类::静态方法名
	@Test
	public void test3(){
		Comparator<Integer> com = (x,y)->Integer.compare(x, y);
		
		Comparator<Integer> com1 = Integer::compare;
	}

	//对象::实例方法名
	@Test
	public void test1(){
		PrintStream ps = System.out;
		Consumer<String> con = (x)->ps.println(x);
		
		Consumer<String> con1 = ps::println;// 必须保证函数式接口中方法的参数列表、返回值类型 和 要调用的方法的参数列表、返回值类型一致
		
		Consumer<String> con2 = System.out::println;
		
		con2.accept("asdsadadsad");
	}

	@Test
	public void test2(){
		Employee emp = new Employee("张三",18,9999.0);
		Supplier<String> sup = ()->emp.getName();
		String str = sup.get();
		System.out.println(str);
		
		Supplier<Integer> sup1 = emp::getAge;
		Integer age = sup1.get();
		System.out.println(age);
	}
	
}








