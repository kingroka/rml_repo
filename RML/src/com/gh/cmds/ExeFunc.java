package com.gh.cmds;

import com.gh.main.Command;
import com.gh.main.Script;

public class ExeFunc extends Command {
	Script script;

	public ExeFunc( Script script) {
		this.script = script;
	
	}

	public void execute(){
		try{
		script.getFunction(
				(String)key.getAttribute("name")
				.getValue()).execute();
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}
}
