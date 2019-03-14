package se_hw3.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import se_hw3.LineStorage;
import se_hw3.Observer.PassLinesObserver;

public class ConsoleInputStrategy extends InputStrategy {
	
	String[] args;
	
	public ConsoleInputStrategy(String[] args_, PassLinesObserver obs) {
		super(obs);
		args = args_;
	}
	
	public String readLine() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		 
		String line = null;
		
		while (line == null) {
			try {
				line = reader.readLine();
			} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return line;
	}
	
	public void readLines() {
		LineStorage lines = new LineStorage();
		String line = "";
		for (int i = 0; i < args.length; i++) {
			if(args[i].contains(".")) {
				args[i] = args[i].replace(".", "");
				if(line.equals("")) {
					line += args[i];
				}
				else {
					line += " " + args[i];
				}
				lines.addLine(line);
				line = "";
			} else {
				if(line.equals("")) {
					line += args[i];
				}
				else {
					line += " " + args[i];
				}
			}
		}
		
		this.callFinishedReadingEvent(lines);
	}

}
