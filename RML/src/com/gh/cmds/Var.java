package com.gh.cmds;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.gh.main.Command;
import com.gh.main.Script;

public class Var extends Command {
	Script script;
	public String val;

	public Var(final Script script) {
		this.script = script;
	
	}

	public Var(Script script, String val) {
		this.script = script;
		this.val = val;
		
	}

	ScriptEngineManager mgr = new ScriptEngineManager();
	ScriptEngine engine = mgr.getEngineByName("JavaScript");

	public void execute() {
		if (parent.getCall().equals("print")) {
			System.out.println((String)script.getVariableByName(val).getValue().toString());
		} else {
			if (script.getVariableByName(
					(String) key.getAttribute("name").getValue()).getValue() instanceof Double) {
				try {
					script.getVariableByName(
							(String) key.getAttribute("name").getValue())
							.setValue(
									Double.toString((double) engine.eval((String) key
											.getAttribute("set").getValue())));
				} catch (ScriptException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				script.getVariableByName(
						(String) key.getAttribute("name").getValue()).setValue(
						(String) key.getAttribute("set").getValue());
			}

		}
	}

}
