package com.gh.cmds;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.gh.main.Command;
import com.gh.main.Script;

public class If extends Command{
	String condition;
	public ArrayList<Command> cmds = new ArrayList<Command>();
	ScriptEngineManager factory = new ScriptEngineManager();
    ScriptEngine engine = factory.getEngineByName("JavaScript");

	public If(Script script, String cond) {
		condition = cond;
	
	}
	public void execute(){
		 try {
				Boolean bool = (Boolean) engine.eval(condition);
				if(bool){
					System.out.println(condition);
					for (int i = 0; i < cmds.size(); i++) {
						cmds.get(i).execute();
					}
				}
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
