package com.gh.cmds;

import com.gh.main.Command;
import com.gh.main.Script;
import com.gh.vars.StringV;

public class StringCmd extends Command{
	Script script;
	String name;
	String value;
	public StringCmd(Script script, String name,String value) {
		// TODO Auto-generated constructor stub
		this.script = script;
		this.name = name;
		this.value = value;
	
	}
	public void execute(){
		script.variables.add(new StringV(name,value));
	}

}
