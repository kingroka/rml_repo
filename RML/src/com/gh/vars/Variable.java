package com.gh.vars;

public class Variable {
	public Object value;
	String name;
	public Variable() {
		
	}
	public Object getValue() {
		return value;
	}
	public Object getValue(boolean string) {
		if(!string || value instanceof String){
		return value;
		}
		else{
			if (value instanceof Integer){
				return Integer.toString((Integer)value);	
			}else{
				return null;
			}
		}
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
