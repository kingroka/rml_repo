package com.gh.main;

import java.util.ArrayList;

public class Command {
	public KeyWord key, parent;
	StringBuilder str = new StringBuilder();
	public ArrayList<Attribute> attributes = new ArrayList<Attribute>();

	public Command(KeyWord key, KeyWord parent) {
		this.key = key;
		this.parent = parent;
	}

	public Command(KeyWord key) {
		this.key = key;

	}

	public Command() {

	}



	public void execute() {
	}

	public KeyWord getKey() {
		return key;
	}

	public void setKey(KeyWord key) {
		this.key = key;
		
	}

	public KeyWord getParent() {
		return parent;
	}

	public void setParent(KeyWord parent) {
		this.parent = parent;
	}

	public String getContains() {
		if (key != null)
			return key.contains;
		else {
			return null;
		}
	}

	public void setContains(String contains) {
		str.append(contains);
		this.key.setContains(str.toString().trim());
	}

}
