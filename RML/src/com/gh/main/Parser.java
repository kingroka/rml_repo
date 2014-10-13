package com.gh.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.gh.cmds.Bool;
import com.gh.cmds.ExeFunc;
import com.gh.cmds.Function;
import com.gh.cmds.If;
import com.gh.cmds.IntCmd;
import com.gh.cmds.Print;
import com.gh.cmds.StringCmd;
import com.gh.cmds.Var;

public class Parser {
	/*
	 * Purpose/Goal: To create an "object oriented" scripting language with a
	 * similar syntax to xml
	 */

	public ArrayList<KeyWord> keywords = new ArrayList<KeyWord>();
	private KeyWord rml, main, integer, list, ar, func, atfunc, condif, print,
			var, string, bool;

	public Parser() {
		
	}
	
	public void defineKeyWords(){
		rml = new KeyWord("rml");// rml tags
		rml.addAttribute(new Attribute("class"));
		rml.addAttribute(new Attribute("test"));
		keywords.add(rml);

		main = new KeyWord("main");// Main Constuctor
		keywords.add(main);

		integer = new KeyWord("int", true);// Integer
		integer.addAttribute("name");
		integer.addAttribute("value");
		keywords.add(integer);
		
		bool = new KeyWord("boolean", true);
		bool.addAttribute("name");
		bool.addAttribute("value");
		keywords.add(bool);
		
		list = new KeyWord("list");// Array Declaration
		list.addAttribute("name");
		list.addAttribute("type");
		keywords.add(list);

		ar = new KeyWord("ar", true);// Array items
		ar.addAttribute("value");
		keywords.add(ar);

		func = new KeyWord("func");
		func.addAttribute("name");
		keywords.add(func);

		atfunc = new KeyWord("@func");
		atfunc.addAttribute("name");
		keywords.add(atfunc);

		condif = new KeyWord("if");
		condif.addAttribute("cond");
		keywords.add(condif);

		print = new KeyWord("print");
		keywords.add(print);

		string = new KeyWord("string");
		string.addAttribute("name");
		string.addAttribute("value");
		keywords.add(string);

		var = new KeyWord("@var", true);
		var.addAttribute("name");
		var.addAttribute("set");
		keywords.add(var);

	}

	public void addKeyWord(KeyWord temp) {
		keywords.add(temp);
	}

	public void log(Object obj) {
		System.out.println(this.getClass() + ": " + obj);

	}

	/**
	 * @param path
	 *            Path to folder containing all files
	 */
	public void loadFiles(String path) {

	}
	/*
	public static void main(String[] args) {
		FileHandle.init(new Parser(), "E:\\Projects");
		FileHandle.run();
	}
*/
	public ArrayList<KeyWord> parents = new ArrayList<KeyWord>();
	public ArrayList<If> cond = new ArrayList<If>();
	public Command cmd = null;
	public KeyWord parent = null;
	public Function openFunc = null;

	public void parse(Script script) {
		BufferedReader reader;
		try {

			reader = new BufferedReader(new FileReader(script.path));
			;// new BufferedReader(new
				// FileReader("F://test.rml"));
			String line = null;

			while ((line = reader.readLine()) != null) {
				if (!line.trim().startsWith("//")) {
					if (hasKeyWord(line)) {
						for (int i = 0; i < keywords.size(); i++) {
							KeyWord temp = keywords.get(i);
							if (line.contains(temp.getOpen())) {
								/* Get Attributes */
								for (int j = 0; j < temp.attributes.size(); j++) {
									if (line.contains(temp.attributes.get(j)
											.getName())) {
										int tab = line.indexOf(temp.attributes
												.get(j).getName());
										if (line.contains(" =\" ".trim())) {
											int start = line.indexOf(
													" =\" ".trim(), tab);
											int end = tab;
											for (int l = start + 2; l < line
													.length(); l++) {
												if (line.substring(l, l + 1)
														.equals("\"")) {
													end = l;
													break;
												}
											}
											if (end != 0) {
												String val = line.substring(
														start + 2, end);
												temp.attributes.get(j)
														.setValue(val);

											}

											/*
											 * log(temp.getCall() + "-> " +
											 * temp.attributes.get(j) .getName()
											 * + "-->" + temp.attributes.get(j)
											 * .getValue());
											 */

										} else {
											/*
											 * log(temp.getCall() + ": " +
											 * temp.attributes.get(j)
											 * .getName());
											 */
										}

									}
								}
								/*Set Commands*/
								this.SetCommands(temp, script);
								/* Set Parents */

								if (parents.size() > 0) {
									parent = parents.get(parents.size() - 1);
									if (parent.getCall().equals(temp.getCall())) {
										if (parents.size() > 1) {
											parent = parents
													.get(parents.size() - 2);
										} else {
											parent = temp;
										}
									}
								}

								if (cmd != null) {
									cmd.setParent(parent);
								}
								/* Set Lists */
								if (cmd != null) {
									if (!temp.getCall().equals("rml")
											&& !temp.getCall().equals("func")) {

										if (parent.getCall().equals("func")) {
											openFunc.cmds.add(cmd);

										} else if (parent.getCall()
												.equals("if")) {
											cond.get(cond.size() - 1).cmds
													.add(cmd);
										} else {	     
												script.cmds.add(cmd);
											
										}

									}
								}
							}

							/* Closing Commands */
							if (line.contains(temp.getClosed())) {
							
								cmd = new Command(parent);
								
								if (temp.getCall().equals("func")
										&& openFunc != null) {

									script.functions.set(
											script.functions.indexOf(openFunc),
											openFunc);
									openFunc = null;

								}
								if(temp.getCall().equals("if")){
									script.cmds.add(cond.get(cond.size() - 1));
								}
								if (temp.getCall().equals(parent.getCall())) {
									parents.remove(parent);
									parents.trimToSize();
								}

							}

						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parents.removeAll(parents);
		parents = new ArrayList<KeyWord>();
		cmd = null;

		parent = null;

	}

	public boolean hasKeyWord(String line) {
		boolean bool = false;
		for (int i = 0; i < keywords.size(); i++) {
			if (line.contains(keywords.get(i).getOpen())
					|| line.contains(keywords.get(i).getClosed())) {
				bool = true;
				break;
			}
		}
		return bool;
	}

	public void SetCommands(KeyWord temp, Script script) {
		if (temp.getCall().equals("print")) {
			cmd = new Print(script);
			cmd.setKey(temp);
			parents.add(temp);
		}
		if (temp.getCall().equals("int")) {
			cmd = new IntCmd(script, (String) temp.getAttribute("name")
					.getValue(), Integer.parseInt((String) temp.getAttribute(
					"value").getValue()));
			cmd.setKey(temp);

		}
		if (temp.getCall().equals("boolean")) {
			cmd = new Bool(script, (String) temp.getAttribute("name")
					.getValue(), Boolean.parseBoolean((String) temp.getAttribute(
					"value").getValue()));
			cmd.setKey(temp);

		}
		if (temp.getCall().equals("string")) {
			cmd = new StringCmd(script, (String) temp.getAttribute("name")
					.getValue(), (String) temp.getAttribute("value").getValue());
			cmd.setKey(temp);

		}
		if (temp.getCall().equals("@var")) {
			cmd = new Var(script, (String) temp.getAttribute("name").getValue());
			cmd.setKey(temp);
		}
		if (temp.getCall().equals("rml")) {
			script.name = (String) temp.getAttribute("class").getValue();
			parents.add(temp);
		}
		if (temp.getCall().equals("func")) {
			cmd.setKey(temp);

			openFunc = null;
			openFunc = new Function(script, (String) temp.getAttribute("name")
					.getValue());
			script.functions.add(openFunc);
			parents.add(temp);

		}
		if (temp.getCall().equals("main")) {
			parents.add(temp);
		}
		if (temp.getCall().equals("@func")) {
			cmd = new ExeFunc(script);
			cmd.setKey(temp);

		}
		if (temp.getCall().equals("if")) {
			cmd.setKey(temp);
			cond.add(new If(script, (String) temp.getAttribute("cond")
					.getValue()));
			parents.add(temp);
		}

	}

	public Command getCmd() {
		return cmd;
	}

	public void setCmd(Command cmd) {
		this.cmd = cmd;
	}

	public KeyWord getParent() {
		return parent;
	}

	public void setParent(KeyWord parent) {
		this.parent = parent;
	}

	public Function getOpenFunc() {
		return openFunc;
	}

	public void setOpenFunc(Function openFunc) {
		this.openFunc = openFunc;
	}

}
