package com.sf.java8.interfacee;

/**
 *	java8中可以有默认方法，用default修饰
 *
 * 	接口中可以有静态方法
 */
public interface MyFun {

	default String getName(){
		return "哈哈";
	}
	
	default String getHello(){
		return "Hello";
	}
	
	public static void show(){
		System.out.println("接口中的静态方法");
	}
	
}
