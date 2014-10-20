package com.gh.cmds;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.gh.main.Command;
import com.gh.main.Script;
import com.gh.vars.BoolV;
import com.gh.vars.DoubleV;
import com.gh.vars.ListV;
import com.gh.vars.StringV;

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
		String[] list = name.split("\\[");
		if (getParent().getCall().equals("print")) {
			if (!(script.getVariableByName(list[0]) instanceof ListV)) {
				System.out.println((String) script.getVariableByName(this.name)
						.getValue().toString());
			} else {
				ListV item = (ListV) script.getVariableByName(list[0]);
				list[1] = list[1].replaceAll("\\]", "");
				list[1] = convertToJavaScript(list[1]);
				if (list.length > 0) {
					System.out.println(item.getItem((int)Double.parseDouble(list[1])));
				}
			}
		} else {
			String name = this.name;
			String[] list1 = name.split("\\[");
			String value = val;
			// System.out.println(name + ": " + value + ": " + this);
			if (script.getVariableByName(list1[0]) instanceof ListV) {
				list1[1] = list1[1].replaceAll("\\]", "");
				list1[1] = convertToJavaScript(list1[1]);
				if (list1.length > 0) {
					ListV item = (ListV) script.getVariableByName(list1[0]);
					if (item.type.trim().toLowerCase().equals("string")) {
						item.setValue(value);
					}
					if (item.type.trim().toLowerCase().equals("double")) {
						item.setValue(Double.parseDouble(value));
					}
					if (item.type.trim().toLowerCase().equals("boolean")) {
						item.setValue(Boolean.parseBoolean(value));
					}
				}
			} else {
				if (script.getVariableByName(name) instanceof DoubleV) {
					// set value to Double (Int)
					if (val.equals("++")) {
						script.getVariableByName(name).setValue(
								(Double) script.getVariableByName(name)
										.getValue() + 1d);
					} else if (val.equals("--")) {
						script.getVariableByName(name).setValue(
								(Double) script.getVariableByName(name)
										.getValue() - 1d);
					} else if (val.trim().startsWith("+=")) {
						val = val.replaceAll("\\+=", "");

						val =convertToJavaScript(val);
						try {
							script.getVariableByName(name).setValue(
									(Double) script.getVariableByName(name)
											.getValue()
											+ (double) engine.eval(val));
						} catch (ScriptException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (val.trim().startsWith("-=")) {
						val = val.replaceAll("-=", "");

						val =convertToJavaScript(val);
						try {
							script.getVariableByName(name).setValue(
									(Double) script.getVariableByName(name)
											.getValue()
											+ -(double) engine.eval(val));
						} catch (ScriptException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						val =convertToJavaScript(val);
						try {
							script.getVariableByName(name).setValue(
									Double.toString((double) engine.eval(val)));
						} catch (ScriptException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					val = oldVal;
				} else if (script.getVariableByName(name) instanceof StringV
						|| script.getVariableByName(name) instanceof BoolV) {
					// Set value to string
					script.getVariableByName(name).setValue(value);

				}
			}

		}
	}

	public String convertToJavaScript(String val) {
		for (int i = 0; i < script.variables.size(); i++) {
			if (val.contains(script.variables.get(i).getName())) {
				String varName = script.variables.get(i).getName();

				val = val.replaceAll(varName, script.variables.get(i)
						.getValue().toString());

			}
		}
		return val;
	}

}
