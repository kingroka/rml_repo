package com.gh.cmds;

import com.gh.main.Command;
import com.gh.main.Script;

public class Print extends Command {

	public Print( Script parent) {
		
	}
	public void execute(){
		super.execute();
		if (getContains() != null ) {
			
			//System.out.println(getParent().getContains().trim());
		}
	}
}
