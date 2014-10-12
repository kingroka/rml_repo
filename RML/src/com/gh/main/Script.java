package com.gh.main;

import java.util.ArrayList;

import com.gh.cmds.Function;
import com.gh.vars.Variable;

public class Script {
	public ArrayList<Command> cmds = new ArrayList<Command>();
	public ArrayList<Variable> variables = new ArrayList<Variable>();
	public ArrayList<Function> functions = new ArrayList<Function>();
	String path;
	String name;
	public boolean compiled;

	public ArrayList<Command> getCmds() {
		return cmds;
	}

	public void setCmds(ArrayList<Command> cmds) {
		this.cmds = cmds;
	}

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
		System.out.println(name + ":" + cmds.size());
		for (int i = 0; i < cmds.size(); i++) {
			cmds.get(i).execute();
			
		}
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
