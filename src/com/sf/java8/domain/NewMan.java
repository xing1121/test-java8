package com.sf.java8.domain;

import java.util.Optional;

public class NewMan {

	private Optional<Godness> godness = Optional.empty();

	public NewMan() {
	}

	public NewMan(Optional<Godness> godness) {
		super();
		this.godness = godness;
	}

	public Optional<Godness> getGodness() {
		return godness;
	}

	public void setGodness(Optional<Godness> godness) {
		this.godness = godness;
	}

	@Override
	public String toString() {
		return "NewMan [godness=" + godness + "]";
	}

}
