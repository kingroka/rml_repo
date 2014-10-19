package com.gh.cmds;

import com.gh.main.Command;
import com.gh.main.Script;
import com.gh.vars.DoubleV;

public class DoubleCmd extends Command{
	Script script;
	String name;
	int value;
	public DoubleCmd(Script script,String name,  int value) {
		this.script = script;
		this.name = name;
		this.value = value;
		
	}
	public void execute(){
		super.execute();
		script.variables.add(new DoubleV(name,value));

	}

}
