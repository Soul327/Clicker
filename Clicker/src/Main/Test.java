package Main;

import java.io.File;

import Misc.YAML;
import States.Galaxy;

public class Test {
	public static void main(String args[]) {
		YAML file = new YAML(new File("Test.sav"));
		file.set("TestString", "Hello World");
		file.set("TestString.sav", "Hello sav");
		file.save();
		System.out.println( file.getString("TestString"));
		System.out.println( file.getString("TestString.sav"));
		//System.out.println("Test".split("\."));
	}
}