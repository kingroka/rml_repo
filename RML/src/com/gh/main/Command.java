package com.gh.main;

import java.util.ArrayList;

import com.gh.cmds.ExeFunc;

public class Command {
	private KeyWord key, parent;
	StringBuilder str = new StringBuilder();
	public ArrayList<Attribute> attributes = new ArrayList<Attribute>();
	ArrayList<Command> children = new ArrayList<Command>();

	public Command(KeyWord key, KeyWord parent) {
		this.key = key;
		this.parent = parent;
		attributes = key.attributes;
	}

	public Command(KeyWord key) {
		this.key = key;
		attributes = key.attributes;
	}

	public Command() {

	}

	public void execute() {
		if (!key.isSelfClosing()) {
			runChildren();
		}
	}

	public void runChildren() {
		for (int i = 0; i < children.size(); i++) {
			children.get(i).execute();
		}

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

	public String getCall() {
		return key.getCall();
	}

	public StringBuilder getStr() {
		return str;
	}

	public void setStr(StringBuilder str) {
		this.str = str;
	}

	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
	}

	public ArrayList<Command> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Command> children) {
		this.children = children;
	}

	
	public Attribute getAttribute(String name){
		Attribute a=null;
		attributes = key.attributes;
		for(int i=0;i<attributes.size();i++){
			//System.out.println(attributes.get(i).getName()+"-->"+attributes.get(i).getValue());
			if(attributes.get(i).getName().trim().equals(name.trim())){
				a=attributes.get(i);
				break;
			}
		}
		return a;
	}
}
