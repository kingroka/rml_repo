package com.gh.cmds;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.gh.main.Command;
import com.gh.main.Script;
import com.gh.vars.DoubleV;

public class Var extends Command {
	Script script;
	String oldVal;
	public String name, val;

	public Var(Script script, String name, String val) {
		this.script = script;
		this.val = val;
		this.name = name;
		oldVal = val;
	}

	ScriptEngineManager mgr = new ScriptEngineManager();
	ScriptEngine engine = mgr.getEngineByName("JavaScript");

	public void execute() {
		// System.out.println("executing: " + this);
		if (getParent().getCall().equals("print")) {
			System.out.println((String) script.getVariableByName(this.name)
					.getValue().toString());
		} else {
			String name = this.name;
			String value = val;
			// System.out.println(name + ": " + value + ": " + this);

			if (script.getVariableByName(name) instanceof DoubleV) {
				// set value to Double (Int)
				if (val.equals("++")) {
					script.getVariableByName(name)
							.setValue(
									(Double) script.getVariableByName(name)
											.getValue() + 1d);
				} else if (val.equals("--")) {
					script.getVariableByName(name)
							.setValue(
									(Double) script.getVariableByName(name)
											.getValue() - 1d);
				} else if (val.trim().startsWith("+=")) {
					val = val.replaceAll("\\+=", "");

					convertToJavaScript();
					try {
						script.getVariableByName(name).setValue(
								(Double) script.getVariableByName(name)
										.getValue()
										+ (double) engine
												.eval(val));
					} catch (ScriptException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}  else if (val.trim().startsWith("-=")) {
					val = val.replaceAll("-=", "");

					convertToJavaScript();
					try {
						script.getVariableByName(name).setValue(
								(Double) script.getVariableByName(name)
										.getValue()
										+ -(double) engine
												.eval(val));
					} catch (ScriptException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					convertToJavaScript();
					try {
						script.getVariableByName(name).setValue(
								Double.toString((double) engine.eval(val)));
					} catch (ScriptException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				val = oldVal;
			} else {
				// Set value to string
				script.getVariableByName(name).setValue(value);

			}

		}
	}

	public void convertToJavaScript() {
		for (int i = 0; i < script.variables.size(); i++) {
			if (val.contains(script.variables.get(i).getName())) {
				String varName = script.variables.get(i).getName();

				val = val.replaceAll(varName, script.variables.get(i)
						.getValue().toString());

			}
		}
	}
}
