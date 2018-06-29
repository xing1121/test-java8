package com.sf.java8.domain;

public class Man {

	private Godness godness;

	public Man() {
	}

	public Man(Godness godness) {
		this.godness = godness;
	}

	public Godness getGodness() {
		return godness;
	}

	public void setGodness(Godness godness) {
		this.godness = godness;
	}

	@Override
	public String toString() {
		return "Man [godness=" + godness + "]";
	}
	
}
