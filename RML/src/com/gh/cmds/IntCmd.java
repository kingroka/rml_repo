package com.gh.cmds;

import com.gh.main.Command;
import com.gh.main.Script;
import com.gh.vars.Int;

public class IntCmd extends Command{
	Script script;
	String name;
	int value;
	public IntCmd(Script script,String name,  int value) {
		this.script = script;
		this.name = name;
		this.value = value;
	
		
	}
	public void execute(){
		super.execute();
		script.variables.add(new Int(name,value));

	}

}
