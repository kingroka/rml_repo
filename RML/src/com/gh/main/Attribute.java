package com.gh.main;

public class Attribute {
	public String name;
	public Object value;

	public Attribute(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
	//	System.out.println(value);
		return value;
	}



	public void setValue(Object value) {
		this.value = value;
	}

}