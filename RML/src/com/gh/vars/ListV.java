package com.gh.vars;

import java.util.ArrayList;

public class ListV extends Variable {
	String type;
	@SuppressWarnings("rawtypes")
	public ArrayList items;
	public ListV(String name, String type) {
		this.type = type;
		this.name = name;
		if(type.trim().toLowerCase().equals("string")){
			items = new ArrayList<String>();
		}
		if(type.trim().toLowerCase().equals("int")){
			items = new ArrayList<Double>();
		}
		if(type.trim().toLowerCase().equals("boolean")){
			items = new ArrayList<Boolean>();
		}
		value = "This is a list";
	}
	
	public Object getItem(int index){
		return items.get(index);
	}
	
	@SuppressWarnings("unchecked")
	public void addItem(Object item){
		items.add(item);
	}
}
