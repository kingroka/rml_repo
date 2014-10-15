package com.gh.main;

import java.util.ArrayList;

public class KeyWord {
	private String call;
	private boolean selfClosing;
	public ArrayList<Attribute> attributes = new ArrayList<Attribute>();
	public String contains="";

	StringBuilder str = new StringBuilder();
	/**
	 * @param call
	 *            "What is the name of the tag"
	 * @param selfClosing
	 *            "If the call uses '/>' to close itself
	 * **/
	public KeyWord(String call, boolean selfClosing) {
		this.call = call;
		this.selfClosing=selfClosing;
	}
	
	public KeyWord(KeyWord word){
		call = word.call;
		selfClosing = word.isSelfClosing();
		attributes=word.attributes;
		contains = word.contains;
		
	}
	public KeyWord(String call) {
		this.call = call;
	}
	
	public void addAttribute(Attribute attr){
		attributes.add(attr);
	}
	
	public boolean isSelfClosing() {
		return selfClosing;
	}
	public void setSelfClosing(boolean selfClosing) {
		this.selfClosing = selfClosing;
	}
	public String getCall() {
		return call.trim();
	}

	public String getOpen() {
		return "<" + call.trim();
	}

	public String getClosed() {
		if (!selfClosing) {
			return ("</" + call + ">").trim();
		} else {
			return "/"+call+">".trim();
		}
	}

	public void setCall(String call) {
		this.call = call;
	}
	public void addAttribute(String string) {
		addAttribute(new Attribute(string));
		
	}
	
	public Attribute getAttribute(String name){
		Attribute a=null;
		for(int i=0;i<attributes.size();i++){
			//System.out.println(attributes.get(i).getName()+"-->"+attributes.get(i).getValue());
			if(attributes.get(i).getName().trim().equals(name.trim())){
				a=attributes.get(i);
			}
		}
		return a;
	}
	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
	}
	public String getContains() {
		return contains;
	}

	public void setContains(String contains) {
		str.append(contains+" ");
		this.contains = str.toString();
	}
	public void clearAttributes() {
		for(int i=0;i<attributes.size();i++){
			attributes.get(i).setValue(null);
		}
		
	}
	
	

}
