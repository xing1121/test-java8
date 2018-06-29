package com.sf.java8.annotation;

import java.lang.reflect.Method;

import org.junit.Test;

/*
 * 重复注解与类型注解
 */
public class TestAnnontation {

	//checker framework	框架
//	private @NonNull String str;		//非空类型检查
	
	@Test
	public void test1() throws Exception {
		Class<TestAnnontation> clazz = TestAnnontation.class;
		Method method = clazz.getMethod("show",String.class);
		MyAnnotation[] myAnnotations = method.getAnnotationsByType(MyAnnotation.class);
		for (MyAnnotation myAnnotation : myAnnotations) {
			System.out.println(myAnnotation.value());
		}
		
	}
	
	@MyAnnotation("Hello")
	@MyAnnotation("World")
	public void show(@MyAnnotation("Param") String str){
		
	}

}
