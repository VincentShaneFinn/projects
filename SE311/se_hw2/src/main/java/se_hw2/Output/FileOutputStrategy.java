package se_hw2.Output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import se_hw2.LineStorage;
import se_hw2.Input.ConsoleInputStrategy;

public class FileOutputStrategy implements OutputStrategy {

	FileWriter fileWriter = null;
	BufferedWriter bufferedWriter = null;
	String fileName;
	
	public void writeLine(String _line) {
		
		boolean bufferWasNull = false;
		if (fileWriter == null) {
			bufferWasNull = true;
			openWriter();
		}
		
		try {
			fileWriter.write(_line + System.lineSeparator());
		}
		catch(Exception e) {
			
		}
		
		if (bufferWasNull) {
			closeWriter();
		}
	
	}
	
	public void writeLines(LineStorage _lines) {
		
		openWriter();
		
		for (int i = 0; i < _lines.getSize(); i++ ) {
			writeLine(_lines.getLineAsString(i));
		}
		
		closeWriter();
		System.out.println("File saved Successfully.");
	}
	
	private void openWriter() {
		if(fileName == null) {
			new ConsoleOutputStrategy().writeLine("Type your file path to write to");
			fileName = new ConsoleInputStrategy().readLine();
		}
		
		File file = new File(fileName);
		fileWriter = null;
		bufferedWriter = null;
		try {
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
		}
		catch(Exception e) {
			System.out.println("Issue Creating file, please try again"); //TODO: add a way to quit trying again
			fileName = null;
			openWriter();
		}
	}
	
	private void closeWriter() {
		try {
			fileWriter.close();
			bufferedWriter.close();
			fileWriter = null;
			bufferedWriter = null;
		}
		catch(Exception e) {
			
		}
	}
	
}
