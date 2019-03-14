package se_hw1.Output;

import se_hw1.LineStorage;

public class ConsoleOutputStrategy implements OutputStrategy {

	public void writeLine(String _line) {
		
		System.out.println(_line);
	
	}
	
	public void writeLines(LineStorage _lines) {
		
		for (int i = 0; i < _lines.getSize(); i++ ) {
			writeLine(_lines.getLineAsString(i));
		}
		
	}
}
