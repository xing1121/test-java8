package com.sf.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * java 8		四大内置核心函数式接口
 * 
 * Consumer<T>：消费型接口		void accept(T t);
 * Supplier<T>：供给型接口		T get();
 * Function<T,R>：函数型接口	R apply(T t);
 * Predicate<T>：断言型接口	boolean test(T t);
 * 
 */
public class TestLambda4 {
	
	// Predicate<T> 断言型接口
	@Test
	public void test4(){
		List<String> list = assertStrs(Arrays.asList("Asadas","5040","阿斯达sas","646046啊"), (x)->x.contains("a"));
		list.forEach(System.out::println);
	}
	// 需求：将满足条件的字符串放入集合中
	public List<String> assertStrs(List<String> strs, Predicate<String> p){
		List<String> list = new ArrayList<>();
		
		strs.forEach(str ->{
			if(p.test(str)){
					list.add(str);
				}
			}
		);
		
		return list;
	}
	
	// Function<T,R>	函数型接口
	@Test
	public void test3(){
		String string = strHandler("aasdad", (x)->x.toUpperCase());
		System.out.println(string);
	}
	// 需求：用于处理字符串
	public String strHandler(String str,Function<String, String> fun){
		return fun.apply(str);
	}
	
	// Supplier<T>	供给型接口
	@Test
	public void test2(){
		List<Integer> list = getNumList(8, ()->(int)(Math.random()*100));
		for (Integer integer : list) {
			System.out.println(integer);
		}
	}
	// 需求：产生一些整数，并放入集合中
	public List<Integer> getNumList(int count,Supplier<Integer> s){
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			Integer n = s.get();
			list.add(n);
		}
		return list;
	}

	// Consumer<T>	消费性接口
	@Test
	public void test1(){
		happy(10000, (x)->System.out.println("刚哥喜欢大保健，一次消费:"+x+"元"));
	}
	public void happy(double money,Consumer<Double> con){
		con.accept(money);
	}
	
}
