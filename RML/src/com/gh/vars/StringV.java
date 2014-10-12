package com.gh.vars;

public class StringV extends Variable{

	public StringV(String name,String val) {
		this.name=name;
		value=val;
	}
	public String getVal(){
		return (String)value;
	}

}
