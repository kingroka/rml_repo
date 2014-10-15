package com.gh.cmds;

import com.gh.main.Command;
import com.gh.main.Script;
import com.gh.vars.ListV;

public class ListCMD extends Command {
	String type,name;
	Script script;
	public ListCMD(Script script,String name, String type) {
		this.type = type;
		this.name = name;
		this.script = script;
	}
	public void execute(){
		script.variables.add(new ListV(name,type));
		System.out.println(name +": " + type);
	}

}
