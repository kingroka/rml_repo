package com.gh.main;

import java.util.ArrayList;

import com.gh.cmds.Function;
import com.gh.vars.Variable;

public class Script {
	public ArrayList<Variable> variables = new ArrayList<Variable>();
	public ArrayList<Function> functions = new ArrayList<Function>();
	String path;
	String name;
	public boolean compiled;

	public ArrayList<Variable> getVariables() {
		return variables;
	}

	public void setVariables(ArrayList<Variable> variables) {
		this.variables = variables;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Script() {

	}

	public void execute() {
		System.out.println("   > Start --> " + name);
		for (int i = 0; i < functions.size(); i++) {
			if (functions.get(i).name.trim().startsWith("$")) {
				functions.get(i).execute();
				
			}
		}
		System.out.println("   > End --> " + name);

	}

	public Variable getVariableByName(String name) {
		Variable v = null;
		for (int i = 0; i < variables.size(); i++) {
			if (variables.get(i).getName().trim().equals(name.trim())) {
				v = variables.get(i);
				break;
			}
		}

		return v;

	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public Function getFunction(String name) {
		Function f = null;
		for (int i = 0; i < functions.size(); i++) {
			if (functions.get(i).name.trim().equals(name.trim())) {
				f = functions.get(i);
				break;
			}
		}

		return f;
	}

}
