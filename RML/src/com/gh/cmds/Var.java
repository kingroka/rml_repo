package com.gh.cmds;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.gh.main.Command;
import com.gh.main.Script;

public class Var extends Command {
	Script script;
	public String name, val;


	public Var(Script script, String name, String val) {
		this.script = script;
		this.val = val;
		this.name=name;

	}

	ScriptEngineManager mgr = new ScriptEngineManager();
	ScriptEngine engine = mgr.getEngineByName("JavaScript");

	public void execute() {
		//System.out.println("executing: " + this);
		if (parent.getCall().equals("print") ) {
			System.out.println((String) script.getVariableByName(this.name)
					.getValue().toString());
		} else {
			String name = this.name;
			String value = val;
		//	System.out.println(name + ": " + value + ": " + this);

			if (script.getVariableByName(name).getValue() instanceof Double) {
				// set value to Double (Int)
				try {
					script.getVariableByName(name).setValue(
							Double.toString((double) engine.eval(value)));
				} catch (ScriptException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// Set value to string
				script.getVariableByName(name).setValue(value);

			}

		}
	}

}
