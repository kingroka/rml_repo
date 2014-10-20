package com.gh.cmds;

import java.util.ArrayList;

import com.gh.main.Command;
import com.gh.main.Script;
import com.gh.vars.ListV;

public class ArrayItemCmd  extends Command{
	String listName, value;
	Script script;
	public ArrayItemCmd(String list, String value, Script script) {
		this.listName = list;
		this.value= value;
		this.script = script;
	}
	
	@SuppressWarnings("unchecked")
	public void execute(){
		ListV list = (ListV) script.getVariableByName(listName);
		if(list.type.trim().toLowerCase().equals("string")){
			list.items.add(value);
		}
		if(list.type.trim().toLowerCase().equals("double")){
			list.items.add(Double.parseDouble(value));
		}
		if(list.type.trim().toLowerCase().equals("boolean")){
			list.items.add(Boolean.parseBoolean(value));
		}
	}

}
