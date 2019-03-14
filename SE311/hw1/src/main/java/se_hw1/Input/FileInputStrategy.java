package se_hw1.Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import se_hw1.Config;
import se_hw1.LineStorage;

public class FileInputStrategy implements InputStrategy {

	BufferedReader reader;
	
	public String readLine() {
		
		//if file is set use that, other wise use f
		boolean readerWasNull = false;
		if (reader == null) {
			openReader();
			readerWasNull = true;
		}
		
		String line = null;
		
		try {
			line = reader.readLine();
		} catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (readerWasNull) {
			closeReader();
		}
	
		return line;
	}
	
	public LineStorage readLines() {
		LineStorage lines = new LineStorage();
		
		openReader();
		
		String line = "";
		while (true) {
			line = readLine();
			if(line != null) {		
				if(!line.equals("")) {
					lines.addLine(line);
				}
			}
			else {
				break;
			}
		}
		
		closeReader();
		
		return lines;
	}
	
	private void openReader() {
		String fileName;
		if(Config.InputFile == null) {
			System.out.println("Type your file path to read from");
			fileName = new ConsoleInputStrategy().readLine();
		}
		else {
			fileName = Config.InputFile;
			Config.InputFile = null;
		}
		
		try {
			reader = new BufferedReader(new FileReader(fileName));
		}
		catch(Exception e) {
			System.out.println("Bad File Name, please try again"); //TODO: add a way to quit trying again
			openReader();
		}
	}
	
	private void closeReader() {
		try {
			reader.close();
			reader = null;
		}
		catch(Exception e) {
			
		}
	}
}
