package se_hw2.Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import se_hw2.LineStorage;

public class FileInputStrategy implements InputStrategy {

	BufferedReader reader;
	//TODO, use this filename
	String fileName;
	
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
		if(fileName == null) {
			System.out.println("Type your file path to read from");
			fileName = new ConsoleInputStrategy().readLine();
		}
		
		try {
			reader = new BufferedReader(new FileReader(fileName));
		}
		catch(Exception e) {
			System.out.println("Bad File Name, please try again"); //TODO: add a way to quit trying again
			fileName = null;
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
