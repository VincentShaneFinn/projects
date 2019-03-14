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
		if(command.equals("$Console")) {
			return Mode.Console;
		}
		else if (command.equals("$File")) {
			return Mode.File;
		}
		else {
			return null;
		}
	}
}
