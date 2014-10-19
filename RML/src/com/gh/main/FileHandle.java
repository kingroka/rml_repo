package com.gh.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.gh.cmds.Function;

public class FileHandle {
	public static ArrayList<Script> scripts = new ArrayList<Script>();

	public static ArrayList<File> files = new ArrayList<File>();
	public static String pathToFolder;
	public static Parser parser;
	public static void init(Parser p, String path) {
		parser = p;
		parser.defineKeyWords();
		findFiles(path);
	}

	private static boolean parsed;
	
	public static void findFiles(String path) {
		if (!parsed) {
			pathToFolder = path;
			File root = new File(path);
			File f;
			if (root == null || files == null || !root.exists()) {
				System.out.println("Root-Folder does not exist: "+ root.getAbsolutePath());
				return;
			} // just for safety
			if (root.isDirectory()) {
				for (int j = 0; j < root.listFiles().length; j++) {
					f = root.listFiles()[j];
					if (f.isFile() && f.getName().endsWith(".rml")) {
						files.add(f);
						System.out.println("found-> "+f.getName());
					} else if (f.isDirectory()) {
						root = f;
					}
				}
			}
			for (int i = 0; i < files.size(); i++) {

				scripts.add(new Script());
				scripts.get(i).path = files.get(i).getAbsolutePath();
				parser.parse(scripts.get(i));
			}
			parsed=true;
		}

	}

	static int index = 0;

	public static BufferedReader getNextScript() {

		try {
			for (int i = 0; i < scripts.size(); i++) {
				if (!scripts.get(i).compiled) {
					index = i;
					break;
				}
			}
			return new BufferedReader(new FileReader(scripts.get(index)
					.getPath()));
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	public static BufferedReader getScript(int i) {
		try {
			return new BufferedReader(new FileReader(scripts.get(i).getPath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public static void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < scripts.size(); i++) {
			scripts.get(i).execute();
		}
	}
	
	public Script getScript(String name){
		Script f=null;
		for(int i=0;i<scripts.size();i++){
			if(scripts.get(i).name.trim().equals(name.trim())){
				f=scripts.get(i);
				break;
			}
		}
		return f;
	}
	
	public Function getFunction(String name,String scriptName){
		Function f=null;
		Script script = getScript(scriptName);
		for(int i=0;i<script.functions.size();i++){
			if(script.functions.get(i).name.trim().equals(name.trim())){
				f=script.functions.get(i);
				break;
			}
		}
		return f;
	}
}
