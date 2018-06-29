package com.sf.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
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
public class TestStreamAPI1 {
	
	//遍历map
	public static void main(String[] args) {
		Map<String, Object> map = new ConcurrentHashMap<>();
		map.put("A", "哈哈");
		map.put("B", 123);
		map.put("C", 33.33);
		map.forEach(new BiConsumer<String, Object>() {
			@Override
			public void accept(String t, Object u) {
				System.out.println(t+"="+u);
			}
		});
	}
	
	//创建Stream
	@Test
	public void test1(){
		//1.可以通过Collection系列集合提供的stream()或parallelStream()
		List<String> list = new ArrayList<>();
		Stream<String> stream1 = list.stream();
		Stream<String> stream2 = list.parallelStream();
		
		//2.通过Arrays中的静态方法stream()获取数组流
		Employee[] employees = new Employee[10];
		Stream<Employee> stream3 = Arrays.stream(employees);
		
		//3.通过Stream中的静态方法of()
		Stream<String> stream4 = Stream.of("aa","bb","cc");
		
		//4.创建无限流
		//迭代
		Stream<Integer> stream5 = Stream.iterate(0, (x)->x+2);
		stream5.limit(10).forEach(System.out::println);
		
		//生成
		Stream<Double> stream6 = Stream.generate(()->Math.random());
		stream6.limit(5).forEach(System.out::println);
	}
	
}









