package com.gh.cmds;

import com.gh.main.Command;
import com.gh.main.Script;

public class ExeFunc extends Command {
	Script script;

	public ExeFunc(Script script) {
		this.script = script;

	}

	boolean ran;

	public void execute() {
			Function exe = script.getFunction((String) getAttribute("name")
					.getValue()
					.toString());

			try {
				exe.execute();
				

			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		
	}

}
