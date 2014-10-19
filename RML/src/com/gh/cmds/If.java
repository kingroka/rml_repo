package com.gh.cmds;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.gh.main.Command;
import com.gh.main.Script;

public class If extends Command {
	String condition;
	public ArrayList<Command> cmds = new ArrayList<Command>();
	ScriptEngineManager factory = new ScriptEngineManager();
	ScriptEngine engine = factory.getEngineByName("JavaScript");
	Script script;

	public If(Script script, String cond) {
		condition = cond;
		this.script = script;
	}

	public void execute() {
		convertToJavaScript();
		try {
			Boolean bool = (Boolean) engine.eval(condition);
			if (bool) {
				super.execute();
				for (int i = 0; i < cmds.size(); i++) {
					cmds.get(i).execute();
					
				}
			}
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void convertToJavaScript() {
		for (int i = 0; i < script.variables.size(); i++) {
			if (condition.contains(script.variables.get(i).getName())) {
				String varName = script.variables.get(i).getName();

				condition = condition.replaceAll(varName,
						script.variables.get(i).getValue().toString());

			}
		}
	}

}
