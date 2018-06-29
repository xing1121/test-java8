package com.sf.java8.optional;

import java.util.Optional;

import org.junit.Test;

import com.sf.java8.domain.Employee;
import com.sf.java8.domain.Godness;
import com.sf.java8.domain.Man;
import com.sf.java8.domain.NewMan;

public class TestMan {
	
	@Test
	public void test1(){
		Man man = new Man();
		String godnessName = this.getGodnessName(man);
		System.out.println(godnessName);
		
		NewMan newMan = new NewMan();
		System.out.println(this.getGodnessName(Optional.ofNullable(newMan)));
	}
	//需求：获取一个男人心中女神的名字
	public String getGodnessName(Man man){
		Optional<Godness> op = Optional.ofNullable(man.getGodness());
		return op.orElse(new Godness("苍老师")).getName();
	}
	
	public String getGodnessName(Optional<NewMan> newMan){
		return newMan.orElse(new NewMan()).getGodness().orElse(new Godness("苍老师")).getName();
	}
	
	public static void main(String[] args) {
		System.out.println(new Employee());
	}
	
}
