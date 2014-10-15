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
import com.gh.cmds.ListCMD;
import com.gh.cmds.Print;
import com.gh.cmds.StringCmd;
import com.gh.cmds.Var;

public class Parser {
	/*
	 * Purpose/Goal: To create an "object oriented" scripting language with a
	 * similar syntax to xml
	 */

	public ArrayList<KeyWord> keywords = new ArrayList<KeyWord>();
	private KeyWord rml, main, dbl, list, ar, func, atfunc, condif, print,
			var, string, bool;

	public Parser() {

	}

	public void defineKeyWords() {
		rml = new KeyWord("rml");// rml tags
		rml.addAttribute(new Attribute("class"));
		keywords.add(rml);

		main = new KeyWord("main");// Main Constuctor
		keywords.add(main);

		dbl = new KeyWord("double", true);// Double
		dbl.addAttribute("name");
		dbl.addAttribute("value");
		keywords.add(dbl);

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

	public static void main(String[] args) {
		FileHandle.init(new Parser(), "C:\\Users\\Justin\\Documents\\Projects");
		FileHandle.run();
	}

	public ArrayList<Command> parents = new ArrayList<Command>();
	public Command cmd = null;
	public Command parent = null;

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
							KeyWord temp = new KeyWord(keywords.get(i));

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
										} else {

										}

									}
								}
								/* Set Commands */
								this.setCommands(temp, script);
								/* Set Parents */

								if (parents.size() > 0) {
									parent = parents.get(parents.size() - 1);
									if (parent != null) {
										if (parent.getCall().equals(
												temp.getCall())) {
											if (parents.size() > 1) {
												parent = parents.get(parents
														.size() - 2);

											} else {
												parent = null;
											}
										}
									}
								}

								/* Set Lists */
								if (cmd != null && parent != null) {
									if (!temp.getCall().equals("rml")) {
										parent.children.add(cmd);

									}
								}
							}
							if (cmd != null && parent != null) {
								cmd.setParent(parent.getKey());
							}
							/* Closing Commands */
							if (line.contains(temp.getClosed())) {
								if (parent != null) {
									if (temp.getCall().equals(parent.getCall())) {
										parents.remove(parent);
										parents.trimToSize();

									}
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
		parents = new ArrayList<Command>();
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

	public void setCommands(KeyWord temp, Script script) {

		if (temp.getCall().equals("print")) {
			cmd = new Print(script);
			cmd.setKey(temp);
			parents.add(cmd);
		}
		if (temp.getCall().equals("int")) {
			cmd = new IntCmd(script, (String) temp.getAttribute("name")
					.getValue(), Integer.parseInt((String) temp.getAttribute(
					"value").getValue()));
			cmd.setKey(temp);

		}
		if (temp.getCall().equals("boolean")) {
			cmd = new Bool(script, (String) temp.getAttribute("name")
					.getValue(), Boolean.parseBoolean((String) temp
					.getAttribute("value").getValue()));
			cmd.setKey(temp);

		}
		if (temp.getCall().equals("string")) {
			cmd = new StringCmd(script, (String) temp.getAttribute("name")
					.getValue(), (String) temp.getAttribute("value").getValue());
			cmd.setKey(temp);

		}
		if (temp.getCall().equals("@var")) {
			cmd = new Var(script,
					(String) temp.getAttribute("name").getValue(),
					(String) temp.getAttribute("set").getValue());
			cmd.setKey(temp);
		}
		if (temp.getCall().equals("rml")) {
			script.name = (String) temp.getAttribute("class").getValue();
			parents.add(cmd);
		}
		if (temp.getCall().equals("func")) {
			cmd = new Function(script, (String) temp.getAttribute("name")
					.getValue());
			cmd.setKey(temp);

			script.functions.add((Function) cmd);
			parents.add(cmd);

		}
		if (temp.getCall().equals("@func")) {
			cmd = new ExeFunc(script);
			cmd.setKey(temp);
		}

		if (temp.getCall().equals("list")) {
			cmd = new ListCMD(script, temp.getAttribute("name").getValue()
					.toString(), temp.getAttribute("type").getValue()
					.toString());
			cmd.setKey(temp);
			parents.add(cmd);
		}
		if (temp.getCall().equals("if")) {
			cmd.setKey(temp);
			cmd = new If(script, (String) temp.getAttribute("cond").getValue());
			parents.add(cmd);
		}
		if (cmd != null)
			cmd.setKey(temp);

	}

	public Command getCmd() {
		return cmd;
	}

	public void setCmd(Command cmd) {
		this.cmd = cmd;
	}

	public Command getParent() {
		return parent;
	}

	public void setParent(Command parent) {
		this.parent = parent;
	}

}
