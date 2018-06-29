package com.sf.java8.interfacee;

public class SubClass extends MyClass implements MyFun,MyInter {

	public static void main(String[] args) {
		System.out.println(new SubClass().getName());
		MyFun.show();
	}

	@Override
	public String getName() {
//		return super.getName();			// 嘿嘿嘿，直接调用super.getName()是调用父类的方法
		return MyInter.super.getName();	// 呵呵呵，这样是调用接口中的getName()方法
	}
	
}
