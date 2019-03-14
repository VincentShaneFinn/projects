package se_hw1.IO;

public class IOMode {

	public enum Mode{
		Console,
		File
	}
	
	public static Mode getDefaultMode() {
		return Mode.Console;
	}
	
	public static Mode toMode(String command) {
		switch(command) {
		case "$Console" :
			return Mode.Console;
		case "$File" :
			return Mode.File;
		default:
			return null;
		}
	}
}
