package com.sf.java8.lambda;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.junit.Test;

/**
 * 一、Lambda表达式的基础语法：Java8中引入了一个新的操作符：->，该操作符成为箭头操作符，或者称为Lambda操作符。
 * 								箭头操作符将Lambda表达式拆分为两部分，左侧和右侧。
 * 左侧：Lambda表达式的参数列表。
 * 右侧：Lambda表达式中需要执行的功能，即Lambda体。
 * 
 * 语法格式一：无参数，无返回值
 * 		()->System.out.println("Hello!Lambda");
 * 
 * 语法格式二：一个参数，无返回值
 * 		(x)->System.out.println(x);
 * 
 * 语法格式三：若只有一个参数，参数的小括号可以省略不写
 * 		x->System.out.println(x);
 * 
 * 语法格式四：有多个参数，并且Lambda体中有多条语句，有返回值
 * 		(x,y)->{
 *			System.out.println("hahah");
 *			return Integer.compare(x, y);
 *		};
 * 
 * 语法格式五：有两个参数，有返回值，Lambda体中只有一条语句，return和{}都可以省略不写
 * 		(x,y)-> Integer.compare(x, y);
 * 
 * 语法格式六：Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器可以通过上下文推断出数据类型，即“类型推断”
 * 		(Integer x,Integer y)-> Integer.compare(x, y);
 * 		Comparator<Integer> com = (x, y)-> Integer.compare(x, y);
 * 
 * 上联：左右遇一括号省
 * 下联：左侧推断类型省
 * 横批：能省则省
 * 
 * 二、Lambda表达式需要函数式接口的支持
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。可以使用注解@FunctionalInterface 修饰
 * 			 可以检查接口是否是函数式接口。
 *  
 */
public class TestLambda2 {
	
	//需求：对一个数进行运算
	@Test
	public void test6(){
		System.out.println(operation(100, (x)->x+1));
		System.out.println(operation(100, (x)->x*800));
	}
	public Integer operation(Integer num,MyFunctional mf){
		return mf.getValue(num);
	}

	// 类型推断
	@Test
	public void test5(){
//		String[] strs = {"aaa","bbb","ccc"};  类型推断	1.7也有
//		String[] strs;
//		strs = {"aaa","bbb","ccc"};				拆开写推断不出来	1.7也有
//		List<String> list = new ArrayList<>(); 类型推断		1.7也有
		this.show(new HashMap<>());	//jdk1.8类型推断升级，1.7中这样不行
	}
	public void show(Map<String, Integer> map){
		
	}
	
	@Test
	public void test4(){
		Comparator<Integer> com = (x, y)-> Integer.compare(x, y);
	}
	
	@Test
	public void test3(){
		Comparator<Integer> com = (x,y)->{
			System.out.println("hahah");
			return Integer.compare(x, y);
		};
	}
	
	@Test
	public void test2(){
		Consumer<String> con = (x)->System.out.println(x);
		con.accept("威武");
	}
	
	@Test
	public void test1(){
		int num = 0;
		Runnable r= new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello World!"+num);
			}
		};
		r.run();
		
		System.out.println("----------------");
		
		Runnable re = ()->System.out.println("Hello Lambda!" + num);
		re.run();
	}
	
}



