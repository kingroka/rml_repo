package com.gh.cmds;

import com.gh.main.Command;
import com.gh.main.Script;
import com.gh.vars.BoolV;
import com.gh.vars.IntV;

public class Bool extends Command {
	Script script;
	String name;
	boolean value;
	public Bool(Script script,String name, boolean value) {
		this.script = script;
		this.name = name;
		this.value = value;
	
		
	}
	public void execute(){
		script.variables.add(new BoolV(name,value));

	}

}
